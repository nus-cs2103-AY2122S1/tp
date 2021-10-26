package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Represents a Recurring Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class RecurringLesson extends Lesson {
    /**
     * Every field must be present and not null.
     *
     * @param date           Date of lesson.
     * @param endDate        End date of the recurrence.
     * @param timeRange      Time range of the lesson.
     * @param subject        Subject of the lesson.
     * @param homework       Homework for the lesson.
     * @param rates          Cost per lesson for the lesson.
     * @param cancelledDates Cancelled dates of the lesson.
     */
    public RecurringLesson(Date date, Date endDate, TimeRange timeRange, Subject subject, Set<Homework> homework,
                           LessonRates rates, Set<Date> cancelledDates) {
        super(date, endDate, timeRange, subject, homework, rates, cancelledDates);
    }

    /**
     * Returns a lesson with the same details but updated cancelled dates.
     *
     * @param updatedCancelledDates A set of cancelled dates of the lesson.
     * @return Lesson with updated cancelled dates.
     */
    @Override
    public Lesson updateCancelledDates(Set<Date> updatedCancelledDates) {
        return new RecurringLesson(getStartDate(), getEndDate(), getTimeRange(),
                getSubject(), getHomework(), getLessonRates(), updatedCancelledDates);
    }

    /**
     * Check if the Lesson object is recurring.
     *
     * @return true.
     */
    @Override
    public boolean isRecurring() {
        return true;
    }

    /**
     * Get the upcoming date of the lesson to display to user.
     *
     * @return The upcoming date on the same day of week if start date
     * has passed or start date if it has yet to pass.
     */
    @Override
    public Date getDisplayDate() {
        Date updatedDate = getStartDate().updateDate(getCancelledDates());
        return getEndDate().compareTo(updatedDate) < 0 // end date earlier than updated date
                ? getEndDate().getPreviousDate(updatedDate.getDayOfWeek())
                : updatedDate;
    }

    private boolean checkOverlapping(Lesson other) {
        requireNonNull(other);

        return !getStartDate().getLocalDate().isAfter(other.getEndDate().getLocalDate()) // <=
            && !other.getStartDate().getLocalDate().isAfter(getEndDate().getLocalDate()); // <=
    }

    /**
     * Returns true if intersects.
     *
     * @param other Other lesson to check.
     * @return
     */
    private boolean checkIntersection(Lesson other) {
        // Non-terminating recurrence
        if (getEndDate().equals(other.getEndDate()) && getEndDate().equals(Date.MAX_DATE)) {
            return true;
        }

        Set<Date> cancelledDates = getCancelledDates();
        Set<Date> otherCancelledDates = other.getCancelledDates();

        // get the intersection
        // https://stackoverflow.com/questions/60785426/
        LocalDate laterStart = Collections.max(Arrays.asList(getLocalDate(), other.getLocalDate()));
        LocalDate earlierEnd = Collections.min(Arrays.asList(getEndDate().getLocalDate(),
                other.getEndDate().getLocalDate()));
        // 3 points, 2 interval
        long numberOfOverlappingDates = ChronoUnit.WEEKS.between(laterStart, earlierEnd.plusDays(1)) + 1;

        Set<Date> cancelledDatesWithinIntersection = cancelledDates.stream().sorted()
                .takeWhile(date -> date.getLocalDate().compareTo(laterStart) >= 0
                    && date.getLocalDate().compareTo(earlierEnd) <= 0)
                .filter(date -> !otherCancelledDates.contains(date))
                .collect(Collectors.toSet());

        Set<Date> otherCancelledDatesWithinIntersection = otherCancelledDates.stream().sorted()
            .takeWhile(date -> date.getLocalDate().compareTo(laterStart) >= 0
                && date.getLocalDate().compareTo(earlierEnd) <= 0)
            .collect(Collectors.toSet());

        long numberOfUniqueCancelledDates = cancelledDatesWithinIntersection.size()
                + otherCancelledDatesWithinIntersection.size();

        return numberOfUniqueCancelledDates < numberOfOverlappingDates;
    }

    /**
     * Returns true if this {@code RecurringLesson} clashes with the given {@code Lesson}.
     *
     * @param otherLesson The other lesson to be compared with.
     * @return True if and only if lessons clash.
     */
    @Override
    public boolean isClashing(Lesson otherLesson) {
        if (otherLesson.isRecurring()) {
            return checkOverlapping(otherLesson) // check if date range overlaps
                    && getDayOfWeek().equals(otherLesson.getDayOfWeek()) // same day
                    && getTimeRange().isClashing(otherLesson.getTimeRange())
                    && checkIntersection(otherLesson); //check if cancelled dates number the size of intersection
        } else {
            return !otherLesson.isCancelled() // other makeup lesson is not cancelled
                    && hasLessonOnDate(otherLesson.getStartDate())
                    && getTimeRange().isClashing(otherLesson.getTimeRange());
        }
    }

    /**
     * Checks if this lesson is cancelled and does not occur on any date.
     *
     * @return false.
     */
    @Override
    public boolean isCancelled() {
        if (getEndDate().equals(Date.MAX_DATE)) {
            // never fully cancelled
            return false;
        }

        // number of weeks between start and end
        long numLessons = ChronoUnit.WEEKS.between(getStartDate().getLocalDate(),
                getEndDate().getLocalDate());

        return numLessons == getCancelledDates().size();
    }

    /**
     * Checks if this lesson occurs on a given date.
     *
     * @param date The lesson date to check.
     * @return True if this lesson occurs on the date.
     */
    @Override
    public boolean hasLessonOnDate(Date date) {
        return date.isOnRecurringDate(getStartDate(), getEndDate()) // other date lies on a recurring lesson date
                && !getCancelledDates().contains(date); // other date is not a cancelled date
    }

}
