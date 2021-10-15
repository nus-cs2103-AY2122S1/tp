package seedu.tracker.model;

import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;

import java.util.Objects;

/**
 * Represents user's information,
 * including MC goal and current semester the user sets.
 */
public class UserInfo {
    AcademicCalendar currentSemester;
    Mc McGoal;

    public UserInfo() {}

    public UserInfo(AcademicCalendar currentSemester, Mc McGoal) {
        this.currentSemester = currentSemester;
        this.McGoal = McGoal;
    }

    public AcademicCalendar getCurrentSemester() {
        return currentSemester;
    }

    public AcademicYear getCurrentYear() {
        return currentSemester.getAcademicYear();
    }

    public Semester getSemester() {
        return currentSemester.getSemester();
    }

    public void setCurrentSemester(AcademicCalendar currentSemester) {
        this.currentSemester = currentSemester;
    }

    public Mc getMcGoal() {
        return McGoal;
    }

    public void setMcGoal(Mc mcGoal) {
        McGoal = mcGoal;
    }

    /**
     * Returns true if both info have the same identity and data fields.
     * This defines a strong notion of equality between two user info.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UserInfo)) {
            return false;
        }

        UserInfo otherInfo = (UserInfo) other;

        boolean result = otherInfo.getMcGoal().equals(getMcGoal())
                && otherInfo.getCurrentSemester().equals(getCurrentSemester());

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentSemester, McGoal);
    }

}
