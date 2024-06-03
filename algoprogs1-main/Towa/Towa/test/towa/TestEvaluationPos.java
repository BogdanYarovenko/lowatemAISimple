package towa;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author erimonteil
 */
public class TestEvaluationPos {

    @Test
    public void testEvaluation() {
        String actionTest ="AbC,45,36";
        int diff = EvaluationPos.evaluation(actionTest);
        assertEquals(9, diff);
        String actionTest2 ="AbC,3,5";
        int diff2 = EvaluationPos.evaluation(actionTest2);
        assertEquals(-2, diff2);
    }
}
