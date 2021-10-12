package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SupplierCommand.SHOWING_SWITCH_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class SupplierCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_supplierSwitch_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_SWITCH_MESSAGE,
                false, false, false, true);
        assertCommandSuccess(new SupplierCommand(), model, expectedCommandResult, expectedModel);
    }
}
