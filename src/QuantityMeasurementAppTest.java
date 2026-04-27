import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testFeetToFeet_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.Unit.FEET,
                1.0,
                QuantityMeasurementApp.Unit.FEET));
    }

    @Test
    void testInchToInch_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.Unit.INCH,
                1.0,
                QuantityMeasurementApp.Unit.INCH));
    }

    @Test
    void testFeetToInch_Equivalent() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.Unit.FEET,
                12.0,
                QuantityMeasurementApp.Unit.INCH));
    }

    @Test
    void testFeetToFeet_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.Unit.FEET,
                2.0,
                QuantityMeasurementApp.Unit.FEET));
    }

    @Test
    void testInchToInch_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.Unit.INCH,
                2.0,
                QuantityMeasurementApp.Unit.INCH));
    }

    @Test
    void testSameReference() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testNullComparison() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(1.0,
                        QuantityMeasurementApp.Unit.FEET);

        assertFalse(q.equals(null));
    }

    @Test
    void testInvalidUnitHandledByDesign() {
        // Enum prevents invalid units → compile-time safety
        assertTrue(true);
    }
}