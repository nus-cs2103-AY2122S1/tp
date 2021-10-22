package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of groups that enforces uniqueness between its elements and does not allow nulls.
 * A group is considered unique by comparing using {@code Group#isSameGroup(Group)}. As such, adding and
 * updating of groups uses Group#isSameGroup(Group) for equality so as to ensure that the group being added
 * or updated is unique in terms of identity in the UniqueGroupList. However, the removal of a group uses
 * Group#equals(Object) so as to ensure that the group with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see TaskHistory
 */
public class TaskHistoryList implements Iterable<TaskHistory> {

    private final ObservableList<TaskHistory> internalList = FXCollections.observableArrayList();
    private final ObservableList<TaskHistory> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private final int listSize = 5;

    /**
     * Adds a group to the list.
     * The group must not already exist in the list.
     */
    public void add(TaskHistory toAdd) {
        requireNonNull(toAdd);
        if (internalList.size() >= listSize) {
            internalList.remove(0);
        }
        internalList.add(toAdd);
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TaskHistory> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TaskHistory> iterator() {
        return internalList.iterator();
    }

}
