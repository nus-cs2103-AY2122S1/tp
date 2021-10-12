package seedu.tracker.logic.commands;

import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

import org.junit.jupiter.api.Test;

import seedu.tracker.model.Model;
import seedu.tracker.model.ModelManager;
import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyModuleTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyModuleTracker_success() {
        Model model = new ModelManager(getTypicalModuleTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalModuleTracker(), new UserPrefs());
        expectedModel.setModuleTracker(new ModuleTracker());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
