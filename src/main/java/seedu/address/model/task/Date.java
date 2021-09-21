package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be non-empty"; //temporary solution

    public final String parsedDate;

    /**
     * Constructs a {@code Date}
     *
     * @param date A valid date for a task.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        parsedDate = date;
    }

    public static boolean isValidDate(String date) {
        return !date.equals("");
    }

    @Override
    public String toString() {
        return parsedDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Date
                && parsedDate.equals(((Date) other).parsedDate));
    }

    @Override
    public int hashCode() {
        return parsedDate.hashCode();
    }
}
