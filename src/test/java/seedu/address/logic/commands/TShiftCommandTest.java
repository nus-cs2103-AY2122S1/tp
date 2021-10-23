package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ShnPeriod;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code TShiftCommand}.
 */
public class TShiftCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validDaysShiftPositive() throws CommandException {
        int days = TShiftCommand.MAX_ABS_DAYS_VALUE;

        TShiftCommand tShiftCommand = new TShiftCommand(days);
        CommandResult commandResult = tShiftCommand.execute(model);
        String expectedMessage = TShiftCommand.MESSAGE_SUCCESS;
        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }

    @Test
    public void execute_validDaysShiftNegative() throws CommandException {
        int days = -TShiftCommand.MAX_ABS_DAYS_VALUE;

        TShiftCommand tShiftCommand = new TShiftCommand(days);
        CommandResult commandResult = tShiftCommand.execute(model);
        String expectedMessage = TShiftCommand.MESSAGE_SUCCESS;
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
    public void shiftingDates_validDays() {
        int days = TShiftCommand.MAX_ABS_DAYS_VALUE;
        TShiftCommand tShiftCommand = new TShiftCommand(days);
        ShnPeriod shnPeriod = new ShnPeriod("2020-01-01 => 2020-01-02");
        ShnPeriod shiftedShnPeriod = tShiftCommand.shiftShnPeriodEndDate(shnPeriod);
        assertEquals(days, shnPeriod.endDate.until(shiftedShnPeriod.endDate, ChronoUnit.DAYS));
    }

    @Test
    public void shiftingDates_validDays_validNegativeShift() {
        int days = -TShiftCommand.MAX_ABS_DAYS_VALUE;
        int daysApart = 10;
        assertTrue(Math.abs(days) > daysApart);

        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = startDate.plusDays(daysApart);
        TShiftCommand tShiftCommand = new TShiftCommand(days);
        ShnPeriod shnPeriod = new ShnPeriod(startDate, endDate);
        ShnPeriod shiftedShnPeriod = tShiftCommand.shiftShnPeriodEndDate(shnPeriod);
        assertEquals(startDate.plusDays(1), shiftedShnPeriod.endDate);
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
