package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventName;
import safeforhall.model.event.ResidentList;
import safeforhall.model.person.Person;

/**
 * Adds a resident to an event.
 */
public class IncludeCommand extends Command {

    public static final String COMMAND_WORD = "include";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds resident to the given event. "
            + "Parameters: "
            + CliSyntax.PREFIX_EVENT + "EVENT "
            + CliSyntax.PREFIX_INFORMATION + "INFORMATION \n"

            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_EVENT + "basketball training "
            + CliSyntax.PREFIX_INFORMATION + "A101, A102, A103";

    public static final String MESSAGE_SUCCESS = "Residents ( %s ) added to event %s";
    private final EventName eventName;
    private final ResidentList residentList;

    /**
     * Creates an IncludeCommand to add the specified {@code EventName} and {@code InformationList}
     */
    public IncludeCommand(EventName eventName, ResidentList residentList) {
        requireNonNull(eventName);
        requireNonNull(residentList);
        this.eventName = eventName;
        this.residentList = residentList;
    }

    /**
     * Checks if there are any person from {@code toAdd} who is already in {@code currentResidents}
     */
    public void checkForDuplicates(ArrayList<Person> toAdd, ArrayList<Person> currentResidents)
            throws CommandException {
        int foundDuplicate = 0;
        StringBuilder names = new StringBuilder();
        for (Person p : toAdd) {
            if (currentResidents.contains(p)) {
                if (foundDuplicate == 0) {
                    names.append(p.getName());
                } else {
                    names.append(", ").append(p.getName());
                }
                foundDuplicate++;
            }
        }
        if (foundDuplicate == 1) {
            throw new CommandException(names.toString() + " is already in this event");
        } else if (foundDuplicate > 1) {
            throw new CommandException(names.toString() + " are already in this event");
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ArrayList<Person> toAdd = model.toPersonList(residentList);
        Event event = model.getEvent(eventName);
        ArrayList<Person> currentResidents = model.getCurrentEventResidents(event.getResidents());

        checkForDuplicates(toAdd, currentResidents);

        String newResidents = event.addResidentsToEvent(currentResidents, toAdd);
        Event editedEvent = new Event(event.getEventName(), event.getEventDate(), event.getVenue(),
                event.getCapacity(), new ResidentList(newResidents));
        model.setEvent(event, editedEvent);

        model.updateFilteredEventList(Model.PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.stream()
                .map(p -> p.getName().toString()).reduce((x, y) -> x + ", " + y).get(), eventName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IncludeCommand // instanceof handles nulls
                && eventName.equals(((IncludeCommand) other).eventName));
    }
}
