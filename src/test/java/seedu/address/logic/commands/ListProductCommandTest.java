package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalProducts;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListProductCommand.
 */
public class ListProductCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalProducts.getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        String expectedMessage = ListProductCommand.MESSAGE_SUCCESS;
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandType.LIST, null, false);
        assertCommandSuccess(new ListProductCommand(), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);
        String expectedMessage = ListProductCommand.MESSAGE_SUCCESS;
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandType.LIST, null, false);
        assertCommandSuccess(new ListProductCommand(), model,
                expectedCommandResult, expectedModel);
    }
}
