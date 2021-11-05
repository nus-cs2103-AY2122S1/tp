package seedu.edrecord.model.assignment;

import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;

import seedu.edrecord.model.name.Name;

/**
 * Represents an Assignment under a module in EdRecord.
 * Guarantees: immutable, details are present and not null.
 */
public class Assignment {
    private final Name name;
    private final Weightage weightage;
    private final Score maxScore;
    private final Integer id;

    /**
     * Constructs an {@code Assignment}. Every field must be present and not null.
     *
     * @param name      The assignment name, must be unique across the module.
     * @param weightage The assignment weightage in percentage.
     * @param maxScore  The maximum score for the assignment.
     * @param id        The unique ID of the assignment.
     */
    public Assignment(Name name, Weightage weightage, Score maxScore, int id) {
        requireAllNonNull(name, weightage, maxScore);
        this.name = name;
        this.weightage = weightage;
        this.maxScore = maxScore;
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    /**
     * Returns true if both assignments have the same name, i.e. they are considered to have the same identity.
     * This defines a weaker notion of equality between two assignments.
     *
     * @param otherAssignment The other assignment to compare to.
     */
    public boolean isSameName(Assignment otherAssignment) {
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
                && id.equals(((Assignment) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
