package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.ObservableList;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.AddressBook;
import safeforhall.model.Model;
import safeforhall.model.event.Event;
import safeforhall.model.person.Person;

public class TraceCommand extends Command {

    public static final String COMMAND_WORD = "trace";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Traces a resident's close contacts based on the "
            + "events they're involved in. \n"
            + "Parameters: "
            + CliSyntax.PREFIX_RESIDENT + "RESIDENT "
            + "[" + CliSyntax.PREFIX_DEPTH + "DEPTH] \n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_RESIDENT + "A210 "
            + CliSyntax.PREFIX_DEPTH + "2\n"
            + "Note: \n"
            + "     1. A resident can be identified either by full name or room \n"
            + "     2. Depth refers to the number of maximum links to the resident in question \n"
            + "     3. Depth should be an integer >= 1 \n";

    public static final String MESSAGE_SUCCESS = "Imported resident information from csv";
    public static final String MESSAGE_FOUND_CONTACTS = "Found %1d close contacts at this depth: ";

    private final String personInput;
    private final Integer depth;
    private Optional<Person> person;

    /**
     * Creates a TraceCommand to trace the depth-level contacts of the specified {@code Person}
     *
     * @param person The resident to trace (either name or room validated)
     * @param depth The depth of tracing
     */
    public TraceCommand(String person, Integer depth) {
        this.personInput = person;
        this.depth = depth;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AddressBook addressBook = (AddressBook) model.getAddressBook();
        this.person = addressBook.findPerson(this.personInput);

        if (this.person.isEmpty()) {
            throw new CommandException("No resident with this information '" + this.personInput + "' could be found");
        }

        ArrayList<Person> contacts = findCloseContacts(model, this.person.get());
        contacts.remove(this.person.get());

        model.updateFilteredPersonList(contacts::contains);
        return new CommandResult(
                String.format(MESSAGE_FOUND_CONTACTS, model.getFilteredPersonList().size()));
    }

    private ArrayList<Person> findCloseContacts(Model model, Person person) {
        ObservableList<Event> allEvents = model.getFilteredEventList();
        ArrayList<Person> contacts = new ArrayList<>();
        contacts.add(person);
        for (int i = 0; i < this.depth; i++) {
            for (Person contact: contacts) {
                ArrayList<Event> relevantEvents = getEvents(allEvents, contact);
                for (Event e: relevantEvents) {
                    ArrayList<Person> attendees = e.getResidentList().getResidents();
                    for (Person attendee: attendees) {
                        if (!contacts.contains(attendee)) {
                            contacts.add(attendee);
                        }
                    }
                }
            }
        }
        return contacts;
    }

    private ArrayList<Event> getEvents(ObservableList<Event> allEvents, Person p) {
        ArrayList<Event> events = new ArrayList<>();
        for (Event e: allEvents) {
            if (e.getResidentList().getResidents().contains(p)) {
                if (!events.contains(e)) {
                    events.add(e);
                }
            }
        }
        return events;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TraceCommand // instanceof handles nulls
                && this.personInput.equals(((TraceCommand) other).personInput)
                && this.depth.equals(((TraceCommand) other).depth)); // state check
    }
}
