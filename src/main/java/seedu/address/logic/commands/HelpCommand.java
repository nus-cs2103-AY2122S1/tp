package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    private final String messageUsage;
    /**
     * Creates a HelpCommand with specific help messages
     */
    public HelpCommand(String message) {
        this.messageUsage = message;
    }

    public String getMessageUsage() {
        return this.messageUsage;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(messageUsage, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && this.getMessageUsage().equals(((HelpCommand) other).getMessageUsage()));
    }
}
