public class QuantityMeasurementApp {

    /**
     * LengthUnit enum with conversion factors relative to FEET
     */
    enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(1.0 / 30.48);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double valueInFeet) {
            return valueInFeet / toFeetFactor;
        }
    }

    /**
     * QuantityLength (value object)
     */
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        // Convert current object to target unit
        public double convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseInFeet = unit.toFeet(value);
            return targetUnit.fromFeet(baseInFeet);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(
                    this.unit.toFeet(this.value),
                    other.unit.toFeet(other.value)
            ) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // =========================
    // API: Conversion Methods
    // =========================

    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        QuantityLength q = new QuantityLength(value, source);
        return q.convertTo(target);
    }

    // Overloaded method 1
    public static double demonstrateLengthConversion(double value,
                                                     LengthUnit source,
                                                     LengthUnit target) {
        return convert(value, source, target);
    }

    // Overloaded method 2
    public static double demonstrateLengthConversion(QuantityLength length,
                                                     LengthUnit target) {
        return length.convertTo(target);
    }

    // Equality demo
    public static boolean demonstrateLengthEquality(QuantityLength q1,
                                                    QuantityLength q2) {
        return q1.equals(q2);
    }

    // Main method (demo)
    public static void main(String[] args) {

        System.out.println("1 FEET → INCHES = " +
                convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        System.out.println("3 YARDS → FEET = " +
                convert(3.0, LengthUnit.YARD, LengthUnit.FEET));

        System.out.println("36 INCHES → YARDS = " +
                convert(36.0, LengthUnit.INCH, LengthUnit.YARD));

        System.out.println("1 CM → INCHES = " +
                convert(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH));
    }
}