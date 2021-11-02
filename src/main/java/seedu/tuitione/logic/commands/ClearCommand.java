package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.generateSuccess;

import seedu.tuitione.model.Model;
import seedu.tuitione.model.Tuitione;

/**
 * Clears the tuitione book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = generateSuccess("Data of TuitiONE has been cleared.");


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTuitione(new Tuitione());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
