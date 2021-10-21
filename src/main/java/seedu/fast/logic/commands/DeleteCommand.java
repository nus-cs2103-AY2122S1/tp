package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Person;
import seedu.fast.storage.JsonFastStorage;

/**
 * Deletes a person identified using it's displayed index from FAST.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "del";
    public static final int MULTIPLE_DELETE_LIMIT = 10;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number(s) used in the displayed person list.\n\n"
            + "Parameters: \nINDEX (must be a positive integer)\n\n"
            + "Example: \n" + COMMAND_WORD + " 1"
            + "Example: \n" + COMMAND_WORD + " 5 10 15";

    public static final String MESSAGE_SINGLE_DELETE_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_MULTIPLE_DELETE_SUCCESS = "Successfully deleted %1$s contacts from "
            + "your contact list.";
    public static final String MESSAGE_MULTIPLE_DELETE_FAILED_DUPLICATES = "Cannot delete the same contact twice! "
            + "(Cannot have duplicated index!)";
    public static final String MESSAGE_MULTIPLE_DELETE_FAILED_WITHIN_LIMIT = "The position/number of contacts "
            + "you want to delete cannot be more than the number of contacts you currently have!";
    public static final String MESSAGE_MULTIPLE_DELETE_FAILED_EXCEED_LIMIT = "You cannot delete more than "
            + MULTIPLE_DELETE_LIMIT + " contacts at one time!";
    public static final String MESSAGE_MULTIPLE_DELETE_INVALID_INDEX_DETECTED = "%1$s contact(s) has been deleted"
            + " before the invalid index at 'index %2$s' is detected.";

    private static final Logger logger = LogsCenter.getLogger(JsonFastStorage.class);

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

        if (indexArray.length > MULTIPLE_DELETE_LIMIT) {
            logger.info("Input contains more than 10 indexes.");
            throw new CommandException(MESSAGE_MULTIPLE_DELETE_FAILED_EXCEED_LIMIT);
        }

        if (indexArray.length > lastShownList.size()) {
            executeInvalidIndexScenario();
        }

        if (isSingleDelete()) {
            return executeSingleDelete(lastShownList, model);
        }

        if (isMultipleDelete()) {
            return executeMultipleDelete(lastShownList, model);
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

    private void checkDuplicates(Index[] array) throws CommandException {
        Set<Integer> set = new HashSet<>();
        for (Index index : array) {
            if (!set.add(index.getZeroBased())) {
                throw new CommandException(MESSAGE_MULTIPLE_DELETE_FAILED_DUPLICATES);
            }
        }
    }

    private void checkIndex(Index index, List<Person> lastShownList, String message) throws CommandException {
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(message);
        }
    }

    private void executeInvalidIndexScenario() throws CommandException {
        if (isSingleDelete()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } else {
            throw new CommandException(MESSAGE_MULTIPLE_DELETE_FAILED_WITHIN_LIMIT);
        }
    }

    private CommandResult executeSingleDelete(List<Person> lastShownList, Model model) throws CommandException {
        Index targetIndex = indexArray[0];

        checkIndex(targetIndex, lastShownList, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);

        return new CommandResult(String.format(MESSAGE_SINGLE_DELETE_SUCCESS, personToDelete));
    }

    private CommandResult executeMultipleDelete(List<Person> lastShownList, Model model) throws CommandException {
        checkDuplicates(indexArray);

        Index targetIndex;
        for (int i = 0; i < indexArray.length; i++) {
            targetIndex = Index.indexModifier(indexArray[i], i);
            String errorMsg = String.format(MESSAGE_MULTIPLE_DELETE_INVALID_INDEX_DETECTED, i,
                    indexArray[i].getOneBased());

            checkIndex(targetIndex, lastShownList, errorMsg);

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
        }

        return new CommandResult(String.format(MESSAGE_MULTIPLE_DELETE_SUCCESS, indexArray.length));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && Arrays.equals(indexArray, ((DeleteCommand) other).indexArray)); // state check
    }
}
