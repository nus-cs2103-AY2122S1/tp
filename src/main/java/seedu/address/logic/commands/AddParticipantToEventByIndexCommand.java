package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.participant.Participant;

public class AddParticipantToEventByIndexCommand extends Command {

    public static final String COMMAND_WORD = "addParticipantByIndex";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": add Participant with specified index to another event with specified index.\n"
        + "Parameters: \n"
        + "PARTICIPANT_INDEX "
        + "EVENT_INDEX "
        + "Example: " + COMMAND_WORD + "1 2";

    public static final String MESSAGE_ADD_PARTICIPANT_TO_EVENT_SUCCESS =
        "Added Participant: %1$s to event %2$s successfully";

    private final Index participantIndex;
    private final Index eventIndex;

    /**
     * Creates an AddParticipantToEventCommandByIndex to add {@code Participant} at
     * specified {@code participantIndex} to {@code event} at specified {@code eventIndex}
     */
    public AddParticipantToEventByIndexCommand(Index participantIndex, Index eventIndex) {
        this.participantIndex = participantIndex;
        this.eventIndex = eventIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Participant> lastShownParticipantList = model.getFilteredParticipantList();
        List<Event> lastShownEventList = model.getFilteredEventList();

        Participant participantToAdd = lastShownParticipantList.get(participantIndex.getZeroBased());

        Event selectedEvent = lastShownEventList.get(eventIndex.getZeroBased());

        if (selectedEvent.getParticipants().contains(participantToAdd)) {
            throw new CommandException("Participant " + participantToAdd.getFullName() + " already exists!");
        }

        // add participant
        selectedEvent.getParticipants().add(participantToAdd);

        return new CommandResult(String.format(MESSAGE_ADD_PARTICIPANT_TO_EVENT_SUCCESS,
            participantToAdd.getFullName(), selectedEvent.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddParticipantToEventByIndexCommand // instanceof handles nulls
            && participantIndex.equals(((AddParticipantToEventByIndexCommand) other).participantIndex))
            && eventIndex.equals(((AddParticipantToEventByIndexCommand) other).eventIndex); //state check
    }
}
