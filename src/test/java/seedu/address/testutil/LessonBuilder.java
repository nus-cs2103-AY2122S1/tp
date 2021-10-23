package seedu.address.testutil;

import java.time.DayOfWeek;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.Timeslot;

/**
 * A utility class to help with building Person objects.
 */
public class LessonBuilder {

    public static final Timeslot DEFAULT_TIMESLOT = new Timeslot("11:00", "13:00");
    public static final Subject DEFAULT_SUBJECT = new Subject("Biology");
    public static final DayOfWeek DEFAULT_DAY_OF_WEEK = DayOfWeek.of(1);

    private Timeslot timeslot;
    private Subject subject;
    private DayOfWeek dayOfWeek;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        timeslot = DEFAULT_TIMESLOT;
        subject = DEFAULT_SUBJECT;
        dayOfWeek = DEFAULT_DAY_OF_WEEK;
    }

    /**
     * Creates a {@code LessonBuilder} with the details from toCopy.
     */
    public LessonBuilder(Lesson toCopy) {
        this.timeslot = toCopy.getTimeslot();
        this.subject = toCopy.getSubject();
        this.dayOfWeek = toCopy.getDayOfWeek();
    }

    /**
     * Sets the {@code Timeslot} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withTimeslot(String startTime, String endTime) {
        this.timeslot = new Timeslot(startTime, endTime);
        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }

    /**
     * Sets the {@code DayOfWeek} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withDayOfWeek(int day) {
        this.dayOfWeek = DayOfWeek.of(day);
        return this;
    }

    /**
     * Sets the {@code DayOfWeek} of the {@code Lesson} that we are building
     */
    public LessonBuilder withDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public Lesson build() {
        return new Lesson(timeslot, subject, dayOfWeek);
    }
}
