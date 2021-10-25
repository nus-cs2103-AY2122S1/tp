package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.folder.Folder;

public class DeletePersonFromFolderCommand extends Command {

    public static final String COMMAND_WORD = "rm";
    public static final String COMMAND_IDENTIFIER = ">>";

    public static final String MESSAGE_NO_SUCH_FOLDER = "No such folder found in UNIon to be renamed";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number from the folder specified.\n"
            + "Parameters: INDEX (must be a positive integer) + "
            + "FOLDER_NAME (must exist in UNIon)\n"
            + "Example: "
            + COMMAND_WORD
            + " 1 " + COMMAND_IDENTIFIER + " CS2103";

    public static final String MESSAGE_DELETE_PERSON_FROM_FOLDER_SUCCESS = "Contact deleted from folder %1$s";

    private final Index targetIndex;
    private final Folder targetFolder;

    public DeletePersonFromFolderCommand(Index targetIndex, Folder targetFolder) {
        requireAllNonNull(targetIndex, targetFolder);
        this.targetIndex = targetIndex;
        this.targetFolder = targetFolder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasFolder(targetFolder)) {
            throw new CommandException(MESSAGE_NO_SUCH_FOLDER);
        }

        model.deletePersonFromFolder(targetIndex, targetFolder);
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

        return targetIndex.equals(otherCommand.targetIndex) &&
                targetFolder.equals(otherCommand.targetFolder); // state check
    }
}
