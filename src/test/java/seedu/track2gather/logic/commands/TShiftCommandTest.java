package seedu.track2gather.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.track2gather.logic.commands.exceptions.CommandException;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.ModelManager;
import seedu.track2gather.model.UserPrefs;
import seedu.track2gather.model.person.attributes.Period;
import seedu.track2gather.model.person.attributes.ShnPeriod;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code TShiftCommand}.
 */
public class TShiftCommandTest {

    private Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());

    @Test
    public void execute_validDaysShiftPositive_success() throws CommandException {
        int days = TShiftCommand.MAX_ABS_DAYS_VALUE;

        TShiftCommand tShiftCommand = new TShiftCommand(days);
        CommandResult commandResult = tShiftCommand.execute(model);
        String expectedMessage = String.format(TShiftCommand.MESSAGE_SUCCESS, days);
        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void execute_validDaysShiftNegative_success() throws CommandException {
        int days = -TShiftCommand.MAX_ABS_DAYS_VALUE;

        TShiftCommand tShiftCommand = new TShiftCommand(days);
        CommandResult commandResult = tShiftCommand.execute(model);
        String expectedMessage = String.format(TShiftCommand.MESSAGE_SUCCESS, days);
        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void execute_zeroDaysShift_throwsCommandException() {
        TShiftCommand tShiftCommand = new TShiftCommand(0);
        assertCommandFailure(tShiftCommand, model, TShiftCommand.MESSAGE_TSHIFT_BY_ZERO);
    }

    @Test
    public void execute_shiftBeyondLimit_throwsCommandException() {
        String expectedMessage = String.format(TShiftCommand.MESSAGE_BEYOND_LIMIT, TShiftCommand.MAX_ABS_DAYS_VALUE);

        TShiftCommand tShiftCommandPlus = new TShiftCommand(TShiftCommand.MAX_ABS_DAYS_VALUE + 1);
        assertCommandFailure(tShiftCommandPlus, model, expectedMessage);

        tShiftCommandPlus = new TShiftCommand(TShiftCommand.MAX_ABS_DAYS_VALUE + 100);
        assertCommandFailure(tShiftCommandPlus, model, expectedMessage);

        TShiftCommand tShiftCommandMinus = new TShiftCommand(-TShiftCommand.MAX_ABS_DAYS_VALUE - 1);
        assertCommandFailure(tShiftCommandMinus, model, expectedMessage);

        tShiftCommandMinus = new TShiftCommand(-TShiftCommand.MAX_ABS_DAYS_VALUE - 100);
        assertCommandFailure(tShiftCommandMinus, model, expectedMessage);
    }

    @Test
    public void singlePeriod_validDaysShiftPositive_success() {
        int days = TShiftCommand.MAX_ABS_DAYS_VALUE;
        TShiftCommand tShiftCommand = new TShiftCommand(days);
        ShnPeriod shnPeriod = new ShnPeriod(new Period("2020-01-01 => 2020-01-02"));
        ShnPeriod shiftedShnPeriod = tShiftCommand.shiftShnPeriodEndDate(shnPeriod);
        assertTrue(shnPeriod.value.isPresent());
        assertTrue(shiftedShnPeriod.value.isPresent());
        LocalDate endDate = shnPeriod.value.get().getEndDate();
        LocalDate shiftedEndDate = shiftedShnPeriod.value.get().getEndDate();
        assertEquals(days, endDate.until(shiftedEndDate, ChronoUnit.DAYS));
    }

    @Test
    public void singlePeriod_validDaysShiftNegativeBeyondStart_success() {
        int days = -TShiftCommand.MAX_ABS_DAYS_VALUE;
        int daysApart = 10;
        assertTrue(Math.abs(days) > daysApart);

        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = startDate.plusDays(daysApart);

        TShiftCommand tShiftCommand = new TShiftCommand(days);
        ShnPeriod shnPeriod = new ShnPeriod(new Period(startDate, endDate));
        ShnPeriod shiftedShnPeriod = tShiftCommand.shiftShnPeriodEndDate(shnPeriod);
        assertTrue(shiftedShnPeriod.value.isPresent());

        LocalDate shiftedEndDate = shiftedShnPeriod.value.get().getEndDate();
        assertEquals(startDate.plusDays(1), shiftedEndDate);
    }

    @Test
    public void singlePeriod_validDaysShiftNegativeWithinStart_success() {
        int days = -3;
        int daysApart = 10;
        assertTrue(Math.abs(days) < daysApart);

        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = startDate.plusDays(daysApart);

        TShiftCommand tShiftCommand = new TShiftCommand(days);
        ShnPeriod shnPeriod = new ShnPeriod(new Period(startDate, endDate));
        ShnPeriod shiftedShnPeriod = tShiftCommand.shiftShnPeriodEndDate(shnPeriod);
        assertTrue(shiftedShnPeriod.value.isPresent());

        LocalDate shiftedEndDate = shiftedShnPeriod.value.get().getEndDate();
        assertEquals(startDate.plusDays(7), shiftedEndDate);
    }

    @Test
    public void equals() {
        TShiftCommand tShiftCommand1 = new TShiftCommand(-1);
        TShiftCommand tShiftCommand2 = new TShiftCommand(0);
        TShiftCommand tShiftCommand3 = new TShiftCommand(1);

        // same object -> returns true
        assertTrue(tShiftCommand1.equals(tShiftCommand1));
        assertTrue(tShiftCommand2.equals(tShiftCommand2));
        assertTrue(tShiftCommand3.equals(tShiftCommand3));

        // different types -> returns false
        assertFalse(tShiftCommand1.equals(1));

        // null -> returns false
        assertFalse(tShiftCommand1.equals(null));

        // different person -> returns false
        assertFalse(tShiftCommand1.equals(tShiftCommand2));
        assertFalse(tShiftCommand2.equals(tShiftCommand3));
        assertFalse(tShiftCommand3.equals(tShiftCommand1));
    }
}
