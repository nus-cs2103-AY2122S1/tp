package seedu.tracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tracker.testutil.Assert.assertThrows;
import static seedu.tracker.testutil.TypicalModules.CS1231S;
import static seedu.tracker.testutil.TypicalModules.CS2030S;
import static seedu.tracker.testutil.TypicalModules.MA1521;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.tracker.model.Model;
import seedu.tracker.model.ModelManager;
import seedu.tracker.model.UserPrefs;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.ModuleInSpecificSemesterPredicate;

class ViewCommandTest {
    private Model model = new ModelManager(getTypicalModuleTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalModuleTracker(), new UserPrefs());

    @Test
    public void constructor_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewCommand(null));
    }

    @Test
    public void equals() {
        AcademicYear year = new AcademicYear(2);
        Semester semester = new Semester(1);
        AcademicCalendar firstAcademicCalendar = new AcademicCalendar(year, semester);

        year = new AcademicYear(1);
        semester = new Semester(1);
        AcademicCalendar secondAcademicCalendar = new AcademicCalendar(year, semester);

        ModuleInSpecificSemesterPredicate inY2S1Predicate =
                new ModuleInSpecificSemesterPredicate(firstAcademicCalendar);

        ModuleInSpecificSemesterPredicate inY1S1Predicate =
                new ModuleInSpecificSemesterPredicate(secondAcademicCalendar);

        ViewCommand viewY2S1Command = new ViewCommand(inY2S1Predicate);
        ViewCommand viewY1S1Command = new ViewCommand(inY1S1Predicate);

        // same object -> returns true
        assertTrue(viewY2S1Command.equals(viewY2S1Command));

        // same values -> returns true
        ViewCommand viewY2S1CommandCopy = new ViewCommand(inY2S1Predicate);
        assertTrue(viewY2S1Command.equals(viewY2S1CommandCopy));

        // different types -> returns false
        assertFalse(viewY2S1Command.equals(1));

        // null -> returns false
        assertFalse(viewY2S1Command.equals(null));

        // different academic year -> returns false
        assertFalse(viewY2S1Command.equals(viewY1S1Command));
    }

    @Test
    public void execute_zeroModuleInSemester_zeroModuleFound() {
        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS_ZERO);

        ModuleInSpecificSemesterPredicate predicate = preparePredicate(6, 1);

        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_oneModuleInSemester_oneModuleFound() {
        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS_ONE);

        ModuleInSpecificSemesterPredicate predicate = preparePredicate(1, 2);

        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2030S), model.getFilteredModuleList());
    }

    @Test
    public void execute_twoOrMoreModulesInSemester_twoOrMoreModulesFound() {
        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS_TWO_OR_MORE, 2);

        ModuleInSpecificSemesterPredicate predicate = preparePredicate(1, 1);

        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MA1521, CS1231S), model.getFilteredModuleList());
    }

    /**
     * Parses {@code userInput} into a {@code ModuleInSpecificSemesterPredicate}.
     */
    private ModuleInSpecificSemesterPredicate preparePredicate(int year, int sem) {
        AcademicYear academicYear = new AcademicYear(year);
        Semester semester = new Semester(sem);
        AcademicCalendar academicCalendar = new AcademicCalendar(academicYear, semester);
        return new ModuleInSpecificSemesterPredicate(academicCalendar);
    }
}
