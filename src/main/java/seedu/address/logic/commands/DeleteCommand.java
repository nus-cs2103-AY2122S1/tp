package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String MESSAGE_USAGE =
            "delete: Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: delete 1 or delete 1-3";

    public static final String MESSAGE_NUMBER_DELETED_PERSON = "%d Deleted Persons: \n";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "%1$s \n";

    private final Index targetIndex;
    private final Index endIndex;

    /**
     * Creates a DeleteCommand to delete the person at specified index
     *
     * @param targetIndex the person to be deleted
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        endIndex = targetIndex;
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
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        int first = targetIndex.getZeroBased();
        int last = endIndex.getZeroBased();

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
        String successMessage = String.format(MESSAGE_NUMBER_DELETED_PERSON, endIndex.getZeroBased() - first + 1)
                + deletedPersons;
        return new CommandResult(successMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)
                && endIndex.equals(((DeleteCommand) other).endIndex)); // state check
    }
}
