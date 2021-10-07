package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Inventory inventory;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredItems;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyInventory inventory, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(inventory, userPrefs);

        logger.fine("Initializing with address book: " + inventory + " and user prefs " + userPrefs);

        this.inventory = new Inventory(inventory);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.inventory.getItemList());
    }

    public ModelManager() {
        this(new Inventory(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getInventoryFilePath() {
        return userPrefs.getInventoryFilePath();
    }

    @Override
    public void setInventoryFilePath(Path inventoryFilePath) {
        requireNonNull(inventoryFilePath);
        userPrefs.setInventoryFilePath(inventoryFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setInventory(ReadOnlyInventory inventory) {
        this.inventory.resetData(inventory);
    }

    @Override
    public ReadOnlyInventory getInventory() {
        return inventory;
    }

    @Override
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return inventory.hasItem(item);
    }

    @Override
    public void deleteItem(Item target) {
        inventory.removeItem(target);
    }

    @Override
    public void addItem(Item item) {
        inventory.addItem(item);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        inventory.setItem(target, editedItem);
    }

    //=========== Filtered Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedInventory}
     */
    @Override
    public ObservableList<Item> getFilteredItemList() {
        return filteredItems;
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return inventory.equals(other.inventory)
                && userPrefs.equals(other.userPrefs)
                && filteredItems.equals(other.filteredItems);
    }

    @Override
    public Item getItemWithName(String name) {
        requireNonNull(name);
        return inventory.getItemWithName(name);
    }

}
