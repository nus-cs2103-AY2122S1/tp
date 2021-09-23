package seedu.address.model.lesson;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a Lesson in the address book.
 * Guarantees: immutable; subject is valid as declared in {@link #isValidLessonName(String)},
 * start and end times are valid as declared in {@link #isTimeInValidRange(LocalTime, LocalTime)}
 * and {@link #hasStartBeforeEndTime(LocalTime, LocalTime)}
 */
public class Lesson {

    public static final String SUBJECT_VALIDATION_REGEX = "\\p{Alnum}+";
    public static final String SUBJECT_MESSAGE_CONSTRAINTS = "Subject names should be alphanumeric";
    public static final String TIME_RANGE_MESSAGE_CONSTRAINTS = "Timings should be between 9 am to 9 pm";
    public static final String START_AND_END_TIME_MESSAGE_CONSTRAINTS = "Start time should be before end time";

    public static final LocalTime BOUNDED_START_TIME = LocalTime.of(9, 0).minusMinutes(1); // 8:59 am
    public static final LocalTime BOUNDED_END_TIME = LocalTime.of(9 + 12, 0).plusMinutes(1); // 9:01 pm

    public final String subject;
    public final LocalDate date;
    public final LocalTime startTime;
    public final LocalTime endTime;
    public final double price;
    public final List<Person> students;
    // todo consider if students should be a hashset if ordering doesn't matter
    // todo consider if education level should be a field here as well

    /**
     * Constructs a {@code Lesson}.
     *
     * @param subject A valid lesson name.
     * @param date A valid date.
     * @param startTime A valid start time.
     * @param endTime A valid end time.
     */
    public Lesson(String subject, LocalDate date, LocalTime startTime, LocalTime endTime, double price) {
        requireAllNonNull(subject, date, startTime, endTime, price);

        checkArgument(isValidLessonName(subject), SUBJECT_MESSAGE_CONSTRAINTS);
        checkArgument(isTimeInValidRange(startTime, endTime), TIME_RANGE_MESSAGE_CONSTRAINTS);
        checkArgument(hasStartBeforeEndTime(startTime, endTime), START_AND_END_TIME_MESSAGE_CONSTRAINTS);

        this.subject = subject;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.students = new ArrayList<>();
    }

    /**
     * Returns true if a given string is a valid lesson name.
     */
    public static boolean isValidLessonName(String test) {
        return test.matches(SUBJECT_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given timings are within bounded limits.
     */
    public static boolean isTimeInValidRange(LocalTime testStart, LocalTime testEnd) {
        return (testStart.isAfter(BOUNDED_START_TIME)) && (testEnd.isBefore(BOUNDED_END_TIME));
    }

    /**
     * Returns true if a given timings are in proper order, i.e. start < end.
     */
    public static boolean hasStartBeforeEndTime(LocalTime testStart, LocalTime testEnd) {
        return (testStart.isBefore(testEnd));
    }

    /**
     * Returns formatted lesson code string.
     */
    public String getLessonCode() {
        return String.format("%s-%s-%s-%s", subject, date, startTime, endTime);
    }

    /**
     * Returns the number of students attending the lesson.
     */
    public int getLessonSize() {
        return students.size();
    }

    // todo consider if add students should be handled here or using commands

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof Lesson)) {
            return false;
        }
        // state check
        Lesson otherLesson = (Lesson) other;
        return subject.equals(otherLesson.subject)
                && date.equals(otherLesson.date)
                && startTime.equals(otherLesson.startTime)
                && endTime.equals(otherLesson.endTime); // todo consider if students list should be in this
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, date, startTime, endTime); // todo consider if students list should be in this
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + getLessonCode() + ']';
    }

}
