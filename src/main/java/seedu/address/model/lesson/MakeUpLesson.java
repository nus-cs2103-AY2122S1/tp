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
     * @param timeRange Time range of the lesson.
     * @param subject Subject of the lesson.
     * @param homework Homework for the lesson.
     */
    public MakeUpLesson(Date date, TimeRange timeRange,
                           Subject subject, Set<Homework> homework) {
        super(date, timeRange, subject, homework);
    }

    @Override
    public Lesson updateDate() {
        return this;
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

