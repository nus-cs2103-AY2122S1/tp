package safeforhall.logic.commands.add;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.event.Event;
import safeforhall.model.event.ResidentList;
import safeforhall.model.person.Person;

public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String PARAMETERS = "n/NAME d/DATE t/TIME v/VENUE c/CAPACITY [r/RESIDENTS]";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the address book.\n"
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "NAME "
            + CliSyntax.PREFIX_DATE + "DATE "
            + CliSyntax.PREFIX_TIME + "TIME "
            + CliSyntax.PREFIX_VENUE + "VENUE "
            + CliSyntax.PREFIX_CAPACITY + "CAPACITY "
            + CliSyntax.PREFIX_RESIDENTS + "RESIDENTS \n"

            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "Football Training "
            + CliSyntax.PREFIX_DATE + "03-01-2021 "
            + CliSyntax.PREFIX_TIME + "0830 "
            + CliSyntax.PREFIX_VENUE + "NUS field "
            + CliSyntax.PREFIX_CAPACITY + "5 "
            + CliSyntax.PREFIX_RESIDENTS + "Alex Yeoh ";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book";
    public static final String MESSAGE_EXCEED_CAPACITY = "Number of residents to add exceed event capacity";
    public static final String MESSAGE_INVALID_RESIDENT = "%s is not a valid resident in the address book";
    public static final String EMPTY_STRING = "";

    private final Event toAdd;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        String invalidResident = model.getInvalidResident(toAdd);
        if (!invalidResident.equals(EMPTY_STRING) && !toAdd.hasNoResidents()) {
            throw new CommandException(String.format(MESSAGE_INVALID_RESIDENT, invalidResident));
        }

        ArrayList<Person> personList = model.toPersonList(toAdd.getResidentList());
        if (personList.size() > toAdd.getCapacity().capacity) {
            throw new CommandException(MESSAGE_EXCEED_CAPACITY);
        }

        String combinedStorageString = toAdd.getCombinedStorageString(personList);
        Event editedEvent = new Event(toAdd.getEventName(), toAdd.getEventDate(), toAdd.getEventTime(),
                toAdd.getVenue(), toAdd.getCapacity(), new ResidentList(personListToString(personList),
                combinedStorageString));
        model.addEvent(editedEvent);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }

    /**
     * Converts a {@code personList} to a String, separated by a comma
     */
    public String personListToString(ArrayList<Person> personList) {
        StringBuilder stringBuilder;
        if (personList.isEmpty()) {
            return ResidentList.DEFAULT_LIST;
        } else {
            stringBuilder = new StringBuilder("");
        }
        int count = 0;
        for (Person p : personList) {
            if (count == 0) {
                stringBuilder.append(p.getName());
            } else {
                stringBuilder.append(", ").append(p.getName());
            }
            count++;
        }
        return stringBuilder.toString();
    }
}
