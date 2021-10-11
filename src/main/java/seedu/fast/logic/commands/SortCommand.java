package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.fast.model.Model;

/**
 * Lists all persons in the FAST to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the people in FAST alphabetically \n\n"
            + "Example: \n" + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Sorted all persons alphabetically";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPerson();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
