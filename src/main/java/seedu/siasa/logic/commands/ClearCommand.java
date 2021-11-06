package seedu.siasa.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.siasa.commons.core.Messages;
import seedu.siasa.model.Model;
import seedu.siasa.model.Siasa;

/**
 * Clears the SIASA.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "SIASA has been cleared!";
    public static final String MESSAGE_CONFIRMATION = "Are you sure you wish to clear all contacts and policies?";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        boolean response = Warning.isUserConfirmingCommand(MESSAGE_CONFIRMATION);
        if (!response) {
            return new CommandResult(Messages.MESSAGE_CANCELLED_COMMAND);
        }
        model.setSiasa(new Siasa());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
