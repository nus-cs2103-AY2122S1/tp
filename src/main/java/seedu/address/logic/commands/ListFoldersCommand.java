package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_FOLDERS;

import seedu.address.model.Model;

/**
 * Lists all folders in the address book to the user.
 */
public class ListFoldersCommand extends Command {

    public static final String COMMAND_WORD = "ls -folders";

    public static final String MESSAGE_SUCCESS = "Listed all folders";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFolderList(PREDICATE_SHOW_ALL_FOLDERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
