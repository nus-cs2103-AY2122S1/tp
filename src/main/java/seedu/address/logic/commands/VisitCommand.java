package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCURRENCE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Frequency;
import seedu.address.model.person.Occurrence;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;

/**
 * Changes the visit of an existing person in the address book.
 */
public class VisitCommand extends Command {

    public static final String COMMAND_WORD = "visit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the visit of the elderly identified "
            + "by the index number used in the last elderly listing. "
            + "Existing visit will be overwritten by the input. "
            + "Or add a recurring visit using optional flags for frequency and occurrence\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "VISIT_DATE "
            + "[" + PREFIX_FREQUENCY + "FREQUENCY "
            + PREFIX_OCCURRENCE + "OCCURRENCE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "2020-11-12 12:00 "
            + "or " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "2020-11-12 12:00 "
            + PREFIX_FREQUENCY + "Weekly "
            + PREFIX_OCCURRENCE + "2";

    public static final String MESSAGE_ADD_VISIT_SUCCESS = "Added visit to Elderly: %1$s";
    public static final String MESSAGE_DELETE_VISIT_SUCCESS = "Removed visit from Elderly: %1$s";
    public static final String MESSAGE_ADD_RECURRING_VISIT_SUCCESS = "Added recurring visit to Elderly: %1$s";
    public static final String MESSAGE_INVALID_OPTIONAL_FLAG = "Frequency cannot be empty for multiple occurrence.";

    private final Index index;
    private final Optional<Visit> visit;
    private final Optional<Frequency> frequency;
    private final Optional<Occurrence> occurrence;

    /**
     * @param index of the person in the filtered person list to edit the visit
     * @param visit of the person to be updated to
     * @param frequency of the recurring visit
     * @param occurrence of a recurring visit
     */
    public VisitCommand(Index index, Optional<Visit> visit,
                        Optional<Frequency> frequency, Optional<Occurrence> occurrence) {
        requireAllNonNull(index, visit);

        this.index = index;
        this.visit = visit;
        this.frequency = frequency;
        this.occurrence = occurrence;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (occurrence.get().isMoreThan(1) && frequency.get().equals(Frequency.EMPTY)) {
            throw new CommandException(MESSAGE_INVALID_OPTIONAL_FLAG);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getLanguage(),
                personToEdit.getAddress(), personToEdit.getLastVisit(), visit,
                frequency, occurrence, personToEdit.getHealthConditions());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        if (visit.isPresent() && visit.get().isOverdue()) {
            return new CommandResult(generateSuccessMessage(editedPerson), CommandWarning.PAST_NEXT_VISIT_WARNING);
        } else {
            return new CommandResult(generateSuccessMessage(editedPerson));
        }
    }

    /**
     * Generates a command execution success message based on whether the visit is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message;

        if (visit.get().value.isEmpty()) {
            // if the visit is empty
            message = MESSAGE_DELETE_VISIT_SUCCESS;
        } else if (frequency.get().isEmpty()) {
            // if the visit is not a recurring visit
            message = MESSAGE_ADD_VISIT_SUCCESS;
        } else {
            // if the visit is a recurring visit
            message = MESSAGE_ADD_RECURRING_VISIT_SUCCESS;
        }
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VisitCommand)) {
            return false;
        }

        // state check
        VisitCommand e = (VisitCommand) other;
        return index.equals(e.index)
                && visit.equals(e.visit);
    }
}
