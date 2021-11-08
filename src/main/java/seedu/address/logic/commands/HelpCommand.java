package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String DEFAULT_MESSAGE = "\nRefer to the user guide: "
            + "https://ay2122s1-cs2103-f10-2.github.io/tp/UserGuide.html";
    public static final String INVALID_WORD = "Command given does not exist.";

    private String messageUsage;

    /**
     * Creates a HelpCommand with specific help messages
     */
    public HelpCommand(String message) {
        if (message.isEmpty()) {
            this.messageUsage = DEFAULT_MESSAGE;
        } else if (message.equals("invalid")) {
            this.messageUsage = INVALID_WORD + DEFAULT_MESSAGE;
        } else {
            this.messageUsage = message;
        }
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
