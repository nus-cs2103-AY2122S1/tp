package seedu.address.logic.general;

import static seedu.address.logic.general.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.Test;

import seedu.address.model.HrManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyHrManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyHrManager_success() {
        Model model = new ModelManager(getTypicalHrManager(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHrManager(), new UserPrefs());
        expectedModel.setHrManager(new HrManager());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
