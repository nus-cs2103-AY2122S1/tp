package seedu.address.model.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;

/**
 * Tests that a {@code Application}'s {@code Deadline} is coming soon.
 */
public class SoonDeadlinePredicate implements Predicate<Application> {

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private final Index days;

    public SoonDeadlinePredicate(Index days) {
        this.days = days;
    }

    @Override
    public boolean test(Application application) {
        long diff = 0;
        boolean isWithinDays = false;
        boolean isCompleted = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String deadline = application.getDeadline().value;
        try {
            LocalDate applicationDate = LocalDate.parse(deadline, formatter);
            LocalDateTime currentDate = LocalDateTime.now();
            diff = -ChronoUnit.DAYS.between(applicationDate, currentDate);
            isWithinDays = diff >= 0 && diff <= days.getZeroBased();
            isCompleted = application.getCompletion().value.equals("Completed");
        } catch (DateTimeParseException e) {
            logger.info("Invalid deadline format");
        }
        return isWithinDays && !isCompleted;

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SoonDeadlinePredicate); // instanceof handles nulls
    }

}
