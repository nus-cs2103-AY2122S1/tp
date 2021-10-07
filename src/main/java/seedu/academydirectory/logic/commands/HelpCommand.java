package seedu.academydirectory.logic.commands;

import static seedu.academydirectory.commons.util.CollectionUtil.requireAllNonNull;

import seedu.academydirectory.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows program usage instructions for the command in query\n"
            + "Example: " + COMMAND_WORD + "add";
    public static final String SHOWING_HELP_MESSAGE = "Showing help.";
    public static final String MESSAGE_HELP_SUCCESS = "Show help for command: %1$s";
    public static final String MESSAGE_ARGUMENTS = "Syntax: %2$s";
    private static final String[] APPROVED_COMMAND = {
        "add", "attendance", "clear", "delete", "edit",
        "exit", "find", "list", "retrieve"
    };

    private final String syntax;

    public HelpCommand() {
        this.syntax = null;
    }

    /**
     * Constructor for the Help command
     * @param syntax of the command needed to be clarified by the users
     */
    public HelpCommand(String syntax) {
        requireAllNonNull(syntax);
        this.syntax = syntax;
    }

    private String generateSuccessMessage(String syntax) {
        return String.format(MESSAGE_HELP_SUCCESS, syntax);
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HelpCommand)) {
            return false;
        }
        HelpCommand curr = (HelpCommand) obj;
        return curr.syntax.equals(this.syntax);
    }
}
