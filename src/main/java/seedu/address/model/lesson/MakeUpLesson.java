package seedu.address.model.lesson;

import java.util.Set;

import seedu.address.model.person.Person;

/**
 * Represents a Make Up Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class MakeUpLesson extends Lesson {
    /**
     * Every field must be present and not null.
     *
     * @param owner The person that this lesson belongs to.
     * @param date Date of lesson.
     * @param timeRange Time range of the lesson.
     * @param subject Subject of the lesson.
     * @param homework Homework for the lesson.
     */
    public MakeUpLesson(Person owner, Date date, TimeRange timeRange,
                        Subject subject, Set<Homework> homework) {
        super(owner, date, timeRange, subject, homework);
    }

    /**
     * Edit the date of the make-up lesson.
     *
     * @param newDateString The date to be updated with.
     * @return {@code MakeUpLesson} with the updated date.
     */
    @Override
    public Lesson updateDate(String newDateString) {
        Date newDate = new Date(newDateString);

        return newDate.compareTo(getDate()) > 0
            ? new MakeUpLesson(getOwner(), newDate, getTimeRange(), getSubject(), getHomework())
            : this;
    }

    /**
     * Check if the Lesson object is recurring.
     *
     * @return False
     */
    public boolean isRecurring() {
        return false;
    }

    /**
     * Returns true if this {@code MakeUpLesson} clashes with the given {@code Lesson}.
     *
     * @param otherLesson The other lesson to be compared with.
     * @return True if and only if lessons clash.
     */
    @Override
    public boolean isClashing(Lesson otherLesson) {
        if (otherLesson.isRecurring()) {
            return getLocalDate().compareTo(otherLesson.getLocalDate()) >= 0 // same date or after
                    && getDayOfWeek().equals(otherLesson.getDayOfWeek()) // same day
                    && getTimeRange().isClashing(otherLesson.getTimeRange());
        } else {
            return getLocalDate().equals(otherLesson.getLocalDate())
                    && getTimeRange().isClashing(otherLesson.getTimeRange());
        }
    }
}

