package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes multiple contacts identified using the displayed index range from the address book.
 */
public class DeleteMultipleCommand extends Command {
    public static final String COMMAND_WORD = "deletem";
    public static final String INDEX_SPLITTER = "-";
    public static final String COMMAND_DESCRIPTION = "Deletes a range of contacts identified "
            + "by the index numbers ( Both inclusive ) "
            + "in the displayed person list.\n";
    public static final String COMMAND_EXAMPLE = "Parameters: START_INDEX - END_INDEX \n"
            + "Example: " + COMMAND_WORD + " 8 " + INDEX_SPLITTER + " 14";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + COMMAND_DESCRIPTION + COMMAND_EXAMPLE;
    public static final String MESSAGE_DELETE_MULTIPLE_PERSON_SUCCESS = "Deleted contacts: ";
    public static final String MESSAGE_CONSTRAINTS = "START_INDEX cannot be larger than END_INDEX \n";
    public static final String MESSAGE_INVALID_INDEX = Index.MESSAGE_INVALID_INDEX;

    private final Index startIndex;
    private final Index endIndex;

    /**
     * Deletes multiple contacts between the range of start index and end index both inclusive.
     *
     * @param startIndex displayed index of first contact to be deleted.
     * @param endIndex displayed index of last contact to be deleted.
     */
    public DeleteMultipleCommand(Index startIndex, Index endIndex) {
        requireAllNonNull(startIndex, endIndex);
        checkArgument(areValidIndexes(startIndex, endIndex), MESSAGE_CONSTRAINTS);
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    /**
     * Checks if {@code startIndex} and {@endIndex} are valid indexes.
     *
     * @param startIndex The index of the first person to delete.
     * @param endIndex The index of the last person to delete.
     * @return if {@code startIndex} and {@endIndex} are valid indexes.
     */
    public static boolean areValidIndexes(Index startIndex, Index endIndex) {
        requireAllNonNull(startIndex, endIndex);
        return startIndex.getZeroBased() <= endIndex.getZeroBased();
    }

    private boolean haveEqualIndexes(DeleteMultipleCommand other) {
        return startIndex.equals(other.startIndex)
                && endIndex.equals(other.endIndex);
    }

    /**
     * Executes {@code DeleteMultipleCommand} which deletes the contacts from {@code startIndex} to {@code endIndex}.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} regarding the status of the {@code DeleteMultipleCommand}.
     * @throws CommandException If invalid indexes are provided.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (startIndex.getZeroBased() >= lastShownList.size()
                || endIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        StringBuilder result = new StringBuilder(MESSAGE_DELETE_MULTIPLE_PERSON_SUCCESS);
        for (int i = startIndex.getZeroBased(); i <= endIndex.getZeroBased(); i++) {
            Person personToDelete = lastShownList.get(startIndex.getZeroBased());
            model.deletePerson(personToDelete);
            result.append(String.format("\n%1$s", personToDelete));
        }
        return new CommandResult(result.toString());
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
                || (other instanceof DeleteMultipleCommand // instanceof handles nulls
                    && haveEqualIndexes((DeleteMultipleCommand) other)); // both indexes are equal
    }
}
