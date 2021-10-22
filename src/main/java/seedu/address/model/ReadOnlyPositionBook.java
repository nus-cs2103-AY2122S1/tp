package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * Unmodifiable view of a position book
 */
public interface ReadOnlyPositionBook {

    /**
     * Returns an unmodifiable view of the position list.
     * This list will not contain any duplicate positions.
     */
    ObservableList<Position> getPositionList();

    /**
     * Searches for a position by its {@code title}.
     */
    public Position getPositionByTitle(Title title);

}
