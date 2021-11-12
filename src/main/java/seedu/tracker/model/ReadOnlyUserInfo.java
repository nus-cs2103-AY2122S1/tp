package seedu.tracker.model;

import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.module.Mc;

public interface ReadOnlyUserInfo {
    AcademicCalendar getCurrentSemester();

    Mc getMcGoal();
}
