package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.event.Event;
import safeforhall.model.event.ResidentList;
import safeforhall.model.person.Person;

/**
 * Removes a resident from an event.
 */
public class ExcludeCommand extends Command {
    public static final String COMMAND_WORD = "exclude";
    public static final String PARAMETERS = "INDEX r/ROOMS/NAMES(COMMA_SEPARATED)";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes residents from the given event.\n"
            + "Parameters: "
            + "INDEX "
            + CliSyntax.PREFIX_RESIDENTS + "ROOM/NAME \n"

            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + CliSyntax.PREFIX_RESIDENTS + "A101, A102, A103";

    public static final String MESSAGE_SUCCESS = "%s removed from event %s";
    private final Index index;
    private final ResidentList residentList;

    /**
     * Creates an ExcludeCommand to add the specified {@code EventName} and {@code InformationList}
     */
    public ExcludeCommand(Index index, ResidentList residentList) {
        requireNonNull(index);
        requireNonNull(residentList);
        this.index = index;
        this.residentList = residentList;
    }

    /**
     * Checks if there are any person from {@code toAdd} who is already in {@code currentResidents}
     */
    public void checkAllExists(ArrayList<Person> toRemove, ArrayList<Person> currentResidents)
            throws CommandException {
        StringBuilder names = new StringBuilder();

        int foundInvalid = (int) toRemove.stream()
                .filter(person -> !currentResidents.contains(person))
                .count();

        toRemove.stream()
                .filter(person -> !currentResidents.contains(person))
                .forEach(person -> names.append(person.getName()).append(", "));

        String invalidNames = names.toString().replaceAll(", $", "");

        if (foundInvalid == 1) {
            throw new CommandException(invalidNames + " is not in this event");
        } else if (foundInvalid > 1) {
            throw new CommandException(invalidNames + " are not in this event");
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();
        Event event;
        try {
            event = lastShownList.get(index.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("Index given is invalid");
        }

        if (residentList.isEmpty()) {
            throw new CommandException("No person with this information '" + residentList.getResidentsDisplay()
                    + "' could be found");
        }

        ArrayList<Person> toRemove = model.toPersonList(residentList);
        ArrayList<Person> currentResidents = model.getCurrentEventResidents(event.getResidentList());
        checkAllExists(toRemove, currentResidents);

        Event editedEvent = createEditedEvent(event, toRemove);
        model.setEvent(event, editedEvent);
        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);

        String resultMsg = String.format(MESSAGE_SUCCESS, toRemove.stream()
                .map(p -> p.getName().toString()).reduce((x, y) -> x + ", " + y).get(), event.getEventName());
        return new CommandResult(resultMsg);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExcludeCommand // instanceof handles nulls
                && index.equals(((ExcludeCommand) other).index));
    }

    /**
     * Creates a new edited {@code Event} with the same fields as the given event, except residents which remove the
     * residents in {@code toRemove} from the current residents
     */
    public Event createEditedEvent(Event event, ArrayList<Person> toRemove) {
        String combinedDisplayString = event.getRemovedDisplayString(toRemove);
        String combinedStorageString = event.getRemovedStorageString(toRemove);

        return new Event(event.getEventName(), event.getEventDate(), event.getEventTime(),
                event.getVenue(), event.getCapacity(), new ResidentList(combinedDisplayString, combinedStorageString));
    }
}
