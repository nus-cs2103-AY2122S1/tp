package seedu.times.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTableReverseClasses;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTableReverseStudents;

import org.junit.jupiter.api.Test;

import seedu.times.model.Model;
import seedu.times.model.ModelManager;
import seedu.times.model.UserPrefs;

class SortCommandTest {

    @Test
    void execute_nameInDescOrder_sortNameAsc() {
        Model model = new ModelManager(getTypicalTimesTableReverseStudents(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        assertCommandSuccess(new SortCommand(SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_OF_SORT_ASC), model,
                String.format(SortCommand.MESSAGE_SUCCESS, SortCommand.STUDENT_TAB_SORTED,
                        SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_OF_SORT_ASC), expectedModel);
    }

    @Test
    void execute_nameInAscOrder_sortNameAsc() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTimesTable(), new UserPrefs());
        assertCommandSuccess(new SortCommand(SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_OF_SORT_ASC), model,
                String.format(SortCommand.MESSAGE_SUCCESS, SortCommand.STUDENT_TAB_SORTED,
                        SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_OF_SORT_ASC), expectedModel);
    }

    @Test
    void execute_nameInDescOrder_sortNameDesc() {
        Model model = new ModelManager(getTypicalTimesTableReverseStudents(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTimesTable(), new UserPrefs());
        assertCommandSuccess(new SortCommand(SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_OF_SORT_DESC), model,
                String.format(SortCommand.MESSAGE_SUCCESS, SortCommand.STUDENT_TAB_SORTED,
                        SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_OF_SORT_DESC), expectedModel);
    }

    @Test
    void execute_nameInAscOrder_sortNameDesc() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTimesTableReverseStudents(), new UserPrefs());
        assertCommandSuccess(new SortCommand(SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_OF_SORT_DESC), model,
                String.format(SortCommand.MESSAGE_SUCCESS, SortCommand.STUDENT_TAB_SORTED,
                        SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_OF_SORT_DESC), expectedModel);
    }

    @Test
    void execute_classInAscOrder_sortTimingAsc() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTimesTable(), new UserPrefs());
        assertCommandSuccess(new SortCommand(SortCommand.SORT_BY_CLASS_TIMING, SortCommand.DIRECTION_OF_SORT_ASC),
                model, String.format(SortCommand.MESSAGE_SUCCESS, SortCommand.CLASSES_TAB_SORTED,
                        SortCommand.SORT_BY_CLASS_TIMING, SortCommand.DIRECTION_OF_SORT_ASC), expectedModel);
    }

    @Test
    void execute_classInDescOrder_sortTimingAsc() {
        Model model = new ModelManager(getTypicalTimesTableReverseClasses(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        assertCommandSuccess(new SortCommand(SortCommand.SORT_BY_CLASS_TIMING, SortCommand.DIRECTION_OF_SORT_ASC),
                model, String.format(SortCommand.MESSAGE_SUCCESS, SortCommand.CLASSES_TAB_SORTED,
                        SortCommand.SORT_BY_CLASS_TIMING, SortCommand.DIRECTION_OF_SORT_ASC), expectedModel);
    }

    @Test
    void execute_classInAscOrder_sortTimingDesc() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTimesTableReverseClasses(), new UserPrefs());
        assertCommandSuccess(new SortCommand(SortCommand.SORT_BY_CLASS_TIMING, SortCommand.DIRECTION_OF_SORT_DESC),
                model, String.format(SortCommand.MESSAGE_SUCCESS, SortCommand.CLASSES_TAB_SORTED,
                        SortCommand.SORT_BY_CLASS_TIMING, SortCommand.DIRECTION_OF_SORT_DESC), expectedModel);
    }

    @Test
    void execute_classInDescOrder_sortTimingDesc() {
        Model model = new ModelManager(getTypicalTimesTableReverseClasses(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTimesTable(), new UserPrefs());
        assertCommandSuccess(new SortCommand(SortCommand.SORT_BY_CLASS_TIMING, SortCommand.DIRECTION_OF_SORT_DESC),
                model, String.format(SortCommand.MESSAGE_SUCCESS, SortCommand.CLASSES_TAB_SORTED,
                        SortCommand.SORT_BY_CLASS_TIMING, SortCommand.DIRECTION_OF_SORT_DESC), expectedModel);
    }

    @Test
    void testEquals() {
        SortCommand sortNameAsc = new SortCommand("name", "asc");
        SortCommand sortNameDesc = new SortCommand("name", "desc");
        SortCommand sortTimingAsc = new SortCommand("timing", "asc");
        SortCommand sortTimingDesc = new SortCommand("timing", "desc");

        //same object
        assertTrue(sortNameAsc.equals(sortNameAsc));
        assertTrue(sortNameDesc.equals(sortNameDesc));
        assertTrue(sortTimingAsc.equals(sortTimingAsc));
        assertTrue(sortTimingDesc.equals(sortTimingDesc));

        //same values
        assertTrue(new SortCommand("name", "asc").equals(sortNameAsc));
        assertTrue(new SortCommand("name", "desc").equals(sortNameDesc));
        assertTrue(new SortCommand("timing", "asc").equals(sortTimingAsc));
        assertTrue(new SortCommand("timing", "desc").equals(sortTimingDesc));

        //different types
        assertFalse(sortNameAsc.equals(1));

        //null
        assertFalse(sortNameDesc.equals(null));

        //different values
        assertFalse(sortNameAsc.equals(sortNameDesc));
        assertFalse(sortNameDesc.equals(sortTimingAsc));
        assertFalse(sortTimingAsc.equals(sortTimingDesc));
        assertFalse(sortTimingDesc.equals(sortNameAsc));
    }
}
