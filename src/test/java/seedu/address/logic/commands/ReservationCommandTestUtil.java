package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Rhrh;
import seedu.address.model.reservation.Reservation;

public class ReservationCommandTestUtil {
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final int VALID_NUMBER_OF_PEOPLE_AMY = 5;
    public static final int VALID_NUMBER_OF_PEOPLE_BOB = 2;
    public static final String VALID_DATE_TIME_AMY = "2021-11-11T20:00";
    public static final String VALID_DATE_TIME_BOB = "2021-12-01T19:00";
    public static final String VALID_REMARK = "birthday party";

    public static final String REMARK_DESC = " " + PREFIX_REMARK + VALID_REMARK;

    public static final String INVALID_REMARK = "10% off";

    public static final String INVALID_REMARK_DESC = " " + PREFIX_REMARK + INVALID_REMARK;

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandResult expectedCommandResult, Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                false, false, false, false, false, true);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - RHRH, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Rhrh expectedRhrh = new Rhrh(actualModel.getRhrh());
        List<Reservation> expectedFilteredList = new ArrayList<>(actualModel.getFilteredReservationList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRhrh, actualModel.getRhrh());
        assertEquals(expectedFilteredList, actualModel.getFilteredReservationList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the reservation at the given {@code targetIndex} in the
     * {@code model}'s RHRH.
     */
    public static void showReservationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredReservationList().size());

        Reservation reservation = model.getFilteredReservationList().get(targetIndex.getZeroBased());
        model.updateFilteredReservationList(r -> r.equals(reservation));
        assertEquals(1, model.getFilteredReservationList().size());
    }
}
