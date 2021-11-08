package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String USERGUIDE_URL =
            "https://ay2122s1-cs2103t-t12-2.github.io/tp/UserGuide.html";

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.\n"
            + "For more information visit: " + USERGUIDE_URL;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || other instanceof HelpCommand; // instanceof handles nulls
    }
}
