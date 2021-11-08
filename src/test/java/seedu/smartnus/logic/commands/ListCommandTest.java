package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.logic.commands.CommandTestUtil.showQuestionAtIndex;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
        expectedModel = new ModelManager(model.getSmartNus(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand("question"), model, ListCommand.MESSAGE_SUCCESS_QUESTIONS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showQuestionAtIndex(model, INDEX_FIRST_QUESTION);
        assertCommandSuccess(new ListCommand("question"), model, ListCommand.MESSAGE_SUCCESS_QUESTIONS, expectedModel);
    }

    @Test
    public void execute_listNotes() {
        assertCommandSuccess(new ListCommand("note"), model, ListCommand.MESSAGE_SUCCESS_NOTES, expectedModel);
    }

    @Test
    public void execute_listTag() {
        assertCommandSuccess(new ListCommand("tag"), model, ListCommand.MESSAGE_SUCCESS_TAGS, expectedModel);
    }

    @Test
    public void test_equals() {
        assertEquals(new ListCommand("question"), new ListCommand("question"));
        assertNotEquals(new ListCommand("question"), new ListCommand("notes"));
    }
}
