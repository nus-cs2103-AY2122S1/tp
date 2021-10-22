package seedu.tracker.model;

import static seedu.tracker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
