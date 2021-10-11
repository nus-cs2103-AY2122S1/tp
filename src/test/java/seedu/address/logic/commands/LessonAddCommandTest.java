package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.SampleDataUtil;

/**
 * Contains integration tests (interaction with the Model) and unit tests for LessonAddCommand.
 */
public class LessonAddCommandTest {

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonAddCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void equals() {
        LessonAddCommand addSampleLessonCommand = new LessonAddCommand(INDEX_FIRST_PERSON,
            SampleDataUtil.getSampleLesson());
        LessonAddCommand addSampleLessonCommand2 = new LessonAddCommand(INDEX_SECOND_PERSON,
            SampleDataUtil.getSampleLesson());

        // same object -> returns true
        assertTrue(addSampleLessonCommand.equals(addSampleLessonCommand));

        // same values -> returns true
        LessonAddCommand addSampleLessonCommandCopy = new LessonAddCommand(INDEX_FIRST_PERSON,
            SampleDataUtil.getSampleLesson());
        assertTrue(addSampleLessonCommand.equals(addSampleLessonCommandCopy));

        // different types -> returns false
        assertFalse(addSampleLessonCommand.equals(1));

        // null -> returns false
        assertFalse(addSampleLessonCommand.equals(null));

        // different person -> returns false
        assertFalse(addSampleLessonCommand.equals(addSampleLessonCommand2));
    }
}
