import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAdd_ExplicitTarget_Feet() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.add(q1, q2,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0,
                result.convertTo(QuantityMeasurementApp.LengthUnit.FEET),
                0.0001);
    }

    @Test
    void testAdd_ExplicitTarget_Inch() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.add(q1, q2,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0,
                result.convertTo(QuantityMeasurementApp.LengthUnit.INCH),
                0.0001);
    }

    @Test
    void testAdd_ExplicitTarget_Yard() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.add(q1, q2,
                        QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(0.667,
                result.convertTo(QuantityMeasurementApp.LengthUnit.YARD),
                0.01);
    }

    @Test
    void testAdd_Commutativity_TargetUnit() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength r1 =
                QuantityMeasurementApp.add(q1, q2,
                        QuantityMeasurementApp.LengthUnit.YARD);

        QuantityMeasurementApp.QuantityLength r2 =
                QuantityMeasurementApp.add(q2, q1,
                        QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(
                r1.convertTo(QuantityMeasurementApp.LengthUnit.YARD),
                r2.convertTo(QuantityMeasurementApp.LengthUnit.YARD),
                0.0001
        );
    }

    @Test
    void testAdd_NullTargetUnit() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.add(q1, q2, null));
    }

    @Test
    void testAdd_WithZero() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(5.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength zero =
                new QuantityMeasurementApp.QuantityLength(0.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.add(q1, zero,
                        QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(
                5.0 / 3.0,
                result.convertTo(QuantityMeasurementApp.LengthUnit.YARD),
                0.0001
        );
    }

    @Test
    void testAdd_NegativeValues() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(5.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(-2.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.add(q1, q2,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(36.0,
                result.convertTo(QuantityMeasurementApp.LengthUnit.INCH),
                0.0001);
    }
}