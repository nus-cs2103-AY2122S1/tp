package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String MESSAGE_USAGE = "delete: "
            + "Deletes the person identified by the index number used in the displayed person list "
            + "or by module code.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MODULE_CODE + "MODULE CODE\n"
            + "Example: delete 1 , delete 1-3 , delete "
            + PREFIX_MODULE_CODE + "CS2040S";

    public static final String MESSAGE_NUMBER_DELETED_PERSON = "%d Deleted Persons: \n";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "%1$s \n";

    private final Index targetIndex;
    private final Index endIndex;
    private final Predicate<Person> predicate;

    /**
     * Creates a DeleteCommand to delete the person at specified index
     *
     * @param targetIndex the person to be deleted
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        endIndex = targetIndex;
        predicate = null;
    }

    /**
     * Creates a DeleteCommand to delete the persons between the specified indexes.
     *
     * @param targetIndex the first person to be deleted
     * @param endIndex the last person to be deleted
     */
    public DeleteCommand(Index targetIndex, Index endIndex) {
        this.targetIndex = targetIndex;
        this.endIndex = endIndex;
        predicate = null;
    }

    public DeleteCommand(ModuleCodesContainsKeywordsPredicate predicate) {
        targetIndex = null;
        endIndex = null;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean hasPredicate = predicate != null;
        if (hasPredicate) {
            model.updateFilteredPersonList(predicate);
        }
        int first = hasPredicate ? 0 : targetIndex.getZeroBased();
        int last = hasPredicate ? model.getFilteredPersonList().size() - 1 : endIndex.getZeroBased();
        int size = last;
        List<Person> lastShownList = model.getFilteredPersonList();

        if (first >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (first > last || last >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RANGE);
        }

        StringBuilder deletedPersons = new StringBuilder();
        while (last >= first) {
            Person personToDelete = lastShownList.get(last);
            model.deletePerson(personToDelete);
            deletedPersons.insert(0, String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
            last--;
        }
        String successMessage = String.format(MESSAGE_NUMBER_DELETED_PERSON, size + 1) + deletedPersons;
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(successMessage);
    }
    /*int last, first;
        if (predicate != null) {
            model.updateFilteredPersonList(predicate);
            first = 0; //first item to delete in the filtered list
            last = model.getFilteredPersonList().size() - 1;
        } else {
            first = targetIndex.getZeroBased();
            last = endIndex.getZeroBased();
        }*/

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)
                && endIndex.equals(((DeleteCommand) other).endIndex)); // state check
    }
}
