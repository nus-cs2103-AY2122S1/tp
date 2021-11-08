package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.smartnus.model.Model;
import seedu.smartnus.model.SmartNus;

/**
 * Clears SmartNUS.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "SmartNUS has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSmartNus(new SmartNus());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
