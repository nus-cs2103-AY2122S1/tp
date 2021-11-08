package seedu.smartnus.logic.commands;

import static seedu.smartnus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptySmartNus_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptySmartNus_success() {
        Model model = new ModelManager(getTypicalSmartNus(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSmartNus(), new UserPrefs());
        expectedModel.setSmartNus(new SmartNus());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
