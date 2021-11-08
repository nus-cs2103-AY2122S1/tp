package nurseybook.model.task;

import static java.util.Objects.requireNonNull;
import static nurseybook.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents if a task is a recurring task in Nursey Book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRecurrence(String)}
 */
public class Recurrence {
    public static final String MESSAGE_CONSTRAINTS = "Recurrence can only be of type day, week or month and "
            + "is case-insensitive. It cannot be null.";

    /**
     * The first character of the Recurrence must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\bNONE|DAY|WEEK|MONTH\\b";

    private boolean isRecurring;

    private RecurrenceType recurrenceType;

    public enum RecurrenceType {
        NONE, DAY, WEEK, MONTH
    }

    /**
     * Constructs an {@code Recurrence}.
     *
     * @param recurrenceTypeStr A valid recurrence type.
     */
    public Recurrence(String recurrenceTypeStr) {
        requireNonNull(recurrenceTypeStr);
        recurrenceTypeStr = recurrenceTypeStr.toUpperCase();
        checkArgument(isValidRecurrence(recurrenceTypeStr), MESSAGE_CONSTRAINTS);
        this.isRecurring = !recurrenceTypeStr.equals(RecurrenceType.NONE.name());
        this.recurrenceType = RecurrenceType.valueOf(recurrenceTypeStr);
    }

    /**
     * Returns true if a given string is a valid recurrence type.
     */
    public static boolean isValidRecurrence(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    /**
     * Returns the number of days between each recurrence, with respect to RecurrenceType.
     */
    public int getRecurrenceIntervalInDays() {
        if (recurrenceType == RecurrenceType.DAY) {
            return 1;
        } else if (recurrenceType == RecurrenceType.WEEK) {
            return 7;
        } else if (recurrenceType == RecurrenceType.MONTH) {
            return 28;
        } else { //recurrenceType == RecurrenceType.NONE
            return 0;
        }
    }

    @Override
    public String toString() {
        return recurrenceType.name();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Recurrence // instanceof handles nulls
                && isRecurring == ((Recurrence) other).isRecurring) // both booleans are the same
                && recurrenceType.equals(((Recurrence) other).recurrenceType); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(isRecurring, recurrenceType);
    }

}
