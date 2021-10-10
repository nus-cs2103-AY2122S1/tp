package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Person;

public class LessonWithoutOwner implements Comparable<LessonWithoutOwner> {

    // Time fields
    private final Date date;
    private final TimeRange timeRange;

    // Data fields
    private final Subject subject;
    private final Set<Homework> homework = new HashSet<>();

    private final boolean isRecurring;

    /**
     * Every field must be present and not null.
     *
     * @param date Date of lesson.
     * @param timeRange Time range of the lesson.
     * @param subject Subject of the lesson.
     * @param homework Homework for the lesson.
     * @param isRecurring Whether this will return a {@code RecurringLesson}
     *                    when LessonWithoutOwner#build() is called.
     */
    public LessonWithoutOwner(Date date, TimeRange timeRange, Subject subject, Set<Homework> homework,
                              boolean isRecurring) {
        requireAllNonNull(date, timeRange, subject, homework);
        this.date = date;
        this.timeRange = timeRange;
        this.subject = subject;
        this.homework.addAll(homework);
        this.isRecurring = isRecurring;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public Date getDate() {
        return date;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public Subject getSubject() {
        return subject;
    }

    public Set<Homework> getHomework() {
        return homework;
    }

    /**
     * Builds a Lesson.
     *
     * @param person The Person will own the lesson.
     * @return A valid Lesson.
     */
    public Lesson buildLessonWithOwner(Person person) {
        requireNonNull(person);
        if (isRecurring) {
            return new RecurringLesson(person, date, timeRange, subject, homework);
        } else {
            return new MakeUpLesson(person, date, timeRange, subject, homework);
        }
    }

    public DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }

    private int compareLocalDate(LessonWithoutOwner otherLessonWithoutOwner) {
        return date.getLocalDate().compareTo(otherLessonWithoutOwner.date.getLocalDate());
    }

    private boolean isTimeRangeClashing(LessonWithoutOwner otherLessonWithoutOwner) {
        return timeRange.isClashing(otherLessonWithoutOwner.timeRange);
    }
    private boolean isSameDay(LessonWithoutOwner otherLessonWithoutOwner) {
        return getDayOfWeek().equals(otherLessonWithoutOwner.getDayOfWeek());
    }

    /**
     * Returns true both lessons clash.
     *
     * @param otherLessonWithoutOwner The other lesson to be compared with.
     * @return True if and only if lessons clash.
     */
    public boolean isClashing(LessonWithoutOwner otherLessonWithoutOwner) {
        if (isRecurring) {
            if (otherLessonWithoutOwner.isRecurring) {
                return isSameDay(otherLessonWithoutOwner)
                        && isTimeRangeClashing(otherLessonWithoutOwner);
            } else {
                return compareLocalDate(otherLessonWithoutOwner) <= 0 // same date or before
                        && isSameDay(otherLessonWithoutOwner)
                        && isTimeRangeClashing(otherLessonWithoutOwner);
            }
        } else {
            if (otherLessonWithoutOwner.isRecurring) {
                return compareLocalDate(otherLessonWithoutOwner) >= 0 // same date or after
                        && isSameDay(otherLessonWithoutOwner)
                        && isTimeRangeClashing(otherLessonWithoutOwner);
            } else {
                return compareLocalDate(otherLessonWithoutOwner) == 0
                        && isTimeRangeClashing(otherLessonWithoutOwner);
            }
        }
    }

    /**
     * Check if both lessons have the same data fields.
     * This defines a stronger notion of equality between two lessons.
     *
     * @param other The other object to compare.
     * @return True if all fields are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LessonWithoutOwner)) {
            return false;
        }

        LessonWithoutOwner otherLessonWithoutOwner = (LessonWithoutOwner) other;
        return date.equals(otherLessonWithoutOwner.date)
                && timeRange.equals(otherLessonWithoutOwner.timeRange)
                && subject.equals(otherLessonWithoutOwner.subject)
                && homework.equals(otherLessonWithoutOwner.homework)
                && isRecurring == otherLessonWithoutOwner.isRecurring;
    }

    /**
     * Compares this LessonWithoutOwner object with the other LessonWithoutOwner object.
     *
     * @param other The LessonWithoutOwner object to compare with.
     * @return 1, if this is later than other;0 if equal; -1 if this is earlier.
     */
    @Override
    public int compareTo(LessonWithoutOwner other) {
        int compareDate = getDate().compareTo(other.getDate());
        int compareTime = getTimeRange().compareTo(other.getTimeRange());
        // Compare time if date is equal
        return compareDate == 0 ? compareTime : compareDate;
    }

}
