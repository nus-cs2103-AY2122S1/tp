package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class TaskDate {

    public static final String MESSAGE_CONSTRAINTS = "Deadlines should be in the format yyyy-MM-dd."
            + " " + "Do ensure you input a valid date.";
    private static final String dateFormat = "uuuu-MM-dd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter
                    .ofPattern(dateFormat)
                    .withResolverStyle(ResolverStyle.STRICT);

    private final String value;
    private LocalDate deadline;

    /**
     * Constructs a {@code TaskDate}.
     *
     * @param deadline A valid deadline date.
     */
    public TaskDate(String deadline) {
        requireNonNull(deadline);
        this.value = deadline;
        try {
            this.deadline = LocalDate.parse(value, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            checkArgument(false, MESSAGE_CONSTRAINTS);
        } catch (DateTimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Constructs a {@code TaskDate}.
     *
     * @param date A valid deadline date.
     */
    public TaskDate(LocalDate date) {
        requireNonNull(date);
        this.value = date.format(DATE_TIME_FORMATTER);
        this.deadline = date;
    }

    /**
     * Returns true if a given string is a valid deadline.
     *
     * @param test The given string.
     * @return The validity of the string as a deadline.
     */
    public static boolean isValidDeadline(String test) {
        try {
            LocalDate.parse(test, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDate // instanceof handles nulls
                && deadline.equals(((TaskDate) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
