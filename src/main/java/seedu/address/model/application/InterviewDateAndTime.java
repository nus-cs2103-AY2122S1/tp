package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Comparator;

/**
 * Represents the interview date and time of an application in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewDateAndTime(String)}
 */
public class InterviewDateAndTime {
    public static final String MESSAGE_CONSTRAINTS = "The 'interview date and time' must be in YYYY-MM-DD HHmm format.";
    public static final String MESSAGE_INVALIDDATEANDTIME = "The day-month-hour-minute combination is invalid.";
    public static final String VALIDATION_REGEX =
            "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])\\ ([0-9][0-9][0-9][0-9])$";
    public final String value;
    public final LocalDateTime dateAndTime;

    /**
     * Constructs a {@code InterviewDateAndTime}.
     *
     * @param interviewDateAndTime A valid set of date and time for multiple interviews.
     */
    public InterviewDateAndTime(String interviewDateAndTime) {
        requireNonNull(interviewDateAndTime);
        checkArgument(isValidInterviewDateAndTime(interviewDateAndTime), MESSAGE_CONSTRAINTS);
        this.dateAndTime = LocalDateTime.parse(interviewDateAndTime, DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
                .withResolverStyle(ResolverStyle.STRICT));
        value = interviewDateAndTime;
    }

    /**
     * Returns the month, date,
     * and year pattern of the date and time.
     *
     * @return The formatted String.
     */
    public String toFormattedString() {
        return dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }

    /**
     * Returns the LocalDateTime object.
     *
     * @return The LocalDateTime object.
     */
    public LocalDateTime getDate() {
        return this.dateAndTime;
    }

    /**
     * Returns true if a given string is in YYYY-MM-DD HHmm format
     * Only checks if the YYYY is a 4-bit digit, MM is from 1 to 12, DD is from 1 to 31, HHmm is a 4-bit digit,
     * which doesn't account for invalid day-month-hour-minute combination
     */
    public static boolean isValidInterviewDateAndTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the given YYYY-MM-DD HHmm format actually forms a valid combination
     * @param test a string in YYYY-MM-DD HHmm format
     * @return whether the given YYYY-MM-DD HHmm format actually forms a valid combination
     */
    public static boolean isValidDateAndTime(String test) {
        try {
            LocalDateTime.parse(test, DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns a Comparator object that compares two applications by their interview date and time.
     * The application with an interview that comes first is taken to be greater.
     *
     * @return Comparator object that compares applications by their interview date and time.
     */
    public static Comparator<Application> getComparator() {
        //Solution below adapted from AY2122S1-CS2103T-T15-3
        return (application, otherApplication) -> {
            InterviewDateAndTime interviewDateAndTime = application.getInterviewDateAndTime()
                    .stream().min(Comparator.comparing(dt -> dt.value)).orElse(null);
            InterviewDateAndTime otherInterviewDateAndTime = otherApplication.getInterviewDateAndTime()
                    .stream().min(Comparator.comparing(dt -> dt.value)).orElse(null);

            if (interviewDateAndTime == null && otherInterviewDateAndTime == null) {
                return 0;
            } else if (interviewDateAndTime == null) {
                return 1;
            } else if (otherInterviewDateAndTime == null) {
                return -1;
            }
            return interviewDateAndTime.dateAndTime.compareTo(otherInterviewDateAndTime.dateAndTime);
        };
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + value + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewDateAndTime // instanceof handles nulls
                && value.equals(((InterviewDateAndTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
