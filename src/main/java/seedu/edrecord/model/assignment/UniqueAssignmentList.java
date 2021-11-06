package seedu.edrecord.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.edrecord.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.edrecord.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.edrecord.model.name.Name;

/**
 * A list of assignments that enforces uniqueness among its elements and does not allow nulls.
 * An assignment is considered unique by comparing using {@code Assignment#isSameAssignment(Assignment)}.
 * As such, adding and updating of assignments uses Assignment#isSameAssignment(Assignment) for equality
 * so as to ensure that the assignment being added or updated is unique in terms of identity in the
 * UniqueAssignmentList. However, the removal of an assignment uses Assignment#equals(Object) so as
 * to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Assignment#isSameAssignment(Assignment)
 */
public class UniqueAssignmentList implements Iterable<Assignment> {

    private static final Weightage maximumTotalWeightage = new Weightage("100");

    private final ObservableList<Assignment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assignment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an assignment with the same identity as the given argument.
     */
    public boolean contains(Assignment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAssignment);
    }

    /**
     * Returns true if adding the assignment {@code toAdd} will bring
     * the total weightage of all assignments in this list to above 100%.
     */
    public boolean isTotalWeightageExceeded(Assignment toAdd) {
        Weightage addedWeightage = getTotalAddedWeightage(toAdd);
        return addedWeightage.compareTo(maximumTotalWeightage) > 0;
    }

    /**
     * Adds an assignment to the list.
     * The assignment must not already exist in the list.
     * @param toAdd The assignment to be added.
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
     * The identity of {@code editedAssignment} must not be the same as another existing assignment in the list.
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
    public void remove(Assignment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AssignmentNotFoundException();
        }
    }

    /**
     * Replaces the entire contents of this list with the {@code replacement} list.
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
     * Returns an {@code Optional} containing the assignment with the given name, if it exists.
     */
    public Optional<Assignment> searchAssignment(Name name) {
        // Create dummy assignment to search by name
        Assignment a = new Assignment(name, new Weightage("0"), new Score("0"));
        int index = internalUnmodifiableList.indexOf(a);
        if (index == -1) {
            return Optional.empty();
        } else {
            return Optional.of(internalUnmodifiableList.get(index));
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Assignment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
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
     * Returns the total weightage of all assignments in this list, plus
     * the weightage of assignment {@code toAdd}.
     */
    private Weightage getTotalAddedWeightage(Assignment toAdd) {
        Float total = 0f;
        for (Assignment assignment : internalList) {
            total += assignment.getWeightage().weightage;
        }
        total += toAdd.getWeightage().weightage;
        return new Weightage(String.valueOf(total));
    }
}

