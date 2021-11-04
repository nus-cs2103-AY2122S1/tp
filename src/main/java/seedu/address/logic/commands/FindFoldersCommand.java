package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.folder.Folder;
import seedu.address.model.folder.FolderNameContainsKeywordsPredicate;

/**
 * Finds and lists all folders in UNIon whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindFoldersCommand extends Command {

    public static final String COMMAND_WORD = "find -folders";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all folders whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2103 group project";

    private final FolderNameContainsKeywordsPredicate predicate;

    public FindFoldersCommand(FolderNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFolderList(predicate);
        ObservableList<Folder> filteredFolders = model.getFilteredFolderList();
        assert filteredFolders != null : "filteredFolders should not be null";
        return new CommandResult(Messages.getMessageFoldersListedOverview(filteredFolders.size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindFoldersCommand // instanceof handles nulls
                && predicate.equals(((FindFoldersCommand) other).predicate)); // state check
    }
}
