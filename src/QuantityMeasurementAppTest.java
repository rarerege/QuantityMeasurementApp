import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testKgToGramEquality() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(gram));
    }

    @Test
    public void testKgToPoundEquality() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight pound = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertTrue(kg.equals(pound));
    }

    @Test
    public void testConvertKgToGram() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertEquals(1000.0,
                kg.convertTo(WeightUnit.GRAM).getValue(),
                0.0001);
    }

    @Test
    public void testAddSameUnit() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = a.add(b);

        assertEquals(3.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddDifferentUnits() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = kg.add(gram);

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUnit() {
        new QuantityWeight(1.0, null);
    }

    @Test
    public void testIncompatibleCategoryNotAllowed() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        // This should be false due to class mismatch (Length class not included here)
        assertFalse(weight.equals("1 kg"));
    }
}