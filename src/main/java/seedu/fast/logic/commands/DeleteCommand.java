package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from FAST.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "del";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n\n"
            + "Parameters: \nINDEX (must be a positive integer)\n\n"
            + "Example: \n" + COMMAND_WORD + " 1";

    public static final String MESSAGE_SINGLE_DELETE_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_MULTIPLE_DELETE_SUCCESS = "Successfully deleted %1$s contacts from "
            + "your contact list.";
    public static final String MESSAGE_MULTIPLE_DELETE_FAILED = "The number of contacts you want to delete "
            + "cannot be more than the number of contacts you currently have!";

    private final Index[] indexArray;

    /**
     * Constructor for {@code DeleteCommand}
     *
     * @param indexArray Array of indexes of the contacts to be deleted.
     */
    public DeleteCommand(Index[] indexArray) {
        this.indexArray = indexArray;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (indexArray.length > lastShownList.size()) {
            if (isSingleDelete()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            throw new CommandException(MESSAGE_MULTIPLE_DELETE_FAILED);
        }

        if (isSingleDelete()) {
            int singleDeleteIndexPosition = 0;
            Index targetIndex = indexArray[singleDeleteIndexPosition];
            executeSingleDelete(lastShownList, model, targetIndex);

            return new CommandResult(String.format(MESSAGE_SINGLE_DELETE_SUCCESS,
                    lastShownList.get(targetIndex.getZeroBased())));
        }

        if (isMultipleDelete()) {
            executeMultipleDelete(lastShownList, model);
            return new CommandResult(String.format(MESSAGE_MULTIPLE_DELETE_SUCCESS, indexArray.length));
        }

        // should never reach here
        throw new CommandException(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private boolean isSingleDelete() {
        return indexArray.length == 1;
    }

    private boolean isMultipleDelete() {
        return indexArray.length > 1;
    }

    private boolean isIndexLongerThanEqualToList(int index, int sizeOfList) {
        return index >= sizeOfList;
    }

    private void deletePersonFromModel(List<Person> lastShownList, Model model, int position) {
        Person personToDelete = lastShownList.get(position);
        model.deletePerson(personToDelete);
    }

    private void executeSingleDelete(List<Person> lastShownList, Model model, Index targetIndex)
            throws CommandException {

        if (isIndexLongerThanEqualToList(targetIndex.getZeroBased(), lastShownList.size())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        deletePersonFromModel(lastShownList, model, targetIndex.getZeroBased());

    }

    private void executeMultipleDelete(List<Person> lastShownList, Model model) throws CommandException {
        Index targetIndex;
        for (int i = 0; i < indexArray.length; i++) {
            targetIndex = Index.indexModifier(indexArray[i], i);

            if (isIndexLongerThanEqualToList(targetIndex.getZeroBased(), lastShownList.size())) {
                throw new CommandException(String.format("%1$s contact(s) has been deleted "
                                + "before the invalid index at 'index %2$s' is detected.",
                        i, indexArray[i].getOneBased()));
            }

            deletePersonFromModel(lastShownList, model, targetIndex.getZeroBased());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && Arrays.equals(indexArray, ((DeleteCommand) other).indexArray)); // state check
    }
}
