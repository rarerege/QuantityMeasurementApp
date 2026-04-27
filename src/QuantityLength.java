import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
        }
        this.value = value;
        this.unit = unit;
    }

    // Convert to another unit
    public QuantityLength convertTo(LengthUnit targetUnit) {
        double baseValue = unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityLength(convertedValue, targetUnit);
    }

    // Equality based on base unit comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < 0.0001;
    }

    // Add two quantities and return result in target unit
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

        double sumInBase =
                this.unit.convertToBaseUnit(this.value)
                        + other.unit.convertToBaseUnit(other.value);

        double resultValue = targetUnit.convertFromBaseUnit(sumInBase);

        return new QuantityLength(resultValue, targetUnit);
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}