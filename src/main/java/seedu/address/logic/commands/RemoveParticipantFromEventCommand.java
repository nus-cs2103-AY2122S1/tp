package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.ParticipantId;

public class RemoveParticipantFromEventCommand extends Command {
    public static final String COMMAND_WORD = "removeParticipant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": add Participant with matching ID to an Event.\n"
        + "Parameters: \n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ADD_PARTICIPANT_TO_EVENT_SUCCESS =
        "Removed Participant: %1$s from event %1$s successfully";

    private final ParticipantId participantId;
    private final EventName eventName;

    /**
     * Creates an RemoveParticipantFromEventCommand to remove the specified {@code Participant} according to
     * specified {@code participantId} from {@code event} with {@code eventName}
     */
    public RemoveParticipantFromEventCommand(ParticipantId participantId, EventName eventName) {
        this.participantId = participantId;
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Participant> lastShownParticipantList = model.getFilteredParticipantList();
        List<Event> lastShownEventList = model.getFilteredEventList();

        boolean hasParticipant =
            lastShownParticipantList.stream().anyMatch(p -> p.getParticipantId().equals(participantId));


        if (!hasParticipant) {
            throw new CommandException("Participant of id: " + participantId + " not found, consider relisting the "
                + "participants using 'list'");
        }

        boolean hasEvent =
            lastShownEventList.stream().anyMatch(e -> e.getName().equals(eventName));

        if (!hasEvent) {
            throw new CommandException("Event " + eventName + "Not Found, consider relisting the events "
                + "using 'listEvents'");
        }


        Participant participantToRemove = lastShownParticipantList.stream()
            .filter(p -> p.getParticipantId().equals(participantId))
            .findFirst().get();

        Event selectedEvent = lastShownEventList.stream()
            .filter(e -> e.getName().equals(eventName))
            .findFirst().get();

        // add participant
        selectedEvent.getParticipants().remove(participantToRemove);

        return new CommandResult(String.format(MESSAGE_ADD_PARTICIPANT_TO_EVENT_SUCCESS,
            participantToRemove.getFullName(), eventName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RemoveParticipantFromEventCommand // instanceof handles nulls
            && participantId.equals(((RemoveParticipantFromEventCommand) other).participantId))
            && eventName.equals(((RemoveParticipantFromEventCommand) other).eventName); //state check
    }
}

