package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fast.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.fast.model.Model;

/**
 * Lists all persons in FAST to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all your clients saved in FAST\n\n"
            + "Example: \n" + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
