package seedu.address.model.table;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.table.exception.TableNotFoundException;

/**
 * Represents a list of tables.
 * Supports a minimal set of list operations.
 */
public class TableList implements Iterable<Table> {
    private final ObservableList<Table> internalList = FXCollections.observableArrayList();
    private final ObservableList<Table> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent table as the given argument
     */
    public boolean contains(Table toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a table to the list
     */
    public void add(Table toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the table {@code target} in the list with {@code editedTable}.
     * {@code target} must exist in the list.
     */
    public void setTable(Table target, Table editedTable) {
        requireAllNonNull(target, editedTable);
        int index = internalList.indexOf(target);
        if (index < 0) {
            throw new TableNotFoundException();
        }
        internalList.set(index, editedTable);
    }

    /**
     * Removes the equivalent table from the list.
     * The table must exist in the list.
     */
    public void remove(Table toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TableNotFoundException();
        }
    }

    public void setTables(TableList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tables}
     */
    public void setTables(List<Table> tables) {
        requireNonNull(tables);
        internalList.setAll(tables);
    }

    /**
     * Return the backing list as an unmodifiable {@code ObservableList}
     */
    public ObservableList<Table> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public int getNumberOfTables() {
        return internalList.size();
    }

    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public Iterator<Table> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object o) {
        return o == this
                || (o instanceof TableList
                && internalList.equals(((TableList) o).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
