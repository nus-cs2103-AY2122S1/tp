package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Participant;

public class RemoveNextOfKinCommand extends Command {

    public static final String COMMAND_WORD = "deleteNok";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the next of kin with specified index from a "
            + "participant with another specified index.\n"
            + "Parameters: \n"
            + "PARTICIPANT_INDEX "
            + "NOK_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_SUCCESS = "Removed \n%1$s \nfrom %2$s successfully";

    private final Index participantIndex;
    private final Index nextOfKinIndex;

    /**
     * This is a constructor for AddNextOfKinCommand.
     *
     * @param participantIndex index of participant to remove next of kin.
     * @param nextOfKinIndex index of next of kin to be removed.
     */
    public RemoveNextOfKinCommand(Index participantIndex, Index nextOfKinIndex) {
        requireNonNull(participantIndex);
        requireNonNull(nextOfKinIndex);
        this.participantIndex = participantIndex;
        this.nextOfKinIndex = nextOfKinIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Participant> lastShownParticipantList = model.getFilteredParticipantList();

        Participant selectedParticipant;

        try {
            selectedParticipant = lastShownParticipantList.get(participantIndex.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX);
        }

        NextOfKin selectedNextOfKin;

        try {
            selectedNextOfKin = selectedParticipant.getNextOfKin(nextOfKinIndex.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX);
        }

        selectedParticipant.removeNextOfKin(selectedNextOfKin);

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                selectedNextOfKin.getFullName(), selectedParticipant.getFullName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RemoveNextOfKinCommand
                && participantIndex.equals(((RemoveNextOfKinCommand) other).participantIndex))
                && nextOfKinIndex.equals(((RemoveNextOfKinCommand) other).nextOfKinIndex);
    }
}
