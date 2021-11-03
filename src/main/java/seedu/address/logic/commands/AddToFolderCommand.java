package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
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
            + "CONTACT_INDEX >> FOLDERNAME \n"
            + "Example: "
            + COMMAND_WORD + " "
            + "3 >> CS2103";

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contact has already been added to this folder";
    public static final String MESSAGE_DUPLICATE_INDEX_PASSED = "Repeated Indexes passed";
    public static final String MESSAGE_NONEXISTENT_FOLDER = "This folder does not exist in UNIon";
    public static final String MESSAGE_SUCCESS = "Contact added to Folder: %1$s";

    private final List<Index> indexList;
    private final FolderName folderName;

    /**
     * Creates a AddToFolderCommand to add the specified {@code FolderName}
     * @param indexList
     * @param folderName
     */
    public AddToFolderCommand(List<Index> indexList, FolderName folderName) {
        requireNonNull(indexList);
        this.indexList = indexList;
        this.folderName = folderName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        for (Index index : this.indexList) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_INDEX_EXCEEDS_LIST_SIZE);
            }

            Person personToAdd = lastShownList.get(index.getZeroBased());

            if (duplicateIndexPassed()) {
                throw new CommandException(MESSAGE_DUPLICATE_INDEX_PASSED);
            }
            if (!model.hasFolderName(folderName)) {
                throw new CommandException(MESSAGE_NONEXISTENT_FOLDER);
            }
            if (model.folderContainsPerson(personToAdd, folderName)) {
                throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
            }

        }
        for (Index index : this.indexList) {
            Person personToAdd = lastShownList.get(index.getZeroBased());
            model.addContactToFolder(personToAdd, folderName);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, folderName));
    }

    /**
     * Checks if user input contains repeated indexes
     * @return true if there are duplicate indexes
     */
    public boolean duplicateIndexPassed() {
        List<Index> uniqueIndexes = new ArrayList<>();
        for (Index index:this.indexList) {
            if (uniqueIndexes.contains(index)) {
                return true;
            }
            uniqueIndexes.add(index);
        }
        return false;
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
        return this.indexList.equals(that.indexList)
                && this.folderName.equals(that.folderName);
    }
}
