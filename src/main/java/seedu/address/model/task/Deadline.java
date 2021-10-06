package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should be in the format yyyy-MM-dd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline date.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        try {
            this.deadline = LocalDateTime.parse(deadline, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            checkArgument(false, MESSAGE_CONSTRAINTS);
        }
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return deadline.toString();
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
