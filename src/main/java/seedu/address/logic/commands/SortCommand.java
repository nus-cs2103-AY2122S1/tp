package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted alphabetically";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
