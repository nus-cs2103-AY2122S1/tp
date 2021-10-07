package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline {

    public static final String MESSAGE_CONSTRAINTS = "Deadlines should be in the format yyyy-MM-dd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final String value;
    private LocalDate deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline date.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        this.value = deadline;
        try {
            this.deadline = LocalDate.parse(value, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            checkArgument(false, MESSAGE_CONSTRAINTS);
        }
    }

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
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
