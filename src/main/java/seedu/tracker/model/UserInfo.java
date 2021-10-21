package seedu.tracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Represents user's information,
 * including MC goal and current semester the user sets.
 */
public class UserInfo implements ReadOnlyUserInfo {
    private AcademicCalendar currentSemester = new AcademicCalendar(new AcademicYear(1), new Semester(1));
    private Mc mcGoal = new Mc(120);

    /**
     * Creates a {@code UserInfo} with default values.
     */
    public UserInfo() {}

    /**
     * Creates an UserInfo using the details in the {@code toBeCopied}
     */
    public UserInfo(ReadOnlyUserInfo toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Creates a {@code UserInfo} with the details.
     */
    @JsonCreator
    public UserInfo(@JsonProperty("currentSemester") AcademicCalendar currentSemester,
                    @JsonProperty("mcGoal") Mc mcGoal) {
        this.currentSemester = currentSemester;
        this.mcGoal = mcGoal;
    }

    /**
     * Resets the existing data of this {@code UserInfo} with {@code newData}.
     */
    public void resetData(ReadOnlyUserInfo newData) {
        requireNonNull(newData);
        setCurrentSemester(newData.getCurrentSemester());
        setMcGoal(newData.getMcGoal());
    }

    /**
     * Replaces the contents of the User Info with {@code details}.
     */
    public void setUserInfo(UserInfo details) {
        this.currentSemester = details.getCurrentSemester();
        this.mcGoal = details.getMcGoal();
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
        assert other != null : "The UserInfo object shouldn't be null";

        assert otherInfo.mcGoal != null : "Mc Goal shouldn't be null";
        boolean result = otherInfo.getMcGoal().equals(getMcGoal())
                && otherInfo.getCurrentSemester().equals(getCurrentSemester());

        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentSemester, mcGoal);
    }
}
