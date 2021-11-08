package seedu.programmer.logic.commands;

import seedu.programmer.model.Model;

/**
 * Shows full help instructions for every command in UI.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows program usage instructions.\n"
            + "Example: "
            + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public HelpCommandResult execute(Model model) {
        return new HelpCommandResult(SHOWING_HELP_MESSAGE);
    }
}
