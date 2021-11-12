package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_MODULE;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TIME_QUIZ;
import static seedu.unify.testutil.TypicalTasks.CS1234_QUIZ;
import static seedu.unify.testutil.TypicalTasks.QUIZ;

import org.junit.jupiter.api.Test;

import seedu.unify.testutil.TaskBuilder;

public class TaskTest {


    @Test
    public void getTimeRepresentation() {
        Task task20211011060 = new TaskBuilder().withName("Test1").withDate("2021-10-11")
                        .withTime("01:00").build();
        Task task20210101000 = new TaskBuilder().withName("Test2").withDate("2021-01-01")
                        .withTime("00:00").build();

        assertTrue(task20211011060.getTimeRepresentation() == 20211011060L);
        assertFalse(task20210101000.getTimeRepresentation() == 20211011060L);

    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(CS1234_QUIZ.isSameTask(CS1234_QUIZ));

        // null -> returns false
        assertFalse(CS1234_QUIZ.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedAlice = new TaskBuilder(CS1234_QUIZ).withTime(VALID_TIME_QUIZ)
                .withDate(VALID_DATE_QUIZ).withTags(VALID_TAG_MODULE).build();
        assertTrue(CS1234_QUIZ.isSameTask(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new TaskBuilder(CS1234_QUIZ).withName(VALID_NAME_QUIZ).build();
        assertFalse(CS1234_QUIZ.isSameTask(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Task editedBob = new TaskBuilder(QUIZ).withName(VALID_NAME_QUIZ.toLowerCase()).build();
        assertFalse(QUIZ.isSameTask(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_QUIZ + " ";
        editedBob = new TaskBuilder(QUIZ).withName(nameWithTrailingSpaces).build();
        assertFalse(QUIZ.isSameTask(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(CS1234_QUIZ).build();
        assertTrue(CS1234_QUIZ.equals(aliceCopy));

        // same object -> returns true
        assertTrue(CS1234_QUIZ.equals(CS1234_QUIZ));

        // null -> returns false
        assertFalse(CS1234_QUIZ.equals(null));

        // different type -> returns false
        assertFalse(CS1234_QUIZ.equals(5));

        // different task -> returns false
        assertFalse(CS1234_QUIZ.equals(QUIZ));

        // different name -> returns false
        Task editedQuiz = new TaskBuilder(CS1234_QUIZ).withName(VALID_NAME_QUIZ).build();
        assertFalse(CS1234_QUIZ.equals(editedQuiz));

        // different time -> returns false
        editedQuiz = new TaskBuilder(CS1234_QUIZ).withTime(VALID_TIME_QUIZ).build();
        assertFalse(CS1234_QUIZ.equals(editedQuiz));

        // different date -> returns false
        editedQuiz = new TaskBuilder(CS1234_QUIZ).withDate(VALID_DATE_QUIZ).build();
        assertFalse(CS1234_QUIZ.equals(editedQuiz));

        // different tags -> returns false
        editedQuiz = new TaskBuilder(CS1234_QUIZ).withTags(VALID_TAG_CCA).build();
        assertFalse(CS1234_QUIZ.equals(editedQuiz));
    }
}
