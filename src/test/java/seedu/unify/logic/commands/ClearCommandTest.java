package seedu.unify.logic.commands;

import static seedu.unify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unify.testutil.TypicalTasks.getTypicalUniFy;

import org.junit.jupiter.api.Test;

import seedu.unify.model.Model;
import seedu.unify.model.ModelManager;
import seedu.unify.model.UniFy;
import seedu.unify.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyUniFy_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyUniFy_success() {
        Model model = new ModelManager(getTypicalUniFy(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalUniFy(), new UserPrefs());
        expectedModel.setUniFy(new UniFy());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
