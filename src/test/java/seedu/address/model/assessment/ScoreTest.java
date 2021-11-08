package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    public void constructor_invalidScore_throwsIllegalArgumentException() {
        int validActualScore = 10;
        int invalidTotalScore = 0;
        assertThrows(IllegalArgumentException.class, () -> new Score(validActualScore, invalidTotalScore));
    }

    @Test
    public void isValidScore() {
        // null score
        assertThrows(NullPointerException.class, () -> Score.isValidScore(null));

        // invalid score
        assertFalse(Score.isValidScore("")); // empty string
        assertFalse(Score.isValidScore("60100")); // no delimiter
        assertFalse(Score.isValidScore("60/100/")); // more than one delimiter
        assertFalse(Score.isValidScore("60:100")); // wrong delimiter
        assertFalse(Score.isValidScore("abc/abc")); // non-numerals characters
        assertFalse(Score.isValidScore("60/")); // only actual score
        assertFalse(Score.isValidScore("/100")); // only total score

        assertFalse(Score.isValidScore(-1, 100)); // negative actual score
        assertFalse(Score.isValidScore(5, -1)); // negative total score
        assertFalse(Score.isValidScore(5, 0)); // zero total score
        assertFalse(Score.isValidScore(11, 10)); // actual score greater than total score

        // valid score
        assertTrue(Score.isValidScore("60/100")); // numbers with delimiter
        assertTrue(Score.isValidScore("123456789/999999999")); // long actual and total scores

        assertTrue(Score.isValidScore(0, 100)); // zero actual score
        assertTrue(Score.isValidScore(99, 100)); // actual score less than total score
        assertTrue(Score.isValidScore(123456789, 999999999)); // large scores
    }
}
