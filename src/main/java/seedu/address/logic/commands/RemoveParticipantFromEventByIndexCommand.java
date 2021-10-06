package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.participant.Participant;

public class RemoveParticipantFromEventByIndexCommand extends Command {

    public static final String COMMAND_WORD = "removeParticipantByIndex";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": remove Participant with specified index from another event with specified index.\n"
        + "Parameters: \n"
        + "PARTICIPANT_INDEX "
        + "EVENT_INDEX "
        + "Example: " + COMMAND_WORD + "1 2";

    public static final String MESSAGE_REMOVE_PARTICIPANT_FROM_EVENT_SUCCESS =
        "Removed Participant: %1$s from event %2$s successfully";

    private final Index participantIndex;
    private final Index eventIndex;

    /**
     * Creates an RemoveParticipantFromEventByIndexCommand to remove {@code Participant} at
     * specified {@code participantIndex} from {@code event} at specified {@code eventIndex}
     */
    public RemoveParticipantFromEventByIndexCommand(Index participantIndex, Index eventIndex) {
        this.participantIndex = participantIndex;
        this.eventIndex = eventIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Participant> lastShownParticipantList = model.getFilteredParticipantList();
        List<Event> lastShownEventList = model.getFilteredEventList();

        Participant participantToRemove = lastShownParticipantList.get(participantIndex.getZeroBased());

        Event selectedEvent = lastShownEventList.get(eventIndex.getZeroBased());

        if (!selectedEvent.getParticipants().contains(participantToRemove)) {
            throw new CommandException("Participant " + participantToRemove.getFullName() + " doesn't exist in event!");
        }

        // remove participant
        selectedEvent.getParticipants().remove(participantToRemove);

        return new CommandResult(String.format(MESSAGE_REMOVE_PARTICIPANT_FROM_EVENT_SUCCESS,
            participantToRemove.getFullName(), selectedEvent.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RemoveParticipantFromEventByIndexCommand // instanceof handles nulls
            && participantIndex.equals(((RemoveParticipantFromEventByIndexCommand) other).participantIndex))
            && eventIndex.equals(((RemoveParticipantFromEventByIndexCommand) other).eventIndex); //state check
    }
}
