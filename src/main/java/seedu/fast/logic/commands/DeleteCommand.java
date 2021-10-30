package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
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
        + ": Deletes the client identified by the index number(s) used in the displayed client list.\n\n"
        + "Parameters: \nINDEX... (must be a positive integer)\n"
        + " OR \nINDEX-INDEX (a range of index).\n\n"
        + "Examples: \n" + COMMAND_WORD + " 1\n"
        + COMMAND_WORD + " 5 10 15\n"
        + COMMAND_WORD + " 5-10";

    public static final String MESSAGE_SINGLE_DELETE_SUCCESS = "Deleted client: %1$s";
    public static final String MESSAGE_MULTIPLE_DELETE_SUCCESS = "Successfully deleted %1$s contacts from "
        + "your client list.";
    public static final String MESSAGE_MULTIPLE_DELETE_FAILED_DUPLICATES = "Cannot delete the same client twice! "
        + "(Cannot have duplicated index!)";
    public static final String MESSAGE_MULTIPLE_DELETE_FAILED_WITHIN_LIMIT = "The position/number of clients "
        + "you want to delete cannot be more than the number of clients you currently have!";
    public static final String MESSAGE_MULTIPLE_DELETE_FAILED_EXCEED_LIMIT = "You cannot delete more than "
        + MULTIPLE_DELETE_LIMIT + " clients at one time!";
    public static final String MESSAGE_MULTIPLE_DELETE_INVALID_INDEX_DETECTED = "Unable to execute command!\n"
            + "One or more invalid index detected at: %1$s\n" + MESSAGE_MULTIPLE_DELETE_FAILED_WITHIN_LIMIT;

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

    private void sortOrder() {
        Arrays.sort(indexArray, Index::compareTo);
    }

    private ArrayList<Index> getInvalidIndex(List<Person> lastShownList) {
        ArrayList<Index> outOfBoundIndexArray = new ArrayList<>();

        for (Index index : indexArray) {
            if (index.getOneBased() > lastShownList.size()) {
                outOfBoundIndexArray.add(index);
            }
        }

        return outOfBoundIndexArray;
    }

    private String getInvalidIndexMessage(ArrayList<Index> arrayList) {
        String msg = "";
        for (Index index : arrayList) {
            msg = msg + index.getOneBased() + " ";
        }

        return msg.trim().replace(" ", ", ");
    }

    private void checkIndex(ArrayList<Index> indexArrayList, String message) throws CommandException {
        if (indexArrayList.size() > 0) {
            throw new CommandException(message);
        }
    }

    private void checkIndex(Index index, List<Person> lastShownList, String message) throws CommandException {
        if (index.getOneBased() > lastShownList.size()) {
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
        ArrayList<Index> invalidIndexList = getInvalidIndex(lastShownList);
        String invalidIndexString = getInvalidIndexMessage(invalidIndexList);
        String errorMsg = String.format(MESSAGE_MULTIPLE_DELETE_INVALID_INDEX_DETECTED, invalidIndexString);
        checkIndex(invalidIndexList, errorMsg);
        checkDuplicates(indexArray);
        sortOrder();

        Index targetIndex;
        for (int i = 0; i < indexArray.length; i++) {
            targetIndex = indexArray[i];

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
