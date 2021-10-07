package seedu.address.model.lesson;

import java.util.Set;

/**
 * Represents a Recurring Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class RecurringLesson extends Lesson {

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
        return new RecurringLesson(newDate, getTimeRange(),
            getSubject(), getHomework());
    }

}
