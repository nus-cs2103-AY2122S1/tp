package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.programmer.model.student.exceptions.DuplicateLabException;
import seedu.programmer.model.student.exceptions.LabNotFoundException;

/**
 * A list of labs that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Lab#isSamestudent(student)}. As such, adding and
 * updating of labs uses student#isSamestudent(student) for equality so as to ensure that the student being added
 * or updated is unique in terms of identity in the UniqueLabList. However, the removal of a student uses
 * student#equals(Object) so as to ensure that the student with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Lab#equals(Object)
 */
public class UniqueLabList implements Iterable<Lab> {

    private final ObservableList<Lab> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lab> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Lab toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a student to the list and the list is sorted each time.
     * The student must not already exist in the list.
     */
    public void add(Lab toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLabException();
        }
        internalList.add(toAdd);
    }

    /**
     * Get the student at the given index.
     * The student must not already exist in the list.
     */
    public Lab get(int index) {
        return internalList.get(index);
    }

    /**
     * Get a list of all the labs.
     */
    public ObservableList<Lab> getList() {
        return internalList;
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Lab toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LabNotFoundException();
        }
    }

    public void setLabs(UniqueLabList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code labs}.
     * {@code labs} must not contain duplicate labs.
     */
    public void setLabs(List<Lab> labs) {
        requireAllNonNull(labs);
        if (!labsAreUnique(labs)) {
            throw new DuplicateLabException();
        }

        internalList.setAll(labs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lab> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Lab> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLabList // instanceof handles nulls
                        && internalList.equals(((UniqueLabList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code labs} contains only unique labs.
     */
    private boolean labsAreUnique(List<Lab> labs) {
        for (int i = 0; i < labs.size() - 1; i++) {
            for (int j = i + 1; j < labs.size(); j++) {
                if (labs.get(i).equals(labs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
