package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Visit;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list. "
            + "Or delete the visit of that person using optional flag " + PREFIX_VISIT + ".\n"
            + "Parameters: [" + PREFIX_VISIT + "] INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + "or delete v/1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_VISIT_SUCCESS = "Deleted Visit for Person: %1$s";
    public static final String MESSAGE_NO_EXISTING_VISIT = "No Existing Visit for Person: %1$s";

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

        Person editedPerson =
                new Person(personToDelete.getName(), personToDelete.getPhone(), personToDelete.getLanguage(),
                personToDelete.getAddress(), personToDelete.getLastVisit(), EMPTY_VISIT, personToDelete.getTags());
        model.setPerson(personToDelete, editedPerson);

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
