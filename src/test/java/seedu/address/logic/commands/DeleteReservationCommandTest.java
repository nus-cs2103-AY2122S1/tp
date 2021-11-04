package seedu.address.logic.commands;

import static seedu.address.logic.commands.ReservationCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.ReservationCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalReservation.getTypicalRhrh;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.reservation.Reservation;

public class DeleteReservationCommandTest {
    private Model model = new ModelManager(getTypicalRhrh(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index firstIndex = Index.fromOneBased(1);
        Reservation toDelete = model.getFilteredReservationList().get(firstIndex.getZeroBased());
        DeleteReservationCommand command = new DeleteReservationCommand(firstIndex);

        String expectedMessage = String.format(
                DeleteReservationCommand.MESSAGE_DELETE_RESERVATION_SUCCESS,
                toDelete
        );

        ModelManager expectedModel = new ModelManager(model.getRhrh(), new UserPrefs());
        expectedModel.deleteReservation(toDelete);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReservationList().size() + 1);
        DeleteReservationCommand command = new DeleteReservationCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_RESERVATION_DISPLAYED_INDEX);
    }
}
