package seedu.address.model.module.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a task's deadline in Ailurus
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDeadline(String)}
 */
public class TaskDeadline {
    private static final String DEADLINE_FORMAT = "dd/MM/yyyy HH:mm";

    public static final DateTimeFormatter DEADLINE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEADLINE_FORMAT);

    public static final String MESSAGE_CONSTRAINTS = new StringBuilder()
            .append("Task deadline should be of format: ")
            .append(DEADLINE_FORMAT)
            .append(", and the year should be from 1970 to 3000. ")
            .append("It should only contain numbers, ':', and '/', and it should not be blank.")
            .toString();

    private final LocalDateTime deadline;

    /**
     * Constructs a {@code Task} by using the given {@code date}
     */
    public TaskDeadline(String time) {
        requireNonNull(time);
        checkArgument(isValidTaskDeadline(time), MESSAGE_CONSTRAINTS);
        deadline = LocalDateTime.parse(time, DEADLINE_DATE_TIME_FORMATTER);
    }

    /**
     * Returns true if the given string is a valid deadline.
     */
    public static boolean isValidTaskDeadline(String time) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(time, DEADLINE_DATE_TIME_FORMATTER);
            int year = dateTime.getYear();
            if (year < 1970 || year > 3000) {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return deadline.format(DEADLINE_DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskDeadline that = (TaskDeadline) o;
        return Objects.equals(deadline, that.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deadline);
    }
}
