package seedu.edrecord.model.assignment;

import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;

import seedu.edrecord.model.name.Name;

/**
 * Represents an Assignment under a module in edrecord.
 * Guarantees: immutable, details are present and not null.
 */
public class Assignment {
    private final Name name;
    private final Weightage weightage;
    private final Score maxScore;

    /**
     * Constructs an {@code Assignment}. Every field must be present and not null.
     *
     * @param name      The assignment name, must be unique across the module.
     * @param weightage The assignment weightage in percentage.
     * @param maxScore  The maximum score for the assignment.
     */
    public Assignment(Name name, Weightage weightage, Score maxScore) {
        requireAllNonNull(name, weightage, maxScore);
        this.name = name;
        this.weightage = weightage;
        this.maxScore = maxScore;
    }

    public Name getName() {
        return name;
    }

    public Weightage getWeightage() {
        return weightage;
    }

    public Score getMaxScore() {
        return maxScore;
    }

    /**
     * Returns true if both assignments have the same name, i.e. they are considered to have the same identity.
     * This defines a weaker notion of equality between two assignments.
     *
     * @param otherAssignment The other assignment to compare to.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }
        return otherAssignment != null
                && otherAssignment.getName().equals(getName());
    }

    /**
     * Returns true if this assignment has a higher weightage than the other assignment.
     */
    public boolean hasHigherWeightage(Assignment other) {
        return weightage.compareTo(other.weightage) > 0;
    }

    @Override
    public String toString() {
        return String.format("%s - Weightage: %s, Maximum Score: %s", name, weightage, maxScore);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && name.equals(((Assignment) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
