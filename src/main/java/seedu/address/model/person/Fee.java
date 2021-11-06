package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Calculator.getStudentTotalFees;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Set;

import seedu.address.model.lesson.Lesson;

/**
 * Represents a Person's outstanding fees in TAB.
 * Calculated by summing up all outstanding fees from lesson.
 */
public class Fee {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private final BigDecimal value;

    /**
     * Constructs an outstanding {@code Fee}.
     *
     * @param lessons A set of lessons to calculate current outstanding fees.
     */
    public Fee(Set<Lesson> lessons) {
        requireNonNull(lessons);
        value = getStudentTotalFees(lessons);
    }

    @Override
    public String toString() {
        return "$" + df.format(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Fee // instanceof handles nulls
                && value.equals(((Fee) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
