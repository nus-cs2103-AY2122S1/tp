package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Classmate;
import seedu.address.model.Model;

/**
 * Clears the ClassMATE.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ClassMATE has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setClassmate(new Classmate());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
