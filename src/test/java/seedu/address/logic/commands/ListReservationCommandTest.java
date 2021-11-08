package seedu.address.logic.commands;

import static seedu.address.logic.commands.ListReservationCommand.SHOWING_SWITCH_MESSAGE;
import static seedu.address.logic.commands.ReservationCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ListReservationCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_reservationSwitch_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_SWITCH_MESSAGE,
                false, false, false, false, false, true);
        assertCommandSuccess(new ListReservationCommand(), model, expectedCommandResult, expectedModel);
    }
}
