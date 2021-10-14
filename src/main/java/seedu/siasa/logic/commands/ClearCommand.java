package seedu.siasa.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.siasa.model.Model;
import seedu.siasa.model.Siasa;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "SIASA has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSiasa(new Siasa());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
