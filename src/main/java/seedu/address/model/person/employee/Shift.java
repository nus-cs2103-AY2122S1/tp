package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Employee's shift in the address book.
 */
public class Shift {
    public static final String MESSAGE_CONSTRAINTS = "Salary should be numerical and should be 3 or more digits long"
            + ".";

    /*
     * Between 1-6 digits, optional 2 digits after the dot
     */
    public static final String VALIDATION_REGEX = "\\d{3,}";

    public final LocalDateTime workingShift;
    public final String shiftString;

    /**
     * Constructs an {@code Shift}.
     *
     * @param shift A valid shift.
     */
    public Shift(String shift) {
        requireNonNull(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        checkArgument(isValidShift(shift, formatter));
        shiftString = shift;
        workingShift = LocalDateTime.parse(shift, formatter);
    }

    /**
     * Returns true if a given string is a valid shift.
     */
    public boolean isValidShift(String test, DateTimeFormatter formatter) {
        try {
            formatter.parse(test);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }

    @Override
    public String toString() {
        return '[' + shiftString + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Shift // instanceof handles nulls
                && workingShift.equals(((Shift) other).workingShift)); // state check
    }

    @Override
    public int hashCode() {
        return workingShift.hashCode();
    }
}
