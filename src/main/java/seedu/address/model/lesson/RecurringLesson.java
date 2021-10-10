package seedu.address.model.lesson;

import java.time.LocalDate;
import java.util.Set;

import seedu.address.model.person.Person;

/**
 * Represents a Recurring Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class RecurringLesson extends Lesson {
    /**
     * Count of the number of lessons taken this month for this recurring lesson.
     */
    private static int recurringLessonsCount = 0;

    /**
     * Every field must be present and not null.
     *
     * @param owner The person that this lesson belongs to.
     * @param date Date of lesson.
     * @param timeRange Time range of the lesson.
     * @param subject Subject of the lesson.
     * @param homework Homework for the lesson.
     */
    public RecurringLesson(Person owner, Date date, TimeRange timeRange,
                           Subject subject, Set<Homework> homework) {
        super(owner, date, timeRange, subject, homework);
        updateRecurringLessonCount();
    }

    /**
     * Returns the number of lessons attended.
     *
     * @return The number of lessons attended for this recurring lesson.
     */
    public static int getRecurringLessonsCount() {
        return recurringLessonsCount;
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
     * Edit the date of the recurring lesson.
     *
     * @param newDateString The date to be updated with.
     * @return {@code RecurringLesson} with the updated date.
     */
    @Override
    public Lesson updateDate(String newDateString) {
        Date newDate = new Date(newDateString);

        return newDate.compareTo(getDate()) > 0
            ? new RecurringLesson(getOwner(), new Date(newDateString), getTimeRange(),
            getSubject(), getHomework())
            : this;
    }

    /**
     * Checks if the date has passed and update the recurring lesson count accordingly.
     *
     * @return {@code RecurringLesson} with the updated date.
     */
    private RecurringLesson updateRecurringLessonCount() {
        // Compare lesson date to current date
        // Increment count if date has passed
        LocalDate lessonDate = getDate().getLocalDate();
        boolean isLessonEarlierThanCurrentDate = lessonDate.compareTo(LocalDate.now()) > 0;
        if (isLessonEarlierThanCurrentDate) {
            return this;
        }
        recurringLessonsCount++;
        // Update the date
        Date newDate = getDate().updateDateWithWeek();
        return new RecurringLesson(getOwner(), newDate, getTimeRange(),
                getSubject(), getHomework());
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
