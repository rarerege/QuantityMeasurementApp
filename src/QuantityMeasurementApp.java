public class QuantityMeasurementApp {

    // =========================
    // Unit Enum (from UC5)
    // =========================
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(1.0 / 30.48);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeet;
        }
    }

    // =========================
    // Value Object
    // =========================
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toBase() {
            return unit.toFeet(value);
        }

        // Convert from base unit to target unit
        private double fromBase(double baseValue, LengthUnit targetUnit) {
            return targetUnit.fromFeet(baseValue);
        }

        // =========================
        // UC5: Conversion
        // =========================
        public double convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double base = toBase();
            return fromBase(base, targetUnit);
        }

        // =========================
        // UC6: ADDITION OPERATION
        // =========================
        public QuantityLength add(QuantityLength other, LengthUnit resultUnit) {

            if (other == null || resultUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumInBase =
                    this.toBase() + other.toBase();

            double resultValue =
                    resultUnit.fromFeet(sumInBase);

            return new QuantityLength(resultValue, resultUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // =========================
    // Static API (as required)
    // =========================

    public static QuantityLength add(QuantityLength q1, QuantityLength q2) {
        return q1.add(q2, q1.unit);
    }

    // Demo method
    public static void main(String[] args) {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = q1.add(q2, LengthUnit.FEET);

        System.out.println("Result: " + result);

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARD);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("Yard + Feet = " +
                yard.add(feet, LengthUnit.YARD));
    }
}