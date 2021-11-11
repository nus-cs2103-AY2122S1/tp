package seedu.address.model.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;

/**
 * Tests that a {@code Application}'s {@code InterviewDateAndTime} is coming soon.
 */
public class InterviewDateAndTimePredicate implements Predicate<Application> {

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);
    private final Index days;

    public InterviewDateAndTimePredicate(Index days) {
        this.days = days;
    }

    @Override
    public boolean test(Application application) {
        long diff = 0;
        boolean isWithinDays = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Set<InterviewDateAndTime> set = application.getInterviewDateAndTime();
        Iterator<InterviewDateAndTime> iterator = set.iterator();
        if (set.size() == 0) {
            return false;
        }
        while (iterator.hasNext()) {
            try {
                LocalDate applicationDate = LocalDate.parse(
                        iterator.next().value.substring(0, 10), formatter);
                LocalDateTime currentDate = LocalDateTime.now();
                diff = -ChronoUnit.DAYS.between(applicationDate, currentDate);
                isWithinDays = diff >= 0 && diff <= days.getZeroBased();
            } catch (DateTimeParseException e) {
                logger.info("Invalid interview date and time format");
            }
            return isWithinDays;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewDateAndTimePredicate); // instanceof handles nulls
    }

}
