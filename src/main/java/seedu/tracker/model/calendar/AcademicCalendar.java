package seedu.tracker.model.calendar;

import static seedu.tracker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class AcademicCalendar {
    private final AcademicYear year;
    private final Semester semester;

    /**
     * Constructs Academic calendar.
     */
    public AcademicCalendar(AcademicYear year, Semester semester) {
        requireAllNonNull(year, semester);
        this.year = year;
        this.semester = semester;
    }

    public AcademicYear getAcademicYear() {
        return year;
    }

    public Semester getSemester() {
        return semester;
    }

    /**
     * Returns true if this academic calendar is chronologically before the other.
     * @param other academic calendar to be compared to.
     * @return true if this academic calendar is chronologically before the other
     */
    public boolean isBefore(AcademicCalendar other) {
        return getAcademicYear().value <= other.getAcademicYear().value
                && getSemester().value < other.getSemester().value;
    }

    /**
     * Returns true if both academic calendar have the same year and semester.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AcademicCalendar)) {
            return false;
        }

        AcademicCalendar otherAcademicCalendar = (AcademicCalendar) other;
        return otherAcademicCalendar.getAcademicYear().equals(getAcademicYear())
                && otherAcademicCalendar.getSemester().equals(getSemester());
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, semester);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("year: ")
                .append(getAcademicYear())
                .append(" ;")
                .append("semester: ")
                .append(getSemester());
        return builder.toString();
    }

}
