package seedu.edrecord.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.edrecord.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.edrecord.model.assignment.exceptions.DuplicateAssignmentException;

/**
 * A list of assignments that enforces uniqueness among its elements and does not allow nulls.
 * An assignment is considered unique by comparing using {@code Assignment#isSameAssignment(Assignment)}.
 * As such, adding and updating of assignments uses Assignment#isSameAssignment(Assignment) for equality
 * so as to ensure that the assignment being added or updated is unique in terms of identity in the
 * UniqueAssignmentList. However, the removal of an assignment uses Assignment#equals(Object) so as
 * to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Assignment#isSameAssignment(Assignment)
 */
public class UniqueAssignmentList implements Iterable<Assignment> {
    private static final float MAXIMUM_TOTAL_WEIGHTAGE = 100f;

    private final ObservableList<Assignment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assignment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private int assignmentCounter = 1;

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
        Float addedWeightage = getTotalAddedWeightage(toAdd);
        return addedWeightage > MAXIMUM_TOTAL_WEIGHTAGE;
    }

    /**
     * Adds an assignment to the list.
     * The assignment must not already exist in the list.
     *
     * @param toAdd The assignment to be added.
     */
    public void add(Assignment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssignmentException();
        }
        assignmentCounter++;
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
     * Returns true if there is another assignment with the same name in the list.
     */
    public boolean hasSameName(Assignment assignment) {
        requireNonNull(assignment);
        return internalList.stream().anyMatch(asg -> !(asg.equals(assignment)) && asg.isSameAssignment(assignment));
    }

    /**
     * Returns true if there is another assignment with the same ID in the list.
     */
    public boolean hasSameId(Assignment assignment) {
        requireNonNull(assignment);
        return internalList.stream().anyMatch(asg -> !(asg.equals(assignment))
                && asg.getId().equals(assignment.getId()));
    }

    /**
     * Returns the current counter for this module's assignment ID.
     */
    public int getAssignmentCounter() {
        return assignmentCounter;
    }

    /**
     * Sets the counter for this module's assignment ID to the given value.
     */
    public void setAssignmentCounter(int i) {
        assignmentCounter = i;
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
    private Float getTotalAddedWeightage(Assignment toAdd) {
        Float total = 0f;
        for (Assignment assignment : internalList) {
            total += assignment.getWeightage().weightage;
        }
        total += toAdd.getWeightage().weightage;
        return total;
    }
}

