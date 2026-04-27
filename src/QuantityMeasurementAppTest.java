import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // YARD tests
    @Test
    void testEquality_YardToFeet() {
        assertTrue(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.Unit.YARD,
                3.0, QuantityMeasurementApp.Unit.FEET));
    }

    @Test
    void testEquality_YardToInch() {
        assertTrue(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.Unit.YARD,
                36.0, QuantityMeasurementApp.Unit.INCH));
    }

    @Test
    void testEquality_YardToYard_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.Unit.YARD,
                1.0, QuantityMeasurementApp.Unit.YARD));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.Unit.YARD,
                2.0, QuantityMeasurementApp.Unit.YARD));
    }

    // CM tests
    @Test
    void testEquality_CmToInch() {
        assertTrue(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.Unit.CENTIMETER,
                0.393701, QuantityMeasurementApp.Unit.INCH));
    }

    @Test
    void testEquality_CmToCm_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(
                2.0, QuantityMeasurementApp.Unit.CENTIMETER,
                2.0, QuantityMeasurementApp.Unit.CENTIMETER));
    }

    @Test
    void testEquality_CmToFeet_NonEquivalent() {
        assertFalse(QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.Unit.CENTIMETER,
                1.0, QuantityMeasurementApp.Unit.FEET));
    }

    // Multi-unit transitive test
    @Test
    void testTransitive_YardFeetInch() {

        boolean yardToFeet = QuantityMeasurementApp.compare(
                1.0, QuantityMeasurementApp.Unit.YARD,
                3.0, QuantityMeasurementApp.Unit.FEET);

        boolean feetToInch = QuantityMeasurementApp.compare(
                3.0, QuantityMeasurementApp.Unit.FEET,
                36.0, QuantityMeasurementApp.Unit.INCH);

        assertTrue(yardToFeet && feetToInch);
    }

    // Safety tests
    @Test
    void testSameReference() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.Unit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testNullComparison() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.Unit.FEET);

        assertFalse(q.equals(null));
    }
}