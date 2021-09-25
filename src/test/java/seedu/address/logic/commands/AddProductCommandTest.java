package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddProductCommand.MESSAGE_ADD_PRODUCT_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class AddProductCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_success() {
        assertCommandSuccess(new AddProductCommand(), model, MESSAGE_ADD_PRODUCT_SUCCESS, model);
    }
}