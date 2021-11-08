package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a contact identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_DESCRIPTION = "Deletes the contact identified by the index number used "
            + "in the displayed person list.\n";
    public static final String COMMAND_EXAMPLE = "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + COMMAND_DESCRIPTION + COMMAND_EXAMPLE;

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted contact: %1$s";

    private final Index targetIndex;

    /**
     * Creates a {@code DeleteCommand} that deletes contact at {@code targetIndex}.
     *
     * @param targetIndex the index of the contact that is to be deleted.
     */
    public DeleteCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the {@code DeleteCommand} which deletes contact at {@code targetIndex}.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} regarding the status of the {@code DeleteCommand}.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    /**
     * Checks if {@code other} is equal to {@code this}.
     *
     * @param other the object to check if it is equal to {@code this}.
     * @return {@code boolean} indicating if it is equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
