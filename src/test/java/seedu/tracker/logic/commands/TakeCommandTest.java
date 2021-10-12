package seedu.tracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tracker.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.tracker.testutil.Assert.assertThrows;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

import org.junit.jupiter.api.Test;

import seedu.tracker.commons.core.Messages;
import seedu.tracker.commons.core.index.Index;
import seedu.tracker.model.Model;
import seedu.tracker.model.ModelManager;
import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.UserPrefs;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Module;
import seedu.tracker.testutil.ModuleBuilder;

public class TakeCommandTest {
    private Model model = new ModelManager(getTypicalModuleTracker(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        AcademicYear academicYear = new AcademicYear(2);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar = new AcademicCalendar(academicYear, semester);

        assertThrows(NullPointerException.class, () -> new TakeCommand(null, academicCalendar));
    }

    @Test
    public void constructor_nullAcademicYear_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TakeCommand(INDEX_FIRST_MODULE, null));
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Module scheduledModule = new ModuleBuilder().withAcademicCalendar(2, 1).build();

        AcademicYear academicYear = new AcademicYear(2);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar = new AcademicCalendar(academicYear, semester);
        TakeCommand takeCommand = new TakeCommand(INDEX_FIRST_MODULE, academicCalendar);

        String expectedMessage = String.format(TakeCommand.MESSAGE_SUCCESS, scheduledModule);

        Model expectedModel = new ModelManager(new ModuleTracker(model.getModuleTracker()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), scheduledModule);

        assertCommandSuccess(takeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        Module moduleInFilteredList = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Module scheduledModule = new ModuleBuilder(moduleInFilteredList).withAcademicCalendar(1, 1).build();
        AcademicYear year = new AcademicYear(1);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar = new AcademicCalendar(year, semester);
        TakeCommand takeCommand = new TakeCommand(INDEX_FIRST_MODULE, academicCalendar);

        String expectedMessage = String.format(TakeCommand.MESSAGE_SUCCESS, scheduledModule);

        Model expectedModel = new ModelManager(new ModuleTracker(model.getModuleTracker()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), scheduledModule);

        assertCommandSuccess(takeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);

        AcademicYear year = new AcademicYear(1);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar = new AcademicCalendar(year, semester);
        TakeCommand takeCommand = new TakeCommand(outOfBoundIndex, academicCalendar);

        assertCommandFailure(takeCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    /**
     * Schedule module in a filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidModuleIndexFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleTracker().getModuleList().size());

        AcademicYear year = new AcademicYear(1);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar = new AcademicCalendar(year, semester);
        TakeCommand takeCommand = new TakeCommand(outOfBoundIndex, academicCalendar);

        assertCommandFailure(takeCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AcademicYear year = new AcademicYear(1);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar = new AcademicCalendar(year, semester);
        final TakeCommand standardCommand = new TakeCommand(INDEX_FIRST_MODULE, academicCalendar);

        // same values -> returns true
        TakeCommand commandWithSameValues = new TakeCommand(INDEX_FIRST_MODULE, academicCalendar);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TakeCommand(INDEX_SECOND_MODULE, academicCalendar)));

        // different academic calendar -> returns false
        AcademicYear diffYear = new AcademicYear(2);
        Semester diffSemester = new Semester(2);
        AcademicCalendar diffAcademicCalendar = new AcademicCalendar(diffYear, diffSemester);
        assertFalse(standardCommand.equals(new TakeCommand(INDEX_FIRST_MODULE, diffAcademicCalendar)));
    }

}
