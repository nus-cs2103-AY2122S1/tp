package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.folder.Folder;
import seedu.address.model.person.Person;


/**
 * Deletes the contact that belongs to an existing
 * folder in UNIon.
 */
public class DeletePersonFromFolderCommand extends Command {

    public static final String COMMAND_WORD = "rm";
    public static final String COMMAND_IDENTIFIER = ">>";

    public static final String MESSAGE_NO_SUCH_PERSON_IN_FOLDER = "This person does not exist in this folder";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number from the folder specified.\n"
            + "Parameters: INDEX (must be a positive integer) + "
            + "FOLDER_NAME (must exist in UNIon)\n"
            + "Example: "
            + COMMAND_WORD
            + " 1 " + COMMAND_IDENTIFIER + " CS2103";

    public static final String MESSAGE_DELETE_PERSON_FROM_FOLDER_SUCCESS = "Deleted person from folder: %1$s";

    private final Index targetIndex;
    private final Folder targetFolder;

    /**
     * Creates a DeletePersonFromFolderCommand to remove
     * the specified {@code targetIndex} of contact from the
     * {@code targetFolder}.
     * @param targetIndex Index from which contact is removed from folder.
     * @param targetFolder Folder in which contact is to be removed from.
     */
    public DeletePersonFromFolderCommand(
            Index targetIndex,
            Folder targetFolder) {
        requireAllNonNull(targetIndex, targetFolder);
        this.targetIndex = targetIndex;
        this.targetFolder = targetFolder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        List<Folder> lastShownFolderList = model.getFilteredFolderList();

        int indexOfFolder = lastShownFolderList.indexOf(targetFolder);

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_IN_UNION);
        }

        if (indexOfFolder == -1) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_FOLDER_IN_CURRENT_LIST);
        }

        Person personToRemove = lastShownList.get(targetIndex.getZeroBased());

        if (!model.folderContainsPerson(personToRemove,
                this.targetFolder.getFolderName())) {
            throw new CommandException(MESSAGE_NO_SUCH_PERSON_IN_FOLDER);
        }

        model.deletePersonFromFolder(personToRemove, targetFolder);
        return new CommandResult(
                String.format(MESSAGE_DELETE_PERSON_FROM_FOLDER_SUCCESS,
                targetFolder));
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePersonFromFolderCommand)) {
            return false;
        }

        DeletePersonFromFolderCommand otherCommand = (DeletePersonFromFolderCommand) other;

        return targetIndex.equals(otherCommand.targetIndex)
                && targetFolder.equals(otherCommand.targetFolder); // state check
    }
}
