package seedu.edrecord.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class GradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Grade(null, null));
    }

    @Test
    public void constructor_emptyScore_success() {
        Optional<Score> score = Optional.empty();
        Grade expectedGrade = new Grade(score, Grade.GradeStatus.GRADED);
        assertEquals(expectedGrade, new Grade(score, Grade.GradeStatus.GRADED));
    }

    @Test
    public void isValidGrade() {
        Optional<Score> validScore = Optional.of(new Score("50"));

        // null grade
        assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null, null));

        // no score, valid status
        assertTrue(Grade.isValidGrade(Grade.GradeStatus.NOT_SUBMITTED, Optional.empty()));
        assertTrue(Grade.isValidGrade(Grade.GradeStatus.SUBMITTED, Optional.empty()));
        assertTrue(Grade.isValidGrade(Grade.GradeStatus.GRADED, Optional.empty()));

        // score present, valid status
        assertTrue(Grade.isValidGrade(Grade.GradeStatus.GRADED, validScore));

        // score present, invalid status
        assertFalse(Grade.isValidGrade(Grade.GradeStatus.NOT_SUBMITTED, validScore));
        assertFalse(Grade.isValidGrade(Grade.GradeStatus.SUBMITTED, validScore));
    }

}
