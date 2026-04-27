public class QuantityMeasurementApp {

    // =========================
    // UNIT ENUM
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
    // VALUE OBJECT
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

        // =========================
        // BASE CONVERSION
        // =========================
        private double toBase() {
            return unit.toFeet(value);
        }

        private double fromBase(double base, LengthUnit target) {
            return target.fromFeet(base);
        }

        // =========================
        // UC5: CONVERSION
        // =========================
        public double convertTo(LengthUnit target) {

            if (target == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            return fromBase(toBase(), target);
        }

        // =========================
        // UC6: ADD (default = first operand unit)
        // =========================
        public QuantityLength add(QuantityLength other) {
            return add(other, this.unit);
        }

        // =========================
        // UC7: ADD (explicit target unit)
        // =========================
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumInBase = this.toBase() + other.toBase();
            double result = fromBase(sumInBase, targetUnit);

            return new QuantityLength(result, targetUnit);
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
    // DEMO API
    // =========================
    public static QuantityLength add(
            QuantityLength a,
            QuantityLength b,
            LengthUnit targetUnit
    ) {
        return a.add(b, targetUnit);
    }

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(
                add(q1, q2, LengthUnit.FEET)
        );

        System.out.println(
                add(q1, q2, LengthUnit.INCH)
        );

        System.out.println(
                add(q1, q2, LengthUnit.YARD)
        );
    }
}