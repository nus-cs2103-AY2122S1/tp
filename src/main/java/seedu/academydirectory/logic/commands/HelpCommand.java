package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.util.CollectionUtil.requireAllNonNull;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.logic.AdditionalViewType;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.VersionedModel;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String DEFAULT_MESSAGE = Messages.GENERAL_HELP_MESSAGE;
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows program usage instructions for the command in query\n"
            + "Example: " + COMMAND_WORD + "add";
    public static final String MESSAGE_HELP_SUCCESS_GENERAL = "Showing summary help.";
    public static final String MESSAGE_HELP_SUCCESS_SPECIFIC = "Show help for command: %1$s.";

    private final String commandWord;
    private final String helpMessage;
    private final boolean isGeneralHelp;

    /**
     * Default constructor of help, resulting in a summary table of all command formats on the help window.
     */
    public HelpCommand() {
        this.commandWord = COMMAND_WORD;
        this.helpMessage = DEFAULT_MESSAGE;
        this.isGeneralHelp = true;
    }

    /**
     * Constructor for the Help command
     * @param helpMessage of the command needed to be clarified by the users
     */
    public HelpCommand(String commandWord, String helpMessage) {
        requireAllNonNull(helpMessage, commandWord);
        this.commandWord = commandWord;
        this.helpMessage = helpMessage;
        this.isGeneralHelp = false;
    }

    @Override
    public CommandResult execute(VersionedModel model) throws CommandException {
        requireNonNull(model);
        model.setAdditionalViewType(AdditionalViewType.HELP);
        model.setAdditionalInfo(AdditionalInfo.of(helpMessage));
        CommandResult commandResult;
        if (isGeneralHelp) {
            commandResult = new CommandResult(MESSAGE_HELP_SUCCESS_GENERAL, true, false);
        } else {
            commandResult = new CommandResult(String.format(MESSAGE_HELP_SUCCESS_SPECIFIC, this.commandWord),
                    true, false);
        }
        return commandResult;
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
        return curr.helpMessage.equals(this.helpMessage)
                && curr.commandWord.equals(this.commandWord);
    }
}
