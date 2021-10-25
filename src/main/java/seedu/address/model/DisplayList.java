package seedu.address.model;

import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.item.Item;

import javafx.collections.ListChangeListener;

/**
 *
 */
public class DisplayList {

    private final FilteredList<Item> filteredItems;
    private final ObservableList<Item> displayedItems;
    private ObservableList<Item> source;

    private ListChangeListener<Item> listener;

    public DisplayList(ObservableList<Item> items) {
        source = items;
        displayedItems = FXCollections.observableArrayList(items);
        filteredItems = new FilteredList<Item>(displayedItems);

        listener = change -> displayedItems.setAll(source);
        source.addListener(listener);
    }

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

    public void setPredicate(Predicate<Item> predicate) {
        filteredItems.setPredicate(predicate);
    }

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