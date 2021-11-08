package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalSchedule;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Schedule;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptySchedule_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptySchedule_success() {
        Model model = new ModelManager(getTypicalSchedule(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSchedule(), new UserPrefs());
        expectedModel.setSchedule(new Schedule());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
