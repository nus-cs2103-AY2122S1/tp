package seedu.address.logic.commands;

import static seedu.address.logic.commands.ResetCustomerSortCommand.SHOWING_RESET_MESSAGE;
import static seedu.address.logic.commands.SupplierCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ResetCustomerSortCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_resetSupplierSort_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_RESET_MESSAGE,
                false, false, true, false, false, false);
        assertCommandSuccess(new ResetCustomerSortCommand(), model, expectedCommandResult, expectedModel);
    }
}
