package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EVENT_NOT_FOUND_IN_FILTERED_LIST;
import static seedu.address.commons.core.Messages.MESSAGE_PARTICIPANT_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANT_ID;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.ParticipantId;


public class AddParticipantToEventCommand extends Command {

    public static final String COMMAND_WORD = "addParticipant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": add Participant with matching ID to an Event.\n"
            + "Parameters: \n"
            + PREFIX_PARTICIPANT_ID + "PARTICIPANT_ID "
            + PREFIX_EVENT + " EVENT_NAME "
            + "Example: " + COMMAND_WORD + " " + PREFIX_PARTICIPANT_ID + "aleyeo " + PREFIX_EVENT + " 240Km Marathon";

    public static final String MESSAGE_ADD_PARTICIPANT_TO_EVENT_SUCCESS =
            "Added Participant: %1$s to event %2$s successfully";

    private final ParticipantId participantId;
    private final EventName eventName;

    /**
     * Creates an AddParticipantToEventCommand to add the specified {@code Participant} according to
     * specified {@code participantId} to {@code event} with {@code eventName}
     */
    public AddParticipantToEventCommand(ParticipantId participantId, EventName eventName) {
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
            throw new CommandException(
                    String.format(MESSAGE_PARTICIPANT_NOT_FOUND, participantId, ListCommand.COMMAND_WORD));
        }

        boolean hasEvent = lastShownEventList.stream().anyMatch(e -> e.getName().equals(eventName));

        if (!hasEvent) {
            throw new CommandException(
                    String.format(MESSAGE_EVENT_NOT_FOUND_IN_FILTERED_LIST, eventName, ListEventCommand.COMMAND_WORD));
        }


        Participant participantToAdd = lastShownParticipantList.stream()
                .filter(p -> p.getParticipantId().equals(participantId))
                .findFirst().get();

        Event selectedEvent = lastShownEventList.stream()
                .filter(e -> e.getName().equals(eventName))
                .findFirst().get();

        if (selectedEvent.hasParticipant(participantToAdd)) {
            throw new CommandException("Participant " + participantToAdd.getFullName() + " already exists!");
        }

        // add participant
        selectedEvent.addParticipant(participantToAdd);

        return new CommandResult(String.format(MESSAGE_ADD_PARTICIPANT_TO_EVENT_SUCCESS,
                participantToAdd.getFullName(), eventName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddParticipantToEventCommand // instanceof handles nulls
                && participantId.equals(((AddParticipantToEventCommand) other).participantId))
                && eventName.equals(((AddParticipantToEventCommand) other).eventName); //state check
    }
}
