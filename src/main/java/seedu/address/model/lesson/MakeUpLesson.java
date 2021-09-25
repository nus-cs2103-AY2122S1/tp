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
     * @param date Date of lesson.
     * @param startTime Start time of the lesson.
     * @param endTime End time of the lesson.
     * @param subject Subject of the lesson.
     * @param homework Homework for the lesson.
     */
    public MakeUpLesson(Date date, Time startTime, Time endTime,
                           Subject subject, Set<Homework> homework) {
        super(date, startTime, endTime, subject, homework);
    }

    /**
     * Edit the date of the make-up lesson.
     *
     * @param newDateString The date to be updated with.
     * @return {@code MakeUpLesson} with the updated date.
     */
    @Override
    public Lesson updateDate(String newDateString) {
        return new MakeUpLesson(new Date(newDateString), getStartTime(), getEndTime(),
                getSubject(), getHomework());
    }

    /**
     * Check if the Lesson object is recurring.
     *
     * @return False
     */
    public boolean isRecurring() {
        return false;
    }

}

