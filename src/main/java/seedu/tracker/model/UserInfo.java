package seedu.tracker.model;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;

/**
 * Represents User's information.
 */
public class UserInfo implements ReadOnlyUserInfo {
    private AcademicCalendar currentSemester = new AcademicCalendar(new AcademicYear(1), new Semester(1));
    private Mc mcGoal = new Mc(160);

    /**
     * Creates a {@code UserInfo} with default values.
     */
    public UserInfo() {}

    /**
     * Creates a {@code UserInfo} with details in {@code currentSemester} and {@code mcGoal}.
     *
     * @param currentSemester semester to be set
     * @param mcGoal Mc goal to be set
     */
    public UserInfo(AcademicCalendar currentSemester, Mc mcGoal) {
        this.currentSemester = currentSemester;
        this.mcGoal = mcGoal;
    }

    /**
     * Creates a {@code UserInfo} with the details in {@code userInfo}.
     */
    public UserInfo(ReadOnlyUserInfo userInfo) {
        this();
        resetData(userInfo);
    }

    public AcademicCalendar getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(AcademicCalendar currentSemester) {
        requireNonNull(currentSemester);
        this.currentSemester = currentSemester;
    }

    public Mc getMcGoal() {
        return mcGoal;
    }

    public void setMcGoal(Mc mcGoal) {
        requireNonNull(mcGoal);
        this.mcGoal = mcGoal;
    }

    /**
     * Resets the existing data of this {@code UserInfo} with {@code newUserInfo}.
     */
    public void resetData(ReadOnlyUserInfo newUserInfo) {
        requireNonNull(newUserInfo);
        setCurrentSemester(newUserInfo.getCurrentSemester());
        setMcGoal(newUserInfo.getMcGoal());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UserInfo)) {
            return false;
        }


        UserInfo o = (UserInfo) other;

        return currentSemester.equals(o.currentSemester)
                && mcGoal.equals(o.mcGoal);

    }

    @Override
    public int hashCode() {
        return Objects.hash(currentSemester, mcGoal);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Semester : " + currentSemester);
        sb.append("\nMc Goal : " + mcGoal);
        return sb.toString();
    }

}
