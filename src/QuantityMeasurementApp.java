public class QuantityMeasurementApp {

    // Step 1: Enum for units
    enum Unit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionFactorToFeet;

        Unit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }

        public double toFeet(double value) {
            return value * conversionFactorToFeet;
        }
    }

    // Step 2: Generic Quantity class
    static class Quantity {

        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double getBaseValue() {
            return unit.toFeet(value);
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

    // Helper methods (as required in UC flow)
    public static boolean compare(double value1, Unit unit1, double value2, Unit unit2) {
        Quantity q1 = new Quantity(value1, unit1);
        Quantity q2 = new Quantity(value2, unit2);
        return q1.equals(q2);
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("Input: 1.0 feet and 12.0 inches");
        System.out.println("Output: Equal (" +
                compare(1.0, Unit.FEET, 12.0, Unit.INCH) + ")");

        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" +
                compare(1.0, Unit.INCH, 1.0, Unit.INCH) + ")");
    }
}