package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.model.display.DisplayMode.DISPLAY_OPEN_ORDER;
import static seedu.address.model.display.DisplayMode.DISPLAY_TRANSACTION_LIST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.BANANA_MUFFIN;
import static seedu.address.testutil.TypicalItems.DONUT;
import static seedu.address.testutil.TypicalTransactions.getTypicalTransactionList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.model.order.Order;
import seedu.address.testutil.InventoryBuilder;
import seedu.address.testutil.ItemDescriptorBuilder;
import seedu.address.testutil.TypicalItems;
import seedu.address.testutil.TypicalOrders;

public class ModelManagerTest {

    @TempDir
    public Path temporaryFolder;

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Inventory(), new Inventory(modelManager.getInventory()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setInventoryFilePath(Paths.get("inventory/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setInventoryFilePath(Paths.get("new/inventory/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setBookKeeping_validBookKeeping_setBookKeeping() {
        BookKeeping bookKeeping = new BookKeeping(3.0, 2.0);
        modelManager.setBookKeeping(bookKeeping);
        assertEquals(bookKeeping, modelManager.getBookKeeping());
    }

    @Test
    public void setInventoryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setInventoryFilePath(null));
    }

    @Test
    public void setInventoryFilePath_validPath_setsInventoryFilePath() {
        Path path = Paths.get("inventory/file/path");
        modelManager.setInventoryFilePath(path);
        assertEquals(path, modelManager.getInventoryFilePath());
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        //Search by item
        assertThrows(NullPointerException.class, () -> modelManager.hasItem((Item) null));
    }

    @Test
    public void hasItem_itemNotInInventory_returnsFalse() {
        assertFalse(modelManager.hasItem(APPLE_PIE));
    }

    @Test
    public void hasItem_itemInInventory_returnsTrue() {
        modelManager.addItem(APPLE_PIE);

        assertTrue(modelManager.hasItem(APPLE_PIE));
    }

    @Test
    public void getItem_itemInInventory_returnsItem() {
        modelManager.addItem(BAGEL);

        // Search by name
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        assertEquals(modelManager.getItems(descriptor), List.of(BAGEL));

        // Search by id
        descriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL).build();
        assertEquals(modelManager.getItems(descriptor), List.of(BAGEL));

        // Search by name and id
        descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        assertEquals(modelManager.getItems(descriptor), List.of(BAGEL));
    }

    @Test
    public void getItem_itemNotInInventory_returnEmptyList() {
        modelManager.addItem(DONUT);

        // Search by name
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        assertEquals(modelManager.getItems(descriptor), List.of());

        // Search by id
        descriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL).build();
        assertEquals(modelManager.getItems(descriptor), List.of());

