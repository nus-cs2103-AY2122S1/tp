package seedu.address.logic.commands;

import static seedu.address.logic.commands.CustomerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListCustomerCommand.SHOWING_SWITCH_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class ListCustomerCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_customerSwitch_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_SWITCH_MESSAGE,
                false, false, true, false, false, false);
        assertCommandSuccess(new ListCustomerCommand(), model, expectedCommandResult, expectedModel);
    }
}
