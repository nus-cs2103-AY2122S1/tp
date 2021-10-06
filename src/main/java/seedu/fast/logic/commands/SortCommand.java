package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.fast.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all persons alphabetically";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPerson();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
