package seedu.tracker.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
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
    AcademicCalendar currentSemester;
    Mc mcGoal;

    public UserInfo() {}

    public UserInfo(AcademicCalendar currentSemester, Mc mcGoal) {
        this.currentSemester = currentSemester;
        this.mcGoal = mcGoal;
    }

    /**
     * Creates an UserInfo using the details in the {@code toBeCopied}
     */
    public UserInfo(ReadOnlyUserInfo toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the User Info with {@code details}.
     */
    public void setUserInfo(UserInfo details) {
        this.currentSemester = details.getCurrentSemester();
        this.mcGoal = details.getMcGoal();
    }

    /**
     * Resets the existing data of this {@code UserInfo} with {@code newData}.
     */
    public void resetData(ReadOnlyUserInfo newData) {
        requireNonNull(newData);

        setUserInfo(newData.getUserInfo().get());
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
        return mcGoal;
    }

    public void setMcGoal(Mc mcGoal) {
        this.mcGoal = mcGoal;
    }

    @Override
    public ObservableObjectValue<UserInfo> getUserInfo() {
        return new SimpleObjectProperty<>(this);
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
        return Objects.hash(currentSemester, mcGoal);
    }
}
