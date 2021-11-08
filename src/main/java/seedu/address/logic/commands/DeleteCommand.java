package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_EXISTING_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;
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
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the elderly identified by the index number used in the displayed elderly list. "
            + "Or delete the visit of that elderly using optional flag " + PREFIX_VISIT + ".\n"
            + "Parameters: [" + PREFIX_VISIT + "] INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + "or " + COMMAND_WORD + " v/1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Elderly: %1$s";
    public static final String MESSAGE_DELETE_VISIT_SUCCESS = "Deleted Visit for Elderly: %1$s";

    private static final Optional<Visit> EMPTY_VISIT = Optional.ofNullable(new Visit(""));

    private final Index targetIndex;
    private final boolean isVisit;

    /**
     * @param targetIndex of the person in the filtered person list to delete
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.isVisit = false;
    }

    /**
     * @param targetIndex of the person in the filtered person list to delete (the visit)
     * @param isVisit indicates if the command is to delete the person or the visit
     */
    public DeleteCommand(Index targetIndex, boolean isVisit) {
        this.targetIndex = targetIndex;
        this.isVisit = isVisit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (isVisit) {
            return deleteVisit(model, personToDelete);
        } else {
            return deletePerson(model, personToDelete);
        }
    }

    private CommandResult deleteVisit(Model model, Person personToDelete) throws CommandException {
        if (personToDelete.getVisit().equals(EMPTY_VISIT)) {
            throw new CommandException(String.format(MESSAGE_NO_EXISTING_VISIT, personToDelete));
        }

        // if frequency and occurrence are not empty, delete visit will delete rest of the recurring
        // visits, resets frequency and occurrence
        Person editedPerson =
                new Person(personToDelete.getName(), personToDelete.getPhone(), personToDelete.getLanguage(),
                personToDelete.getAddress(), personToDelete.getLastVisit(), EMPTY_VISIT, Optional.of(Frequency.EMPTY),
                        Optional.of(new Occurrence(1)), personToDelete.getHealthConditions());
        model.setPerson(personToDelete, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_VISIT_SUCCESS, personToDelete));
    }

    private CommandResult deletePerson(Model model, Person personToDelete) {
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
