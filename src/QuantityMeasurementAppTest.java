import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // Same unit addition
    @Test
    void testAddition_FeetPlusFeet() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result =
                q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(3.0, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), 0.0001);
    }

    // Cross unit addition
    @Test
    void testAddition_FeetPlusInch() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), 0.0001);
    }

    // Reverse direction addition
    @Test
    void testAddition_InchPlusFeet() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result =
                q1.add(q2, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0, result.convertTo(QuantityMeasurementApp.LengthUnit.INCH), 0.0001);
    }

    // YARD + FEET
    @Test
    void testAddition_YardPlusFeet() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.YARD);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(3.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result =
                q1.add(q2, QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(2.0, result.convertTo(QuantityMeasurementApp.LengthUnit.YARD), 0.0001);
    }

    // Identity test (zero)
    @Test
    void testAddition_WithZero() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(5.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength zero =
                new QuantityMeasurementApp.QuantityLength(0.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                q1.add(zero, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(5.0, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), 0.0001);
    }

    // Negative values
    @Test
    void testAddition_NegativeValues() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(5.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(-2.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result =
                q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(3.0, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), 0.0001);
    }

    // Commutativity
    @Test
    void testAddition_Commutativity() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength r1 =
                q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength r2 =
                q2.add(q1, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(r1.convertTo(QuantityMeasurementApp.LengthUnit.FEET),
                r2.convertTo(QuantityMeasurementApp.LengthUnit.FEET), 0.0001);
    }

    // Null handling
    @Test
    void testAddition_NullOperand() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            q1.add(null, QuantityMeasurementApp.LengthUnit.FEET);
        });
    }
}