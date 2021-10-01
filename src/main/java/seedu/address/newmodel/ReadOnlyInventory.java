package seedu.address.newmodel;

import javafx.collections.ObservableList;
import seedu.address.newmodel.item.Item;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyInventory {

    /**
     * Returns an unmodifiable view of the items list.
     * This list will not contain any duplicate items.
     */
    ObservableList<Item> getItemList();

}
