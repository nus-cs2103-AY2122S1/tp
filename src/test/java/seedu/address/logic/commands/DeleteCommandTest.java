package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.DONUT;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.Test;

import seedu.address.model.BookKeeping;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TransactionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.testutil.ItemDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs(),
            new TransactionList(), new BookKeeping());

    @Test
    public void execute_deleteExistingItemByName_success() {
        model.addItem(BAGEL);

        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        DeleteCommand deleteCommand = new DeleteCommand(bagelDescriptor);

        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.deleteItem(BAGEL);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_SUCCESS, BAGEL);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteExistingItemById_success() {
        model.addItem(BAGEL);

        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL).build();
        DeleteCommand deleteCommand = new DeleteCommand(bagelDescriptor);

        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.deleteItem(BAGEL);

        String expectedMessage = String.format(seedu.address.logic.commands.DeleteCommand.MESSAGE_SUCCESS, BAGEL);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteExistingItemByNameAndId_success() {
        model.addItem(BAGEL);

        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        DeleteCommand deleteCommand = new DeleteCommand(bagelDescriptor);

        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.deleteItem(BAGEL);

        String expectedMessage = String.format(seedu.address.logic.commands.DeleteCommand.MESSAGE_SUCCESS, BAGEL);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentItem_throwsCommandException() {
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();

        DeleteCommand deleteCommand = new DeleteCommand(bagelDescriptor);
        String expectedMessage = DeleteCommand.MESSAGE_ITEM_NOT_FOUND;

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_idExistNonexistentName_throwsCommandException() {
        model.addItem(BAGEL);
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName("boo").withId(VALID_ID_BAGEL).build();

        DeleteCommand deleteCommand = new DeleteCommand(bagelDescriptor);
        String expectedMessage = DeleteCommand.MESSAGE_NAME_NOT_FOUND;

        Model expectedModel = new ModelManager(model.getInventory(), model.getUserPrefs(),
                new TransactionList(), new BookKeeping());

        assertCommandFailure(deleteCommand, model, expectedModel, expectedMessage);
    }

    @Test
    public void execute_nameExistNonexistentId_throwsCommandException() {
        model.addItem(BAGEL);
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId("182018").build();

        DeleteCommand deleteCommand = new DeleteCommand(bagelDescriptor);
        String expectedMessage = DeleteCommand.MESSAGE_ID_NOT_FOUND;

        Model expectedModel = new ModelManager(model.getInventory(), model.getUserPrefs(),
                new TransactionList(), new BookKeeping());

        assertCommandFailure(deleteCommand, model, expectedModel, expectedMessage);
    }

    @Test
    public void execute_multipleMatches_throwsCommandException() {
        model.addItem(BAGEL);
        model.addItem(DONUT);

        ItemDescriptor descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_DONUT).build();

        DeleteCommand deleteCommand = new DeleteCommand(descriptor);
        String expectedMessage = DeleteCommand.MESSAGE_MULTIPLE_MATCHES;

        Model expectedModel = new ModelManager(model.getInventory(), model.getUserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.updateFilteredItemList(DISPLAY_INVENTORY, x-> x.equals(BAGEL) || x.equals(DONUT));

        assertCommandFailure(deleteCommand, model, expectedModel, expectedMessage);
    }

    @Test
    public void equals() {
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder(BAGEL).build();
        ItemDescriptor donutDescriptor = new ItemDescriptorBuilder(DONUT).build();

        DeleteCommand deleteFirstCommand = new DeleteCommand(bagelDescriptor);
        DeleteCommand deleteSecondCommand = new DeleteCommand(donutDescriptor);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(bagelDescriptor);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different values -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
