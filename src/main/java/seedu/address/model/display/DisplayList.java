package seedu.address.model.display;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Manages the list of displayables that is to be displayed by the UI.
 * Linked to a {@code ObservableList} source such that
 * any changes to the source will be propagated to the displayed list.
 *
 * Needed by BogoBogo as the source to the displayed list can be switched at runtime.
 */
public class DisplayList {

    private final FilteredList<Displayable> filtered;
    private final ObservableList<Displayable> displayed;
    private ObservableList<? extends Displayable> source;

    private ListChangeListener<Displayable> listener;

    /**
     * Constructs a {@code DisplayList} that uses {@code toDisplay} as it source.
     */
    public DisplayList(ObservableList<? extends Displayable> toDisplay) {
        source = toDisplay;
        displayed = FXCollections.observableArrayList(toDisplay);
        filtered = new FilteredList<Displayable>(displayed);

        listener = change -> displayed.setAll(source);
        source.addListener(listener);
    }


    /**
     * Set {@code toDisplay} as the new source.
     */
    public void setItems(ObservableList<? extends Displayable> toDisplay) {

        filtered.setPredicate(x -> true);

        // Remove listener from old source
        source.removeListener(listener);

        // Change to new source
        source = toDisplay;
        displayed.setAll(source);

        // Add new listener to new source
        listener = change -> displayed.setAll(source);
        source.addListener(listener);
    }

    /**
     * Set {@code toDisplay} as the new source.
     */
    public void setItems(List<? extends Displayable> toDisplay) {
        setItems(FXCollections.observableArrayList(toDisplay));
    }

    /**
     * Filters the with the given {@code Predicate}.
     *
     * @throws ClassCastException if the predicate incorrectly casts the displayed elements.
     */
    public void setPredicate(Predicate<Displayable> predicate) throws ClassCastException {
        filtered.setPredicate(predicate);
    }

    /**
     * Returns the filtered list of displayables to be displayed.
     */
    public FilteredList<Displayable> getFilteredDisplayList() {
        return filtered;
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
                && displayed.equals(other.displayed)
                && filtered.equals(other.filtered);
    }
}
