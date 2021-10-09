package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.util.CollectionUtil.requireAllNonNull;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.commons.util.UserGuideReaderUtil;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String DEFAULT_SYNTAX = "`all`";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows program usage instructions for the command in query\n"
            + "Example: " + COMMAND_WORD + "add";
    public static final String SHOWING_HELP_MESSAGE = "Showing help.";
    public static final String MESSAGE_HELP_SUCCESS = "Show help for command: %1$s";

    private final String syntax;

    public HelpCommand() {
        this.syntax = DEFAULT_SYNTAX;
    }

    /**
     * Constructor for the Help command
     * @param syntax of the command needed to be clarified by the users
     */
    public HelpCommand(String syntax) {
        requireAllNonNull(syntax);
        this.syntax = syntax;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.syntax.equals(DEFAULT_SYNTAX)) {
            String generalHelp = UserGuideReaderUtil.getGeneralHelp();
            return new CommandResult(SHOWING_HELP_MESSAGE, generalHelp, true, false);
        }
        String specificHelp = UserGuideReaderUtil.getSpecificHelp(this.syntax);
        if (specificHelp.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_HELP_NOT_EXIST);
        }
        return new CommandResult(String.format(MESSAGE_HELP_SUCCESS, this.syntax),
                specificHelp, true, false);
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
