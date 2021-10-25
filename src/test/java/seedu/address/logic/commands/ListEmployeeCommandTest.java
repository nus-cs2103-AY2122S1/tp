package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListEmployeeCommand.SHOWING_SWITCH_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;



public class ListEmployeeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_employeeSwitch_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_SWITCH_MESSAGE,
                false, false, false, true, false, false);
        assertCommandSuccess(new ListEmployeeCommand(), model, expectedCommandResult, expectedModel);
    }
}
