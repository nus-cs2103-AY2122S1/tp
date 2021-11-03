package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.folder.Folder;

public class CreateFolderCommand extends Command {

    public static final String COMMAND_WORD = "mkdir";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a folder in UNIon. "
            + "Parameters: "
            + "FOLDER_NAME \n"
            + "Example: "
            + COMMAND_WORD + " "
            + "School";

    public static final String MESSAGE_SUCCESS = "New folder added: %1$s";
    public static final String MESSAGE_DUPLICATE_FOLDER = "This folder already exists in UNIon";
    public static final String MESSAGE_FOLDER_NAME_TOO_LONG = "This folder name is too long!"
            + "Please keep it to a maximum of 30 chars";

    private final Folder folderToAdd;

    /**
     * Creates a CreateFolderCommand to add the specified {@code Folder}
     * @param folder
     */
    public CreateFolderCommand(Folder folder) {
        requireNonNull(folder);

        this.folderToAdd = folder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.hasFolder(folderToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FOLDER);
        }

        if (folderToAdd.getFolderName().toString().length() > 30) {
            throw new CommandException(MESSAGE_FOLDER_NAME_TOO_LONG);
        }

        model.addFolder(folderToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, folderToAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CreateFolderCommand)) {
            return false;
        }
        CreateFolderCommand that = (CreateFolderCommand) other;
        return Objects.equals(folderToAdd, that.folderToAdd);
    }
}
