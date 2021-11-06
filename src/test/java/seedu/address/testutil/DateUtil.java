package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.lesson.Date;

public class DateUtil {
    /**
     * Builds and returns a {@code Date} from a {@code LocalDate}.
     */
    public static Date build(LocalDate date) {
        return new Date(date.format(Date.FORMATTER));
    }
}
