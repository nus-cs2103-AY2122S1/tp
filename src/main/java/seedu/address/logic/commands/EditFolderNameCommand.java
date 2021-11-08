package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.folder.Folder;

/**
 * Edits the name of an existing folder in UNIon.
 */
public class EditFolderNameCommand extends Command {

    public static final String COMMAND_WORD = "mv";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Renames the folder identified by the old "
            + "folder name used in the displayed folders list.\n"
            + "Parameters: OLD_FOLDER_NAME (Name must be an existing folder)"
            + " + NEW_FOLDER_NAME (Name must not already exist in folder list)\n"
            + "Example: " + COMMAND_WORD + " OLD_FOLDER_NAME | " + "NEW_FOLDER_NAME";

    public static final String MESSAGE_SUCCESS_EDIT_FOLDER_NAME = "Folder updated to: %1$s";
    public static final String MESSAGE_DUPLICATE_FOLDER = "This folder already exists in UNIon";
    public static final String MESSAGE_SAME_FOLDER_NAME_ENTERED = "This folder name is the same as the current one";
    public static final String MESSAGE_FOLDER_NAME_TOO_LONG = "This folder name is too long! "
            + "Please keep it to a maximum of 30 chars.";

    private final Folder oldFolder;
    private final Folder newFolder;

    /**
     * Creates a EditFolderNameCommand to update
     * the specified {@code oldFolder} to the
     * {@code newFolder} name.
     * @param oldFolder folder name to be changed.
     * @param newFolder new folder to replace old folder.
     */
    public EditFolderNameCommand(Folder oldFolder, Folder newFolder) {
        requireAllNonNull(oldFolder, newFolder);
        this.oldFolder = oldFolder;
        this.newFolder = newFolder;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (newFolder.getFolderName().equals(oldFolder.getFolderName())) {
            throw new CommandException(MESSAGE_SAME_FOLDER_NAME_ENTERED);
        }

        List<Folder> lastShownFolderList = model.getFilteredFolderList();
        int indexOfFolder = lastShownFolderList.indexOf(oldFolder);
        if (indexOfFolder == -1) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_FOLDER_IN_CURRENT_LIST);
        }

        if (model.hasFolder(newFolder)) {
            throw new CommandException(MESSAGE_DUPLICATE_FOLDER);
        }
        if (newFolder.getFolderName().toString().length() > 30) {
            throw new CommandException(MESSAGE_FOLDER_NAME_TOO_LONG);
        }

        model.setNewFolder(oldFolder, newFolder);
        return new CommandResult(String.format(MESSAGE_SUCCESS_EDIT_FOLDER_NAME, newFolder));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditFolderNameCommand)) {
            return false;
        }

        // state check
        EditFolderNameCommand e = (EditFolderNameCommand) other;
        return this.oldFolder.equals(e.oldFolder)
                && this.newFolder.equals(e.newFolder);
    }

}
