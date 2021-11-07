package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an interview in the address book.
 * Guarantees: immutable; time input is valid as declared in {@link #isValidInterviewTime(String)}
 */
public class Interview implements Comparable<Interview> {
    public static final Interview EMPTY_INTERVIEW = new Interview("");
    public static final String PARSE_FORMAT = "yyyy-M-d, H:m"; //e.g. 2022-09-21, 9:30
    public static final String DISPLAY_FORMAT = "MMM dd yyyy , HH:mm"; //e.g. Sep 21 2022, 09:30
    public static final String EMPTY_TIME = "-";
    public static final String MESSAGE_CONSTRAINTS =
            "Interview time should be on a valid day and follow the format: "
                    + "yyyy-M-d, H:m (Year should be strictly 4-digit while others can be either 1 or 2 digit) "
                    + "E.g. i/2021-09-01, 8:00 or i/2021-9-1,08:00";

    public final String parseTime;

    /**
     * Constructs an {@code Interview}.
     *
     * @param time A valid interview time.
     */
    public Interview(String time) {
        if (time.isEmpty()) {
            this.parseTime = EMPTY_TIME;
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
        if (test.equals(EMPTY_TIME)) {
            return true;
        }
        try {
            DateFormat df = new SimpleDateFormat(PARSE_FORMAT);
            df.setLenient(false);
            df.parse(test);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Returns true if this Interview has passed with respect to the current date and time, and false otherwise.
     */
    public boolean hasInterviewPassed() {
        assert isValidInterviewTime(this.parseTime) : "Not a valid interview time";
        try {
            DateFormat parseFormat = new SimpleDateFormat(PARSE_FORMAT);
            Date interview = parseFormat.parse(parseTime);
            return interview.before(java.util.Calendar.getInstance().getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int compareTo(Interview interview) {
        int result;

        try {
            if (this.isEmptyInterview()) {
                result = -1;
            } else if (interview.isEmptyInterview()) {
                result = 1;
            } else {
                DateFormat parseFormat = new SimpleDateFormat(PARSE_FORMAT);
                Date thisInterview = parseFormat.parse(this.parseTime);
                Date givenInterview = parseFormat.parse(interview.parseTime);
                result = thisInterview.before(givenInterview)
                        ? -1
                        : givenInterview.before(thisInterview)
                        ? 1
                        : 0;
            }

            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert false : "Execution of method should not arrive here";
        return -1;
    }

    /**
     * Returns true if this Interview is an empty interview, and false otherwise.
     */
    public boolean isEmptyInterview() {
        return this.equals(Interview.EMPTY_INTERVIEW);
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
     * Returns the time in display format.
     *
     * @return Formatted time.
     */
    public String displayTime() {
        String formatted = parseTime;
        if (!isEmptyInterview()) {
            try {
                DateFormat parseFormat = new SimpleDateFormat(PARSE_FORMAT);
                Date date = parseFormat.parse(parseTime);
                DateFormat displayFormat = new SimpleDateFormat(DISPLAY_FORMAT);
                formatted = displayFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return formatted;
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return parseTime;
    }

}
