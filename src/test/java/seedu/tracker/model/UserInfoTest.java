package seedu.tracker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {
    @Test
    public void setCurrentSemester_nullCurrentSemester_throwsNullPointerException() {
        UserInfo userInfo = new UserInfo();
        assertThrows(NullPointerException.class, () -> userInfo.setCurrentSemester(null));
    }

    @Test
    public void setMcGoal_nullMc_throwsNullPointerException() {
        UserInfo userInfo = new UserInfo();
        assertThrows(NullPointerException.class, () -> userInfo.setMcGoal(null));
    }
}