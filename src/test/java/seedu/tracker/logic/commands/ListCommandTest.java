package seedu.tracker.logic.commands;

import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tracker.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tracker.model.Model;
import seedu.tracker.model.ModelManager;
import seedu.tracker.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModuleTracker(), new UserPrefs());
        expectedModel = new ModelManager(model.getModuleTracker(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
