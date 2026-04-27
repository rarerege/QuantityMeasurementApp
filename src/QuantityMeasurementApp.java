public class QuantityMeasurementApp {

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
        // CONVERT TO BASE UNIT
        // =========================
        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        // =========================
        // CONVERT TO TARGET UNIT
        // =========================
        public double convertTo(LengthUnit target) {

            if (target == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double base = toBase();
            return target.convertFromBaseUnit(base);
        }

        // =========================
        // ADD (UC6 / UC7 supported)
        // =========================
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumBase =
                    this.toBase() + other.toBase();

            double result =
                    targetUnit.convertFromBaseUnit(sumBase);

            return new QuantityLength(result, targetUnit);
        }

        // UC6 backward compatibility
        public QuantityLength add(QuantityLength other) {
            return add(other, this.unit);
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
    // STATIC API
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

        System.out.println(add(q1, q2, LengthUnit.FEET));
        System.out.println(add(q1, q2, LengthUnit.INCH));
        System.out.println(add(q1, q2, LengthUnit.YARD));
    }
}