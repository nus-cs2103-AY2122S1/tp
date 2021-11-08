package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assignment.exceptions.AssignmentAlreadyCompletedException;
import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;

/**
 * A list of assignments that enforces uniqueness between its elements and does not allow nulls.
 * An assignment is considered unique by comparing using {@code Assignment#isSameAssignment(Assignment)}.
 * As such, adding and updating of assignments uses Assignment#isSameAssignment(Assignment) for equality to ensure
 * that the assignment being added or updated is unique in terms of identity in the UniqueAssignmentList.
 * However, the removal of an assignment uses Assignment#equals(Object)
 * to ensure that the assignment with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Assignment#isSameAssignment(Assignment)
 */
public class UniqueAssignmentList implements Iterable<Assignment> {

    private final ObservableList<Assignment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assignment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns the assignment list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Assignment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns true if the list contains an equivalent assignment as the given argument.
     */
    public boolean contains(Assignment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAssignment);
    }

    /**
     * Adds an assignment to the list.
     * The person must not already exist in the list.
     */
    public void add(Assignment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssignmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the assignment {@code target} in the list with {@code editedAssignment}.
     * {@code target} must exist in the list.
     * The assignment identity of {@code editedAssignment}
     * must not be the same as another existing assignment in the list.
     */
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        requireAllNonNull(target, editedAssignment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AssignmentNotFoundException();
        }

        if (!target.isSameAssignment(editedAssignment) && contains(editedAssignment)) {
            throw new DuplicateAssignmentException();
        }

        internalList.set(index, editedAssignment);
    }

    /**
     * Removes the equivalent assignment from the list.
     * The assignment must exist in the list.
     */
    public void delete(Assignment toDelete) {
        requireNonNull(toDelete);
        if (!internalList.remove(toDelete)) {
            throw new AssignmentNotFoundException();
        }
    }

    /**
     * Marks the equivalent assignment as done from the list.
     * The assignment must exist in the list.
     */
    public void done(Assignment toDone) {
        requireNonNull(toDone);
        if (!internalList.contains(toDone)) {
            throw new AssignmentNotFoundException();
        }

        if (toDone.isCompleted()) {
            throw new AssignmentAlreadyCompletedException();
        }
        setAssignment(toDone, new Assignment(toDone.getDescription(), toDone.getDueDate(),
                Status.createCompletedStatus()));
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     * {@code replacement} must not contain duplicate assignments.
     */
    public void setAssignments(UniqueAssignmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code assignments}.
     * {@code assignments} must not contain duplicate assignments.
     */
    public void setAssignments(List<Assignment> assignments) {
        requireAllNonNull(assignments);
        if (!assignmentsAreUnique(assignments)) {
            throw new DuplicateAssignmentException();
        }

        internalList.setAll(assignments);
    }

    /**
     * Removes all assignments within the UniqueAssignmentList
     */
    public void clearAllAssignments() {
        internalList.clear();
    }

    @Override
    public Iterator<Assignment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAssignmentList // instanceof handles nulls
                && internalList.equals(((UniqueAssignmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code assignments} contains only unique assignments.
     */
    private boolean assignmentsAreUnique(List<Assignment> assignments) {
        for (int i = 0; i < assignments.size() - 1; i++) {
            for (int j = i + 1; j < assignments.size(); j++) {
                if (assignments.get(i).isSameAssignment(assignments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if {@code internalList} has no assignments
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     *
     */
    public Assignment get(int index) {
        return asUnmodifiableObservableList().get(index);
    }

    public Assignment getAssignment(Description description) {
        requireNonNull(description);
        boolean isPresent = false;
        Assignment assignment = null;
        for (Assignment currentAssignment : internalList) {
            if (currentAssignment.getDescription().equals(description)) {
                isPresent = true;
                assignment = currentAssignment;
                break;
            }
        }

        if (isPresent) {
            requireNonNull(assignment);
            return assignment;
        } else {
            throw new AssignmentNotFoundException();
        }

    }

    /**
     * Sorts the assignment list by status.
     * Assignments with the same status are then sorted by date
     */
    public void sort() {
        Comparator<Assignment> byStatusAndDate = Comparator.naturalOrder();
        this.internalList.sort(byStatusAndDate);
    }

    /**
     * Returns the copy of unique assignment list.
     */
    public UniqueAssignmentList copyUniqueAssignmentList() {
        UniqueAssignmentList uniqueAssignmentListCopy = new UniqueAssignmentList();
        for (Assignment assignment: this.internalList) {
            uniqueAssignmentListCopy.add(assignment.copyAssignment());
        }
        return uniqueAssignmentListCopy;
    }
}
