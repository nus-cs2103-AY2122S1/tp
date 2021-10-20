package seedu.address.model.interview;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an interview in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidInterviewTime(String)}
 */
public class Interview {

    public static final String MESSAGE_CONSTRAINTS = "Interview time should follow the format: yyyy-MM-dd, HH:mm";

    public static final String parseFormat = "yyyy-MM-dd, HH:mm";

    public final String parseTime;

    //public final LocalDate ld;

    /**
     * Constructs an {@code Interview}.
     *
     * @param time A valid interview time.
     */
    public Interview(String time) {
        requireNonNull(time);
        checkArgument(isValidInterviewTime(time), MESSAGE_CONSTRAINTS);
        this.parseTime = time;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidInterviewTime(String test) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parseFormat);
            LocalDate.parse(test, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Interview // instanceof handles nulls
                && parseTime.equals(((Interview) other).parseTime)); // state check
    }

    @Override
    public int hashCode() {
        return parseTime.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return parseTime;
    }

}
