package seedu.address.model.lesson;

import java.time.LocalDate;
import java.util.Set;

public class RecurringLesson extends Lesson {
    private static int recurringLessonsCount = 0;

    /**
     * Every field must be present and not null.
     *
     * @param date
     * @param startTime
     * @param endTime
     * @param subject
     * @param homework
     */
    public RecurringLesson(Date date, Time startTime, Time endTime,
                           Subject subject, Set<Homework> homework) {
        super(date, startTime, endTime, subject, homework);
        updateRecurringLessonCount();
    }

    public static int getRecurringLessonsCount() {
        return recurringLessonsCount;
    }

    @Override
    public boolean isRecurring() {
        return true;
    }

    @Override
    public Lesson updateDate(String newDateString) {
        return new RecurringLesson(new Date(newDateString), getStartTime(), getEndTime(),
                getSubject(), getHomework());
    }

    private RecurringLesson updateRecurringLessonCount() {
        // Compare lesson date to current date
        // Increment count if date has passed
        if (getDate().getLocalDate().compareTo(LocalDate.now()) > 0) {
            return this;
        }
        recurringLessonsCount++;
        // Update the date
        Date newDate = super.updateDateWithWeek();
        return new RecurringLesson(newDate, getStartTime(), getEndTime(),
                getSubject(), getHomework());
    }

}
