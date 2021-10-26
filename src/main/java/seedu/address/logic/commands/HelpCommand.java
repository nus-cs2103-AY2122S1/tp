package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "man";

    public static final String DESCRIPTION = "Shows program usage instructions.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + DESCRIPTION + "\nExample: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private boolean isShowHelp = false;

    private String commandSpecified = null;

    /**
     * Constructor for a HelpCommand to show help window.
     */
    public HelpCommand() {
        isShowHelp = true;
    }

    /**
     * Constructor for a HelpCommand to show help for a specified command.
     * @param commandSpecified The command specified to show help for.
     */
    public HelpCommand(String commandSpecified) {
        this.commandSpecified = commandSpecified;
    }

    @Override
    public CommandResult execute(Model model) {
        if (isShowHelp) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        } else {
            
        }
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
