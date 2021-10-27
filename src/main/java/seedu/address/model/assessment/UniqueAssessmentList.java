package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assessment.exceptions.AssessmentNotFoundException;
import seedu.address.model.assessment.exceptions.DuplicateAssessmentException;

/**
 * A list of assessments that enforces uniqueness between its elements and does not allow nulls.
 * An assessment is considered unique by comparing using {@code Assessment#isSameAssessment(Assessment)}. As such,
 * adding and updating of assessments uses Assessment#isSameAssessment(Assessment) for equality so as to ensure
 * that the assessment being added or updated is unique in terms of identity in the UniqueAssessmentList. However,
 * the removal of an assessment uses Assessment#equals(Object) so as to ensure that the assessment with exactly the
 * same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Assessment#isSameAssessment(Assessment)
 */
public class UniqueAssessmentList implements Iterable<Assessment> {

    private final ObservableList<Assessment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assessment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent assessment as the given argument.
     */
    public boolean contains(Assessment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAssessment);
    }

    /**
     * Adds an assessment to the list.
     * The assessment must not already exist in the list.
     */
    public void add(Assessment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssessmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent assessment from the list.
     * The assessment must exist in the list.
     */
    public void remove(Assessment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AssessmentNotFoundException();
        }
    }

    /**
     * Replaces the assessment {@code target} in the list with {@code editedAssessment}.
     * {@code target} must exist in the list.
     * The assessment identity of {@code editedAssessment} must not be the same as another existing assessment
     * in the list.
     */
    public void setAssessment(Assessment target, Assessment editedAssessment) {
        requireAllNonNull(target, editedAssessment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AssessmentNotFoundException();
        }

        if (!target.isSameAssessment(editedAssessment) && contains(editedAssessment)) {
            throw new DuplicateAssessmentException();
        }

        internalList.set(index, editedAssessment);
    }

    public void setAssessment(UniqueAssessmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code assessments}.
     * {@code assessments} must not contain duplicate assessments.
     */
    public void setAssessment(List<Assessment> assessments) {
        requireAllNonNull(assessments);
        if (!assessmentsAreUnique(assessments)) {
            throw new DuplicateAssessmentException();
        }

        internalList.setAll(assessments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Assessment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Assessment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAssessmentList // instanceof handles nulls
                && internalList.equals(((UniqueAssessmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < internalList.size(); i++) {
            builder.append(internalList.get(i)).append("\n");
        }

        return builder.toString();
    }

    /**
     * Returns true if {@code assessments} contains only unique assessments.
     */
    private boolean assessmentsAreUnique(List<Assessment> assessments) {
        for (int i = 0; i < assessments.size() - 1; i++) {
            for (int j = i + 1; j < assessments.size(); j++) {
                if (assessments.get(i).isSameAssessment(assessments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
