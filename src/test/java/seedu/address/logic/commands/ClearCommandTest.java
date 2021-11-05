package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalClients;
import seedu.address.testutil.TypicalProducts;

public class ClearCommandTest {
    private final CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS,
            CommandType.CLEAR, null, false);

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(TypicalClients.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalClients.getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);

        model = new ModelManager(TypicalProducts.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(TypicalProducts.getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }
}
