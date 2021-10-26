package seedu.address.model.lesson;

import java.util.Set;

/**
 * Represents a Make Up Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class MakeUpLesson extends Lesson {
    /**
     * Every field must be present and not null.
     *
     * @param date Start and end Date of lesson.
     * @param timeRange      Time range of the lesson.
     * @param subject        Subject of the lesson.
     * @param homework       Homework for the lesson.
     * @param rates          Cost per lesson for the lesson.
     * @param cancelledDates Cancelled dates of the lesson.
     */
    public MakeUpLesson(Date date, TimeRange timeRange, Subject subject, Set<Homework> homework, LessonRates rates,
                        Set<Date> cancelledDates) {
        super(date, date, timeRange, subject, homework, rates, cancelledDates);
    }

    @Override
    public Lesson updateCancelledDates(Set<Date> updatedCancelledDates) {
        return new MakeUpLesson(getStartDate(), getTimeRange(), getSubject(), getHomework(), getLessonRates(),
            updatedCancelledDates);
    }

    /**
     * Check if the Lesson object is recurring.
     *
     * @return False
     */
    @Override
    public boolean isRecurring() {
        return false;
    }

    /**
     * Get the date of the makeup lesson to display.
     * Date will be start date since a makeup lesson only has one date.
     *
     * @return startDate Start date of the makeup lesson.
     */
    @Override
    public Date getDisplayDate() {
        return getStartDate();
    }

    /**
     * Returns true if this {@code MakeUpLesson} clashes with the given {@code Lesson}.
     *
     * @param otherLesson The other lesson to be compared with.
     * @return True if and only if lessons clash.
     */
    @Override
    public boolean isClashing(Lesson otherLesson) {
        // this makeup lesson is cancelled
        if (getCancelledDates().contains(getStartDate())) {
            return false;
        }
        return !isCancelled()
                && otherLesson.hasLessonOnDate(getStartDate())
                && getTimeRange().isClashing(otherLesson.getTimeRange());
    }

    @Override
    public boolean isCancelled() {
        return getCancelledDates().contains(getStartDate());
    }

    @Override
    public boolean hasLessonOnDate(Date otherDate) {
        return getStartDate().equals(otherDate) && !isCancelled();
    }
}