        // Search by name and id
        descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        assertEquals(modelManager.getItems(descriptor), List.of());
    }

    @Test
    public void getItem_multipleMatches_returnMultiple() {
        modelManager.addItem(DONUT);
        modelManager.addItem(BAGEL);

        ItemDescriptor descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_DONUT).build();
        assertEquals(modelManager.getItems(descriptor), List.of(DONUT, BAGEL));
    }

    @Test
    public void getFilteredItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredDisplayList().remove(0));
    }


    // ========= order related methods tests ==========

    @Test
    public void setOrder_nullOrder_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setOrder(null));
    }

    @Test
    public void setOrder_emptyOrder_orderIsSet() {
        Order order = new Order();
        modelManager.setOrder(order);

        assertTrue(modelManager.hasUnclosedOrder());
    }

    @Test
    public void setOrder_typicalOrder_orderIsSet() {
        modelManager.setOrder(TypicalOrders.getTypicalOrder());

        assertEquals(modelManager.getOrder(), TypicalOrders.getTypicalOrder());
    }

    @Test
    public void transactAndClearOrder_noOrderIsSetYet_throwAssertionError() {
        ModelManager model = new ModelManager();
        assertThrows(AssertionError.class, model::transactAndCloseOrder);
    }

    @Test
    public void transactAndClearOrder_typicalOrder_inventoryIsClear() {
        ModelManager model = new ModelManager();
        model.setInventory(TypicalItems.getTypicalInventory());
        model.setOrder(TypicalOrders.getTypicalOrder());
        model.transactAndClearOrder();

        assertFalse(model.hasUnclosedOrder());
        assertEquals(model.getInventory(), new Inventory());
    }

    @Test
    public void updateFilteredDisplayList_switchDisplayMode_success() {
        modelManager.setInventory(TypicalItems.getTypicalInventory());
        modelManager.setOrder(new Order());

        // Switch display to order mode
        modelManager.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);
        assertEquals(modelManager.getDisplayMode(), DISPLAY_OPEN_ORDER);
        assertTrue(modelManager.getFilteredDisplayList().size() == 0);

        // Switch display back to inventory mode
        modelManager.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);
        assertEquals(modelManager.getDisplayMode(), DISPLAY_INVENTORY);
        assertEquals(modelManager.getFilteredDisplayList(), TypicalItems.getTypicalInventory().getItemList());
    }

    @Test
    public void updateFilteredDisplayList_sameDisplayMode_success() {
        modelManager.setInventory(TypicalItems.getTypicalInventory());
        modelManager.setOrder(new Order());

        // Switch display back to inventory mode
        modelManager.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);
        assertEquals(modelManager.getDisplayMode(), DISPLAY_INVENTORY);
        assertEquals(modelManager.getFilteredDisplayList(), TypicalItems.getTypicalInventory().getItemList());

        // Order mode to order mode
        modelManager.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);
        modelManager.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);
        assertEquals(modelManager.getDisplayMode(), DISPLAY_OPEN_ORDER);
        assertTrue(modelManager.getFilteredDisplayList().size() == 0);
    }

    @Test
    public void updateFilteredItemList_displayingTransactions_throwClassCastException() {
        modelManager.setInventory(TypicalItems.getTypicalInventory());
        modelManager.setOrder(new Order());

        // Attempt to pass item predicate when in transaction mode
        assertThrows(ClassCastException.class, () ->
            modelManager.updateFilteredItemList(DISPLAY_TRANSACTION_LIST, x -> true)
        );
    }

    @Test
    public void openTransactionTest() {
        Inventory inventory = new InventoryBuilder().withItem(APPLE_PIE).withItem(BANANA_MUFFIN).build();
        Inventory differentInventory = new Inventory();
        UserPrefs userPrefs = new UserPrefs();

        ModelManager modelManager = new ModelManager(inventory, userPrefs,
                new TransactionList(getTypicalTransactionList()), new BookKeeping());
        Double totalCost = modelManager.openTransaction("bagelid");
        assertTrue(totalCost == BAGEL.getSalesPrice() * BAGEL.getCount());
    }

    @Test
    public void openTransactionTest_empty() {
        Inventory inventory = new InventoryBuilder().withItem(APPLE_PIE).withItem(BANANA_MUFFIN).build();
        Inventory differentInventory = new Inventory();
        UserPrefs userPrefs = new UserPrefs();

        ModelManager modelManager = new ModelManager(inventory, userPrefs,
                new TransactionList(), new BookKeeping());
        Double totalCost = modelManager.openTransaction("bagelid");
        assertTrue(totalCost == -1.0);
    }

    @Test
    public void equals() {
        Inventory inventory = new InventoryBuilder().withItem(APPLE_PIE).withItem(BANANA_MUFFIN).build();
        Inventory differentInventory = new Inventory();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(inventory, userPrefs, new TransactionList(), new BookKeeping());
        ModelManager modelManagerCopy = new ModelManager(inventory, userPrefs,
                new TransactionList(), new BookKeeping());
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different inventory -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentInventory, userPrefs,
                new TransactionList(), new BookKeeping())));

        // different filteredList -> returns false
        String[] keywords = APPLE_PIE.getName().fullName.split("\\s+");
        modelManager.updateFilteredItemList(DISPLAY_INVENTORY,
                new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(inventory, userPrefs,
                new TransactionList(), new BookKeeping())));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setInventoryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(inventory, differentUserPrefs,
                new TransactionList(), new BookKeeping())));

        // different order -> returns false
        ModelManager other = new ModelManager(inventory, userPrefs,
                new TransactionList(), new BookKeeping());
        other.setOrder(new Order());
        assertFalse(modelManager.equals(other));

        // different display mode / list -> returns false
        modelManager.setOrder(new Order());
        other = new ModelManager(inventory, userPrefs, new TransactionList(), new BookKeeping());
        other.setOrder(new Order());
        other.updateFilteredDisplayList(DISPLAY_OPEN_ORDER, PREDICATE_SHOW_ALL_ITEMS);
        assertFalse(modelManager.equals(other));

    }

}
