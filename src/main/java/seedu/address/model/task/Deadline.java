package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task's deadline.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should be in the format YYYY-MM-DD, such as 2007-12-03";
    public final LocalDate deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline string format (YYYY-MM-DD).
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns true if a given string is a valid date format.
     *
     * @param test The description to be tested.
     */
    public static boolean isValidDeadline(String test) {
        try {
            LocalDate testDate = LocalDate.parse(test);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
