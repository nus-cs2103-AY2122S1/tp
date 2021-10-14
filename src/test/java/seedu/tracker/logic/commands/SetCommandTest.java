package seedu.tracker.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.tracker.model.Model;
import seedu.tracker.model.ModelManager;
import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.UserPrefs;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

public class SetCommandTest {
    private Model model = new ModelManager(getTypicalModuleTracker(), new UserPrefs());

    @Test
    public void execute_academicYearSpecified_success() {
        AcademicYear academicYear = new AcademicYear(2);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar = new AcademicCalendar(academicYear, semester);

        SetCommand setCommand = new SetCommand(academicCalendar);

        String expectedMessage = String.format(SetCommand.MESSAGE_SUCCESS_SEM, academicCalendar);

        Model expectedModel = new ModelManager(new ModuleTracker(model.getModuleTracker()), new UserPrefs());
        expectedModel.setCurrentSemester(academicCalendar);

        assertCommandSuccess(setCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_mcGoalSpecified_success() {
        Mc mcGoal = new Mc(188);

        SetCommand setCommand = new SetCommand(mcGoal);

        String expectedMessage = String.format(SetCommand.MESSAGE_SUCCESS_MC, mcGoal);

        Model expectedModel = new ModelManager(new ModuleTracker(model.getModuleTracker()), new UserPrefs());
        expectedModel.setMcGoal(mcGoal);

        assertCommandSuccess(setCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        AcademicYear year = new AcademicYear(1);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar = new AcademicCalendar(year, semester);
        final SetCommand standardCommand = new SetCommand(academicCalendar);

        // same values -> returns true
        SetCommand commandWithSameValues = new SetCommand(academicCalendar);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different academic calendar -> returns false
        AcademicYear diffYear = new AcademicYear(2);
        Semester diffSemester = new Semester(2);
        AcademicCalendar diffAcademicCalendar = new AcademicCalendar(diffYear, diffSemester);
        assertFalse(standardCommand.equals(new SetCommand(diffAcademicCalendar)));
    }
}
