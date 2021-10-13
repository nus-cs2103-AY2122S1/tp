package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.folder.FolderName;
import seedu.address.model.person.Person;

public class AddToFolderCommand extends Command {

    public static final String COMMAND_WORD = "echo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to folder in UNIon. "
            + "Parameters: "
            + "INDEX >> FOLDERNAME \n"
            + "Example: "
            + COMMAND_WORD + " "
            + "3 >> CS2103";

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contact has already been added to this folder";
    public static final String MESSAGE_NONEXISTENT_FOLDER = "This folder does not exist in UNIon";
    public static final String MESSAGE_SUCCESS = "Contact added to Folder: %1$s";

    private final Index index;
    private final FolderName folderName;

    /**
     * Creates a AddToFolderCommand to add the specified {@code FolderName}
     * @param index
     * @param folderName
     */
    public AddToFolderCommand(Index index, FolderName folderName) {
        requireNonNull(index);
        this.index = index;
        this.folderName = folderName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAdd = lastShownList.get(index.getZeroBased());

        if (!model.hasFolderName(folderName)) {
            throw new CommandException(MESSAGE_NONEXISTENT_FOLDER);
        } else if (model.folderContainsPerson(personToAdd, folderName)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        model.addContactToFolder(personToAdd, folderName);
        return new CommandResult(String.format(MESSAGE_SUCCESS, folderName));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddToFolderCommand)) {
            return false;
        }
        AddToFolderCommand that = (AddToFolderCommand) other;
        return this.index.equals(that.index)
                && this.folderName.equals(that.folderName);
    }
}
