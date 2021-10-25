package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.item.Item;

/**
 * Manages the list of items that is to be displayed by the UI.
 * Linked to a {@code ObservableList} source such that
 * any changes to the source will be propagated to the displayed list.
 *
 * Needed by BogoBogo as the source to the displayed list can be switched at runtime.
 */
public class DisplayList {

    private final FilteredList<Item> filteredItems;
    private final ObservableList<Item> displayedItems;
    private ObservableList<Item> source;

    private ListChangeListener<Item> listener;

    /**
     * Constructs a {@code DisplayList} that uses {@code items} as it source.
     */
    public DisplayList(ObservableList<Item> items) {
        source = items;
        displayedItems = FXCollections.observableArrayList(items);
        filteredItems = new FilteredList<Item>(displayedItems);

        listener = change -> displayedItems.setAll(source);
        source.addListener(listener);
    }


    /**
     * Set {@code items} as the new source.
     */
    public void setItems(ObservableList<Item> items) {
        // Remove listener from old source
        source.removeListener(listener);

        // Change to new source
        source = items;
        displayedItems.setAll(source);

        // Add new listener to new source
        listener = change -> displayedItems.setAll(source);
        source.addListener(listener);
    }

    /**
     * Filters the displayed list with the given {@code predicate}.
     */
    public void setPredicate(Predicate<Item> predicate) {
        filteredItems.setPredicate(predicate);
    }

    /**
     * Returns the filtered displayed list.
     */
    public FilteredList<Item> getFilteredItemList() {
        return filteredItems;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof DisplayList)) {
            return false;
        }

        // state check
        DisplayList other = (DisplayList) obj;
        return source.equals(other.source)
                && displayedItems.equals(other.displayedItems)
                && filteredItems.equals(other.filteredItems);
    }
}
