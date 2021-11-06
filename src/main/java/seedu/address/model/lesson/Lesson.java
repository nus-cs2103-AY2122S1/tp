package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a Lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Lesson implements Comparable<Lesson> {
    // Time fields
    private final Date startDate;
    private final Date endDate;
    private final TimeRange timeRange;

    // Data fields
    private final Subject subject;
    private final Set<Homework> homework = new HashSet<>();
    private final Set<Date> cancelledDates = new HashSet<>();

    // Fees calculation related fields
    private final LessonRates lessonRates;
    private final OutstandingFees outstandingFees;

    /**
     * Every field must be present and not null.
     *
     * @param date Start date of the lesson.
     * @param endDate End date of the lesson.
     * @param timeRange Time range of the lesson.
     * @param subject Subject of the lesson.
     * @param homework Homework for the lesson.
     * @param rates Cost per hour for the lesson.
     * @param fees Outstanding fees that student has not paid for this lesson.
     * @param cancelledDates Cancelled dates of the lesson.
     */
    public Lesson(Date date, Date endDate, TimeRange timeRange, Subject subject, Set<Homework> homework,
                  LessonRates rates, OutstandingFees fees, Set<Date> cancelledDates) {
        requireAllNonNull(date, endDate, timeRange, subject, homework, rates, fees, cancelledDates);
        this.startDate = date;
        this.endDate = endDate;
        this.timeRange = timeRange;
        this.subject = subject;
        this.homework.addAll(homework);
        this.lessonRates = rates;
        this.outstandingFees = fees;
        if (!isRecurring()) {
            // non-recurring lesson should have maximum one cancelled date
            assert cancelledDates.size() <= 1;
        }
        this.cancelledDates.addAll(cancelledDates);
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public LocalDate getLocalDate() {
        return startDate.getLocalDate();
    }

    public DayOfWeek getDayOfWeek() {
        return startDate.getDayOfWeek();
    }

    public boolean hasStarted() {
        return startDate.isOver();
    }

    public Subject getSubject() {
        return subject;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public LocalDateTime getStartDateTime() {
        return timeRange.getStart().atDate(startDate.getLocalDate());
    }

    public LocalDateTime getEndDateTime() {
        return timeRange.getEnd().atDate(startDate.getLocalDate());
    }

    public LessonRates getLessonRates() {
        return lessonRates;
    }

    public OutstandingFees getOutstandingFees() {
        return outstandingFees;
    }

    /**
     * Returns an immutable homework set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Homework> getHomework() {
        return Collections.unmodifiableSet(homework);
    }

    /**
     * Returns an immutable cancelledDates set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Date> getCancelledDates() {
        return Collections.unmodifiableSet(cancelledDates);
    }

    /**
     * Returns a lesson with the same details but updated cancelled dates.
     *
     * @param updatedCancelledDates A set of cancelled dates of the lesson.
     * @return Lesson with updated cancelled dates.
     */
    public abstract Lesson updateCancelledDates(Set<Date> updatedCancelledDates);

    /**
     * Checks if the Lesson object is recurring.
     *
     * @return True if it is a recurring lesson, false otherwise.
     */
    public abstract boolean isRecurring();

    /**
     * Returns a string representing the type of this lesson.
     *
     * @return The type of this lesson.
     */
    public abstract String getTypeOfLesson();

    /**
     * Gets the date of the lesson to display to the user.
     */
    public abstract Date getDisplayDate();

    /**
     * Gets the day of week of the lesson to display to the user.
     */
    public String getDisplayDayOfWeek() {
        return getLocalDate().format(DateTimeFormatter.ofPattern("EEE"));
    }

    /**
     * Gets the local date of the lesson to display to the user.
     *
     * @return {@code LocalDate} to be displayed.
     */
    public LocalDate getDisplayLocalDate() {
        return getDisplayDate().getLocalDate();
    }

    /**
     * Gets the start {@code LocalDateTime} to be displayed.
     *
     * @return start {@code LocalDateTime} to be displayed.
     */
    public LocalDateTime getDisplayStartLocalDateTime() {
        return timeRange.getStart().atDate(getDisplayLocalDate());
    }

    /**
     * Gets the end {@code LocalDateTime} to be displayed.
     *
     * @return End {@code LocalDateTime} to be displayed.
     */
    public LocalDateTime getDisplayEndLocalDateTime() {
        return timeRange.getEnd().atDate(getDisplayLocalDate());
    }

    /**
     * Returns true both lessons clash.
     *
     * @param otherLesson The other lesson to be compared with.
     * @return True if and only if the lessons clash.
     */
    public abstract boolean isClashing(Lesson otherLesson);

    /**
     * Checks if this lesson occurs on a given date.
     *
     * @param date The lesson date to check.
     * @return True if this lesson occurs on the date.
     */
    public abstract boolean hasLessonOnDate(Date date);

    /**
     * Checks if this lesson is cancelled and does not occur on any date.
     *
     * @return True if lesson is cancelled.
     */
    public abstract boolean isCancelled();

    /**
     * Checks if both lessons have the same data fields.
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

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;

        return otherLesson.getStartDate().equals(getStartDate())
                && otherLesson.getEndDate().equals(getEndDate())
                && otherLesson.getTimeRange().equals(getTimeRange())
                && otherLesson.getSubject().equals(getSubject())
                && otherLesson.getHomework().equals(getHomework())
                && otherLesson.getLessonRates().equals(getLessonRates())
                && otherLesson.getOutstandingFees().equals(getOutstandingFees())
                && otherLesson.getCancelledDates().equals(getCancelledDates())
                && otherLesson.isRecurring() == isRecurring();
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, timeRange, subject, homework,
                lessonRates, outstandingFees, cancelledDates);
    }

    /**
     * Get the lesson timing details in String.
     *
     * @return String representation for time fields for the Lesson.
     */
    public String getLessonDetails() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Start date: ")
                .append(getStartDate());

        if (!getEndDate().equals(Date.MAX_DATE)) {
            builder.append("; End date: ")
                .append(getEndDate());
        }

        builder.append("; Time: ")
                .append(getTimeRange());

        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        // common fields of Lesson
        builder.append("Date: ")
                .append(getDisplayDate())
                .append("; Time: ")
                .append(getTimeRange())
                .append("; Subject: ")
                .append(getSubject())
                .append("; Outstanding Fees: ")
                .append(getOutstandingFees())
                .append("; Lesson Rates: ")
                .append(getLessonRates());

        String homework = getHomework().stream().map(Homework::toString).collect(Collectors.joining(", "));
        if (!homework.isEmpty()) {
            builder.append("; Homework: ")
                    .append(homework);
        }
        return builder.toString();
    }

    /**
     * Compares this Lesson object with the other Lesson object.
     *
     * @param other The Lesson object to compare with.
     * @return 1, if this is later than other, 0 if clashing, and -1 if this is earlier.
     */
    @Override
    public int compareTo(Lesson other) {
        if (isClashing(other)) {
            return 0;
        }

        /*
        Orders by display date, followed by time range.
        If both are the same, it means that at least one lesson isCancelled and orders with cancelled lesson below.
        If both are cancelled, orders with Recurring lessons above.
        If same type of lesson, orders by subject alphabetically.
        Otherwise, this lesson is below.
         */
        int result = Comparator.comparing(Lesson::getDisplayDate)
                .thenComparing(Lesson::getTimeRange)
                .thenComparing(lesson -> lesson.isCancelled())
                .thenComparing(lesson -> !lesson.isRecurring())
                .thenComparing(Lesson::getSubject)
                .compare(this, other);
        return result == 0 ? 1 : result;
    }

}
