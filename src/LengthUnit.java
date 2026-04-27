public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(1.0 / 30.48);

    private final double toFeet;

    LengthUnit(double toFeet) {
        this.toFeet = toFeet;
    }

    // Convert this unit → base unit (feet)
    public double convertToBaseUnit(double value) {
        return value * toFeet;
    }

    // Convert base unit (feet) → this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeet;
    }
}