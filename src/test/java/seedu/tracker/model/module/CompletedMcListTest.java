package seedu.tracker.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.tracker.logic.commands.AddCommand;
import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.testutil.TypicalModules;

public class CompletedMcListTest {
    private final AcademicCalendar defaultCalendar = getDefaultCalendar();
    private final ObservableList<Module> typicalModuleList = TypicalModules.getTypicalModuleTracker().getModuleList();

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompletedMcList(null));
    }

    @Test
    public void getCompletedMcList_containsDefaultList_returnsDefaultList() {
        CompletedMcList completedMcList = new CompletedMcList(defaultCalendar);
        boolean isAllZero = completedMcList.getCompletedMcList()
                .stream()
                .allMatch(mc -> mc.value == 0);
        assertTrue(isAllZero);
    }

    @Test
    public void update_withTypicalList_returnsTypicalList() {
        CompletedMcList completedMcList = new CompletedMcList(defaultCalendar);
        completedMcList.update(typicalModuleList, defaultCalendar);
        boolean isValid = isTypicalCompletedMcs(completedMcList.getCompletedMcList());
        assertTrue(isValid);
    }

    private boolean isTypicalCompletedMcs(ObservableList<Mc> mcList) {
        boolean isValid = true;
        for (int index : CompletedMcList.TAG_INDEXES_LIST) {
            System.out.println(mcList.get(index).value);
            isValid = isValid && mcList.get(index).value == 4;
        }
        return isValid;
    }

    private AcademicCalendar getDefaultCalendar() {
        AcademicYear year = new AcademicYear(3);
        Semester semester = new Semester(1);
        return new AcademicCalendar(year , semester);
    }
}
