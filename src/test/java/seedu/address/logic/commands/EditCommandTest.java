package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showItemAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_OPEN_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;
import static seedu.address.testutil.TypicalItems.getTypicalItems;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.BookKeeping;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TransactionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.order.Order;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.ItemDescriptorBuilder;
import seedu.address.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs(),
            new TransactionList(), new BookKeeping());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Item editedItem = new ItemBuilder().withCount(getTypicalItems().get(0).getCount().toString()).build();
        ItemDescriptor descriptor = new ItemDescriptorBuilder(editedItem).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_COUNT_CNT_BE_EDITED, editedItem);

        Model expectedModel = new ModelManager(new Inventory(model.getInventory()), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.setItem((Item) model.getFilteredDisplayList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastItem = Index.fromOneBased(model.getFilteredDisplayList().size());
        Item lastItem = (Item) model.getFilteredDisplayList().get(indexLastItem.getZeroBased());

        ItemBuilder itemInList = new ItemBuilder(lastItem);
        Item editedItem = itemInList.withId(VALID_ID_DONUT).build();

        ItemDescriptor descriptor = new ItemDescriptorBuilder().withId(VALID_ID_DONUT).build();
        EditCommand editCommand = new EditCommand(indexLastItem, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new Inventory(model.getInventory()), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.setItem(lastItem, editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, new ItemDescriptor());
        Item editedItem = (Item) model.getFilteredDisplayList().get(INDEX_FIRST_ITEM.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new Inventory(model.getInventory()), new UserPrefs(),
                new TransactionList(), new BookKeeping());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);

        Item itemInFilteredList = (Item) model.getFilteredDisplayList().get(INDEX_FIRST_ITEM.getZeroBased());
        Item editedItem = new ItemBuilder(itemInFilteredList).withName(VALID_NAME_DONUT).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM,
                new ItemDescriptorBuilder().withName(VALID_NAME_DONUT).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedItem);

        Model expectedModel = new ModelManager(new Inventory(model.getInventory()), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.setItem((Item) model.getFilteredDisplayList().get(0), editedItem);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateItemUnfilteredList_failure() {
        Item firstItem = (Item) model.getFilteredDisplayList().get(INDEX_FIRST_ITEM.getZeroBased());
        ItemDescriptor descriptor = new ItemDescriptorBuilder(firstItem).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ITEM, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_duplicateItemFilteredList_failure() {
        showItemAtIndex(model, INDEX_FIRST_ITEM);

        // edit item in filtered list into a duplicate in inventory
        Item itemInList = model.getInventory().getItemList().get(INDEX_SECOND_ITEM.getZeroBased());

        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM,
                new ItemDescriptorBuilder(itemInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_duplicateIdUnfilteredList_failure() {
        Item secondItem = model.getInventory().getItemList().get(INDEX_SECOND_ITEM.getZeroBased());
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withId(secondItem.getId()).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ID);
    }

    @Test
    public void execute_duplicateNameUnfilteredList_failure() {
        Item secondItem = model.getInventory().getItemList().get(INDEX_SECOND_ITEM.getZeroBased());
        ItemDescriptor descriptor = new ItemDescriptorBuilder().setName(secondItem.getName()).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_THIRD_ITEM, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_NAME);
    }

    @Test
    public void execute_invalidItemIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDisplayList().size() + 1);
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withName(VALID_NAME_DONUT).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_editCount_failure() {
        Item firstItem = model.getInventory().getItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        ItemDescriptor descriptor = new ItemDescriptorBuilder()
                .withCount(firstItem.getCount() + 1).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_ITEM, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_COUNT_CNT_BE_EDITED);
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
                new ItemDescriptorBuilder().withName(VALID_NAME_DONUT).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_displayOrderMode_failure() {
        Item editedItem = new ItemBuilder().build();
        ItemDescriptor descriptor = new ItemDescriptorBuilder(editedItem).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        model.setOrder(new Order());
        model.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);
        String expectedMessage = EditCommand.MESSAGE_INVENTORY_NOT_DISPLAYED;

        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ITEM, DESC_BAGEL);

        // same values -> returns true
        ItemDescriptor copyDescriptor = new ItemDescriptor(DESC_BAGEL);
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
