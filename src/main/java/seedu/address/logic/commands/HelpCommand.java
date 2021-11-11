package seedu.address.logic.commands;

import seedu.address.logic.state.ApplicationState;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand implements Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private final ApplicationState currentApplicationState;

    /**
     * Creates a HelpCommand to display the help message.
     *
     * @param currentApplicationState
     */
    public HelpCommand(ApplicationState currentApplicationState) {
        this.currentApplicationState = currentApplicationState;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult.Builder(SHOWING_HELP_MESSAGE)
                .goShowHelp()
                .setNextApplicationState(currentApplicationState)
                .build();
    }
}
