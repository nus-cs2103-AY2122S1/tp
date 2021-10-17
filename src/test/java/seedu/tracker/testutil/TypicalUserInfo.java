package seedu.tracker.testutil;

import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;

public class TypicalUserInfo {
    public static final UserInfo bachelorYearOneSemOne = new UserInfo(new AcademicCalendar(new AcademicYear(1), new Semester(1)),
            new Mc(120));

    private TypicalUserInfo() {} // prevents instantiation

    /**
     * Returns an {@code UserInfo} with the typical info.
     */
    public static UserInfo getTypicalUserInfo() {
        return bachelorYearOneSemOne;
    }
}
