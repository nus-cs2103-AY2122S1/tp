package seedu.address.model.lesson;

import java.util.Set;

public class MakeUpLesson extends Lesson {
    /**
     * Every field must be present and not null.
     *
     * @param date
     * @param startTime
     * @param endTime
     * @param subject
     * @param homework
     */
    public MakeUpLesson(Date date, Time startTime, Time endTime,
                           Subject subject, Set<Homework> homework) {
        super(date, startTime, endTime, subject, homework);
    }

    @Override
    public Lesson updateDate(String newDateString) {
        return new MakeUpLesson(new Date(newDateString), getStartTime(), getEndTime(),
                getSubject(), getHomework());
    }

}

