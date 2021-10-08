package seedu.address.model.lesson;

import java.util.Set;

/**
 * Represents a Recurring Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class RecurringLesson extends Lesson {

    private final Date startDate;

    /**
     * Every field must be present and not null.
     *
     * @param date Date of lesson.
     * @param timeRange Time range of the lesson.
     * @param subject Subject of the lesson.
     * @param homework Homework for the lesson.
     */
    public RecurringLesson(Date date, TimeRange timeRange,
                           Subject subject, Set<Homework> homework) {
        super(date, timeRange, subject, homework);
        startDate = date;
    }

    /**
     * Every field must be present and not null.
     *
     * @param startDate Start date of this recurring lesson.
     * @param date Date of lesson.
     * @param timeRange Time range of the lesson.
     * @param subject Subject of the lesson.
     * @param homework Homework for the lesson.
     */
    public RecurringLesson(Date startDate, Date date, TimeRange timeRange,
                           Subject subject, Set<Homework> homework) {
        super(date, timeRange, subject, homework);
        this.startDate = startDate;
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
     * Updates the date of the recurring lesson to the latest
     * date on the same day of the week that has yet to be passed.
     *
     * @return {@code RecurringLesson} with the updated date.
     */
    @Override
    public Lesson updateDate() {
        Date newDate = getDate().updateDate();

        if (newDate.compareTo(getDate()) <= 0) {
            return this;
        }

        // Update the date
        return new RecurringLesson(getStartDate(), newDate, getTimeRange(),
            getSubject(), getHomework());
    }

    /**
     * Return the original starting date of the recurring lesson.
     *
     * @return {@code Date} that the lesson first started.
     */
    @Override
    public Date getStartDate() {
        return startDate;
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
            return getDayOfWeek().equals(otherLesson.getDayOfWeek()) // same day
                    && getTimeRange().isClashing(otherLesson.getTimeRange());
        } else {
            return getLocalDate().compareTo(otherLesson.getLocalDate()) <= 0 // same date or before
                    && getDayOfWeek().equals(otherLesson.getDayOfWeek()) // same day
                    && getTimeRange().isClashing(otherLesson.getTimeRange());
        }
    }
}
