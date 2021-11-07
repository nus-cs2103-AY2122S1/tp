package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.AddressBook;
import safeforhall.model.Model;
import safeforhall.model.event.Event;
import safeforhall.model.person.Person;

public class TraceCommand extends Command {

    public static final String COMMAND_WORD = "trace";
    public static final String PARAMETERS = "r/RESIDENT [d/DEPTH] [t/DURATION] ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Traces a resident's close contacts based on the "
            + "events they're involved in. \n"
            + "Parameters: "
            + CliSyntax.PREFIX_RESIDENT + "RESIDENT "
            + "[" + CliSyntax.PREFIX_DEPTH + "DEPTH] "
            + "[" + CliSyntax.PREFIX_DURATION + "DURATION] \n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_RESIDENT + "A210 "
            + CliSyntax.PREFIX_DEPTH + "2 "
            + CliSyntax.PREFIX_DURATION + "4 \n"
            + "Note: \n"
            + "     1. A resident can be identified either by full name or room \n"
            + "     2. Depth refers to the number of maximum links to reach resident in question \n"
            + "     3. Depth should be an integer >= 1 and will default to 1 \n"
            + "     4. Duration is in days and will default to 7\n";

    public static final String MESSAGE_FOUND_CONTACTS = "Found %1d close contacts at this depth: ";

    public static final Integer DEFAULT_DEPTH = 1;
    public static final Integer DEFAULT_DURATION = 7;

    private final String personInput;
    private final Integer depth;
    private final Integer duration;
    private Optional<Person> person;

    /**
     * Creates a TraceCommand to trace the depth-level contacts of the specified {@code Person}
     *
     * @param person The resident to trace (either name or room validated)
     */
    public TraceCommand(String person) {
        this.personInput = person;
        this.depth = DEFAULT_DEPTH;
        this.duration = DEFAULT_DURATION;
    }

    /**
     * Creates a TraceCommand to trace the depth-level contacts of the specified {@code Person}
     *
     * @param person The resident to trace (either name or room validated)
     * @param depth The depth of tracing
     */
    public TraceCommand(String person, Integer depth) {
        this.personInput = person;
        this.depth = depth;
        this.duration = DEFAULT_DURATION;
    }

    /**
     * Creates a TraceCommand to trace the depth-level contacts of the specified {@code Person}
     *
     * @param person The resident to trace (either name or room validated)
     * @param depth The depth of tracing
     * @param duration The number of days to trace back to (for events)
     */
    public TraceCommand(String person, Integer depth, Integer duration) {
        this.personInput = person;
        this.depth = depth;
        this.duration = duration;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AddressBook addressBook = (AddressBook) model.getAddressBook();
        this.person = addressBook.findPerson(this.personInput);

        if (this.person.isEmpty()) {
            throw new CommandException("No resident with this information '" + this.personInput + "' could be found");
        }

        List<Person> contacts = findCloseContacts(model, this.person.get());
        contacts.remove(this.person.get());

        model.updateFilteredPersonList(contacts::contains);
        return new CommandResult(
                String.format(MESSAGE_FOUND_CONTACTS, model.getFilteredPersonList().size()));
    }

    private ArrayList<Person> findCloseContacts(Model model, Person person) {
        Predicate<Event> predicate = event -> {
            LocalDate eventDate = event.getEventDate().toLocalDate();
            LocalDate today = LocalDate.now();
            long days = ChronoUnit.DAYS.between(eventDate, today);
            return days >= 0 && days <= this.duration;
        };
        ArrayList<Person> contacts = new ArrayList<>();
        contacts.add(person);
        for (int i = 0; i < this.depth; i++) {
            ArrayList<Person> copyOfContacts = new ArrayList<>(contacts);
            for (Person contact: contacts) {
                ArrayList<Event> relevantEvents = model.getPersonEvents(contact, predicate);
                addToContacts(copyOfContacts, relevantEvents);
            }
            contacts = copyOfContacts;
        }
        return contacts;
    }

    private void addToContacts(List<Person> contacts, ArrayList<Event> relevantEvents) {
        ArrayList<Person> finalContacts = new ArrayList<>(contacts);
        relevantEvents.forEach(event ->
                finalContacts.addAll(event.getResidentList().getResidents()));
        contacts.removeAll(contacts);
        contacts.addAll(finalContacts.stream()
                .distinct()
                .collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TraceCommand // instanceof handles nulls
                && this.personInput.equals(((TraceCommand) other).personInput)
                && this.depth.equals(((TraceCommand) other).depth)
                && this.duration.equals(((TraceCommand) other).duration)); // state check
    }
}
