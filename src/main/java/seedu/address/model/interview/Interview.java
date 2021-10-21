package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an interview in the address book.
 * Guarantees: immutable; time input is valid as declared in {@link #isValidInterviewTime(String)}
 */
public class Interview {
    public static final Interview EMPTY_INTERVIEW = new Interview("");
    public static final String PARSE_FORMAT = "yyyy-MM-dd, H:mm";
    public static final String MESSAGE_CONSTRAINTS =
            "Interview time should follow the exact format: [" + PARSE_FORMAT + "]. E.g. i/2021-10-22, 8:00";

    public final String parseTime;

    /**
     * Constructs an {@code Interview}.
     *
     * @param time A valid interview time.
     */
    public Interview(String time) {
        if (time.isEmpty()) {
            this.parseTime = "-";
        } else {
            requireNonNull(time);
            checkArgument(isValidInterviewTime(time), MESSAGE_CONSTRAINTS);
            this.parseTime = time;
        }

    }

    /**
     * Returns true if a given string is a valid interview time which follows the timing format.
     */
    public static boolean isValidInterviewTime(String test) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PARSE_FORMAT);
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
