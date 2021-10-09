package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.position.Position;

/**
 * Unmodifiable view of a position book
 */
public interface ReadOnlyPositionBook {

    /**
     * Returns an unmodifiable view of the position list.
     * This list will not contain any duplicate positions.
     */
    ObservableList<Position> getPositionList();

}
