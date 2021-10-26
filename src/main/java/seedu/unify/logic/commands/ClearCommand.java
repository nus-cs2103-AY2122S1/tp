package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.unify.model.Model;
import seedu.unify.model.UniFy;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Uni-Fy has been cleared of all the tasks!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setUniFy(new UniFy());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
