package seedu.tracker.model.module;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.testutil.TypicalModules;

public class McProgressListTest {
    private final AcademicYear defaultYear = new AcademicYear(3);
    private final Semester defaultSemester = new Semester(1);
    private final AcademicCalendar defaultAcademicCalendar = new AcademicCalendar(defaultYear, defaultSemester);
    private final UserInfo defaultUserInfo = new UserInfo(defaultAcademicCalendar, new Mc(160));
    private final ObservableList<Module> typicalModuleList = TypicalModules.getTypicalModuleTracker().getModuleList();

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new McProgressList(null));
    }

    @Test
    public void getMcProgressList_containsDefaultList_returnsDefaultList() {
        McProgressList mcProgressList = new McProgressList(defaultUserInfo);
        boolean isAllZero = mcProgressList.getMcProgressList()
                .stream()
                .allMatch(progress -> progress.getCompleted().value == 0);
        assertTrue(isAllZero);
    }

    @Test
    public void update_withTypicalList_returnsTypicalList() {
        McProgressList mcProgressList = new McProgressList(defaultUserInfo);
        mcProgressList.update(typicalModuleList, defaultUserInfo);
        boolean isValid = isTypicalMcProgress(mcProgressList.getMcProgressList());
        assertTrue(isValid);
    }

    private boolean isTypicalMcProgress(ObservableList<McProgress> progressList) {
        boolean isValid = true;
        int pointer = 0;
        for (McProgress progress : progressList) {
            if (pointer == McProgressList.TOTAL_INDEX) {
                isValid = isValid && progress.getCompleted().value == 36;
            } else {
                isValid = isValid && progress.getCompleted().value == 4;
            }
            pointer++;
        }
        return isValid;
    }
}
