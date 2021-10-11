package seedu.anilist.logic.commands;

import seedu.anilist.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String USER_GUIDE_LINK = "https://ay2122s1-cs2103t-t10-4.github.io/tp/UserGuide.html";

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "These are the commands we currently support: \n"
            + CommandsList.getListOfCommandsAsString() + "\n"
            + "For more information, try inputting each command, or check out our user guide at:\n"
            + USER_GUIDE_LINK;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE);
    }
}
