package seedu.notor.logic.commands;

import java.util.Arrays;
import java.util.List;

import seedu.notor.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand implements Command {
    public static final String COMMAND_WORD = "help";
    public static final List<String> COMMAND_WORDS = Arrays.asList("help", "h");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, false);
    }
}
