package seedu.tracker.model.calendar;

import static seedu.tracker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class AcademicCalendar implements Comparable<AcademicCalendar> {
    public static final String MESSAGE_CONSTRAINTS = "Academic Year should only contain numbers from 1 to 6, and "
            + "semester should only contain numbers from 1 to 4. Both of them should not be blank.";
    private final AcademicYear year;
    private final Semester semester;

    /**
     * Constructs a dummy object only used by JsonUserInfoStorage.
     * This default constructor shouldn't be used anywhere else.
     */
    public AcademicCalendar() {
        this.year = null;
        this.semester = null;
    }

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
        if (getAcademicYear().value < other.getAcademicYear().value) {
            return true;
        } else if (getAcademicYear().value > other.getAcademicYear().value) {
            return false;
        } else {
            return getSemester().value < other.getSemester().value;
        }
    }

    /**
     * Returns true if a given int is a valid Academic calendar.
     */
    public static boolean isValidAcademicCalendar(int testYear, int testSem) {
        return AcademicYear.isValidAcademicYear(testYear) && Semester.isValidSemester(testSem);
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
        builder.append("year ")
                .append(getAcademicYear())
                .append(", semester ")
                .append(getSemester());
        return builder.toString();
    }

    @Override
    public int compareTo(AcademicCalendar academicCalendar) {
        if (this.year.compareTo(academicCalendar.year) == 0) {
            return this.semester.compareTo(academicCalendar.semester);
        }
        return this.year.compareTo(academicCalendar.year);
    }

}
