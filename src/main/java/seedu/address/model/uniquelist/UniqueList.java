package seedu.address.model.uniquelist;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.uniquelist.exceptions.DuplicateItemException;
import seedu.address.model.uniquelist.exceptions.ItemNotFoundException;

/**
 * A list of items that enforces uniqueness between them and does not allow nulls.
 * An item is considered unique by comparing using {@code T#isSame(T)}. As such, adding and updating of
 * items uses T#isSame(T) for equality so as to ensure that the item being added or updated is
 * unique in terms of identity in the UniqueList. However, the removal of an item uses T#equals(Object) so
 * as to ensure that the item with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 */
public class UniqueList<T extends UniqueListItem> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Adds the item to the list.
     * The item must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateItemException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedItem} must not be the same as another existing item in the list.
     */
    public void setItem(T targetItem, T editedItem) {
        requireAllNonNull(targetItem, editedItem);

        int index = internalList.indexOf(targetItem);
        if (index == -1) {
            throw new ItemNotFoundException();
        }

        if (!targetItem.isSame(editedItem) && contains(editedItem)) {
            throw new DuplicateItemException();
        }

        internalList.set(index, editedItem);
    }

    /**
     * Removes the equivalent item from the list.
     * The item must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItemNotFoundException();
        }
    }

    public void setItems(UniqueList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItems(List<T> items) {
        requireAllNonNull(items);
        if (!itemsAreUnique(items)) {
            throw new DuplicateItemException();
        }

        internalList.setAll(items);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the backing list as a modifiable {@code ObservableList}
     */
    public ObservableList<T> asModifiableObservableList() {
        return internalList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueList // instanceof handles nulls
                && internalList.equals(((UniqueList) other).internalList));
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalList, internalUnmodifiableList);
    }

    /**
     * Method for debugging
     */
    public void printItemsInList() {
        for (T item : internalList) {
            System.out.println(item.toString());
        }
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean itemsAreUnique(List<T> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).isSame(items.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


}
