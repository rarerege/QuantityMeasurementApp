import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testFeetToBase() {
        assertEquals(1.0,
                LengthUnit.FEET.convertToBaseUnit(1.0),
                0.0001);
    }

    @Test
    void testInchToBase() {
        assertEquals(1.0,
                LengthUnit.INCH.convertToBaseUnit(12.0),
                0.0001);
    }

    @Test
    void testYardToBase() {
        assertEquals(3.0,
                LengthUnit.YARD.convertToBaseUnit(1.0),
                0.0001);
    }

    @Test
    void testCmToBase() {
        assertEquals(1.0,
                LengthUnit.CENTIMETER.convertToBaseUnit(30.48),
                0.0001);
    }

    @Test
    void testBaseToInch() {
        assertEquals(12.0,
                LengthUnit.INCH.convertFromBaseUnit(1.0),
                0.0001);
    }

    @Test
    void testBaseToYard() {
        assertEquals(1.0,
                LengthUnit.YARD.convertFromBaseUnit(3.0),
                0.0001);
    }

    // =========================
    // Quantity Tests
    // =========================

    @Test
    void testEquality_Feet_Inch() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testConvert_FeetToInch() {

        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(12.0, q.convertTo(LengthUnit.INCH), 0.0001);
    }

    @Test
    void testAdd_WithTargetUnit_Yard() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                q1.add(q2, LengthUnit.YARD);

        assertEquals(0.667,
                result.convertTo(LengthUnit.YARD),
                0.01);
    }

    @Test
    void testAdd_Feet_Feet() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(2.0, LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result =
                q1.add(q2, LengthUnit.FEET);

        assertEquals(3.0,
                result.convertTo(LengthUnit.FEET),
                0.0001);
    }

    @Test
    void testNullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.QuantityLength(1.0, null));
    }
}