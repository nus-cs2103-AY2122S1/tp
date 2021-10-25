package seedu.address.logic.commands;

import static seedu.address.logic.commands.CustomerCommand.SHOWING_SWITCH_MESSAGE;
import static seedu.address.logic.commands.CustomerCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class CustomerCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_customerSwitch_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_SWITCH_MESSAGE,
                false, false, true, false, false, false);
        assertCommandSuccess(new CustomerCommand(), model, expectedCommandResult, expectedModel);
    }
}
