package seedu.unify.logic.commands;

import static seedu.unify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unify.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.unify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.unify.testutil.TypicalTasks.getTypicalUniFy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.unify.model.Model;
import seedu.unify.model.ModelManager;
import seedu.unify.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalUniFy(), new UserPrefs());
        expectedModel = new ModelManager(model.getUniFy(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
