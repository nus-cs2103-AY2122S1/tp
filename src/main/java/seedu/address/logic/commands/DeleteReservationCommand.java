package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;
import seedu.address.model.reservation.Reservation;

/**
 * Deletes a reservation identified using it's displayed index from RHRH.
 */
public class DeleteReservationCommand extends Command {

    public static final String COMMAND_WORD = "deleter";

    public static final String MESSAGE_USAGE = CommandUtil.formatCommandWord(COMMAND_WORD)
            + ": Deletes the reservation identified by the index number used in the displayed reservation list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + CommandUtil.formatCommandWord(COMMAND_WORD) + " 1";

    public static final String MESSAGE_DELETE_RESERVATION_SUCCESS = "Deleted Reservation: %1$s";

    private final Index targetIndex;

    /**
     * Instantiates a {@code DeleteReservationCommand} with a given index
     */
    public DeleteReservationCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        assert this.targetIndex.getOneBased() > 0;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reservation> lastShownList = model.getFilteredReservationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESERVATION_DISPLAYED_INDEX);
        }

        Reservation toDelete = lastShownList.get(targetIndex.getZeroBased());
        assert toDelete != null;
        model.deleteReservation(toDelete);
        return new CommandResult(
                String.format(MESSAGE_DELETE_RESERVATION_SUCCESS, toDelete),
                false, false, false, false, false, true
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteReservationCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteReservationCommand) other).targetIndex)); // state check
    }
}
