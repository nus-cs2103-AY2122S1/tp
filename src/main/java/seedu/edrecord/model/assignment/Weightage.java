package seedu.edrecord.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.AppUtil.checkArgument;

/**
 * Represents an Assignment's weightage in edrecord.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeightage(String)}
 */
public class Weightage implements Comparable<Weightage> {
    public static final String MESSAGE_CONSTRAINTS =
            "Assignment weightage should be a non-negative integer or float (max 2 decimal numbers) from 0 to 100";

    public final Float weightage;

    /**
     * Constructs a {@code Weightage} object.
     * @param weightage The weightage in percentage.
     */
    public Weightage(String weightage) {
        requireNonNull(weightage);
        checkArgument(isValidWeightage(weightage), MESSAGE_CONSTRAINTS);
        this.weightage = Float.valueOf(weightage);
    }

    /**
     * Returns true if a given string is a valid weightage.
     * @param test The string to test.
     */
    public static boolean isValidWeightage(String test) {
        try {
            float f = Float.parseFloat(test);
            return (f >= 0) && (f <= 100);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f%%", weightage);
    }

    @Override
    public int compareTo(Weightage other) {
        return this.weightage.compareTo(other.weightage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weightage // instanceof handles nulls
                && weightage.equals(((Weightage) other).weightage)); // state check
    }

    @Override
    public int hashCode() {
        return weightage.hashCode();
    }

}
