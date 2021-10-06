package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BAKED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditItemDescriptor;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.testutil.EditItemDescriptorBuilder;
import seedu.address.testutil.ItemBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Item editedItem = new ItemBuilder().build();
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(editedItem).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new Inventory(model.getInventory()), new UserPrefs());
        expectedModel.setItem(model.getFilteredItemList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastItem = Index.fromOneBased(model.getFilteredItemList().size());
        Item lastItem = model.getFilteredItemList().get(indexLastItem.getZeroBased());

        ItemBuilder itemInList = new ItemBuilder(lastItem);
        Item editedItem = itemInList.withName(VALID_NAME_DONUT).withTags(VALID_TAG_BAKED).build();

        EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withName(VALID_NAME_DONUT)
                .withTags(VALID_TAG_BAKED).build();
        EditCommand editCommand = new EditCommand(indexLastItem, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new Inventory(model.getInventory()), new UserPrefs());
        expectedModel.setItem(lastItem, editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, new EditItemDescriptor());
        Item editedItem = model.getFilteredItemList().get(INDEX_FIRST_ITEM.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new Inventory(model.getInventory()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);

        Item itemInFilteredList = model.getFilteredItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        Item editedItem = new ItemBuilder(itemInFilteredList).withName(VALID_NAME_DONUT).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM,
                new EditItemDescriptorBuilder().withName(VALID_NAME_DONUT).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new Inventory(model.getInventory()), new UserPrefs());
        expectedModel.setItem(model.getFilteredItemList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateItemUnfilteredList_failure() {
        Item firstItem = model.getFilteredItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(firstItem).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ITEM, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_duplicateItemFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);

        // edit item in filtered list into a duplicate in inventory
        Item itemInList = model.getInventory().getItemList().get(INDEX_SECOND_ITEM.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM,
                new EditItemDescriptorBuilder(itemInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_invalidItemIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItemList().size() + 1);
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withName(VALID_NAME_DONUT).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of inventory
     */
    @Test
    public void execute_invalidItemIndexFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);
        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of inventory list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInventory().getItemList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditItemDescriptorBuilder().withName(VALID_NAME_DONUT).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ITEM, DESC_BAGEL);

        // same values -> returns true
        EditItemDescriptor copyDescriptor = new EditItemDescriptor(DESC_BAGEL);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ITEM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ITEM, DESC_BAGEL)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ITEM, DESC_DONUT)));
    }

}
