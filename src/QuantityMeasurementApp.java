public class QuantityMeasurementApp {

    // Step 1: Extended Unit Enum
    enum Unit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(1.0 / 30.48); // 1 cm = 0.0328084 feet approx

        private final double toFeet;

        Unit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double convertToFeet(double value) {
            return value * toFeet;
        }
    }

    // Step 2: Generic Quantity Class (UNCHANGED from UC3)
    static class Quantity {

        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double getBaseValue() {
            return unit.convertToFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.getBaseValue(), other.getBaseValue()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(getBaseValue());
        }
    }

    // Helper method
    public static boolean compare(double v1, Unit u1, double v2, Unit u2) {
        return new Quantity(v1, u1).equals(new Quantity(v2, u2));
    }

    // Main demo
    public static void main(String[] args) {

        System.out.println("1 YARD = 3 FEET → " +
                compare(1.0, Unit.YARD, 3.0, Unit.FEET));

        System.out.println("1 YARD = 36 INCH → " +
                compare(1.0, Unit.YARD, 36.0, Unit.INCH));

        System.out.println("1 CM = 0.393701 INCH → " +
                compare(1.0, Unit.CENTIMETER, 0.393701, Unit.INCH));

        System.out.println("2 YARD = 2 YARD → " +
                compare(2.0, Unit.YARD, 2.0, Unit.YARD));
    }
}