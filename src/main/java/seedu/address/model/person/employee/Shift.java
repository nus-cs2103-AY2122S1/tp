package seedu.address.model.person.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.ParserUtil.DATE_TIME_FORMATTERS;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Employee's shift in the address book.
 */
public class Shift {
    public static final String MESSAGE_CONSTRAINTS = "Working shifts should be in the format yyyy-mm-dd HHmm"
            + ".";

    public static final String VALIDATION_REGEX =
            "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9]) (2[0-3]|[01]?[0-9])([0-5]?[0-9])$";

    private static DateTimeFormatter chosenFormat = null;
    public final LocalDateTime workingShift;
    public final String shiftString;

    /**
     * Constructs an {@code Shift}.
     *
     * @param shift A valid shift.
     */
    public Shift(String shift) {
        requireNonNull(shift);
        checkArgument(isValidShift(shift), MESSAGE_CONSTRAINTS);
        workingShift = LocalDateTime.parse(shift, chosenFormat);
        shiftString = this.workingShift.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns true if a given string is a valid shift.
     */
    public static boolean isValidShift(String test) {
        LocalDateTime temp = null;
        for (DateTimeFormatter dateTimeFormatter : DATE_TIME_FORMATTERS) {
            try {
                temp = LocalDateTime.parse(test, dateTimeFormatter);
                chosenFormat = dateTimeFormatter;
            } catch (DateTimeException e) {
                //do nothing
            }
        }
        return temp != null;
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
