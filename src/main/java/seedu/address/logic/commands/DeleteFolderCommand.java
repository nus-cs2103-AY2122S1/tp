package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.folder.Folder;

/**
 * Deletes folder name specified by user.
 */
public class DeleteFolderCommand extends Command {

    public static final String COMMAND_WORD = "rmdir";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a folder in UNIon. "
            + "Parameters: "
            + "FOLDER_NAME \n"
            + "Example: "
            + COMMAND_WORD + " "
            + "CS2103";

    public static final String MESSAGE_SUCCESS = "Folder deleted: %1$s";
    public static final String MESSAGE_NO_SUCH_FOLDER = "Folder name supplied"
            + " cannot be found in the folders' listing below";

    private final Folder folderToRemove;

    /**
     * Creates a DeleteFolderCommand to remove the specified {@code Folder}
     * @param folder
     */
    public DeleteFolderCommand(Folder folder) {
        requireNonNull(folder);

        this.folderToRemove = folder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Folder> lastShownFolderList = model.getFilteredFolderList();
        int indexOfFolder = lastShownFolderList.indexOf(folderToRemove);

        if (indexOfFolder == -1) {
            throw new CommandException(MESSAGE_NO_SUCH_FOLDER);
        }

        model.deleteFolder(folderToRemove);
        return new CommandResult(String.format(MESSAGE_SUCCESS, folderToRemove));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteFolderCommand)) {
            return false;
        }
        DeleteFolderCommand that = (DeleteFolderCommand) other;
        return Objects.equals(folderToRemove, that.folderToRemove);
    }

}
