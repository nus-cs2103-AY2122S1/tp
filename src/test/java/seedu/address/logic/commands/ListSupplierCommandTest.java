package seedu.address.logic.commands;

import static seedu.address.logic.commands.ListSupplierCommand.SHOWING_SWITCH_MESSAGE;
import static seedu.address.logic.commands.SupplierCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ListSupplierCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_supplierSwitch_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_SWITCH_MESSAGE,
                false, false, false, false, true, false);
        assertCommandSuccess(new ListSupplierCommand(), model, expectedCommandResult, expectedModel);
    }
}
