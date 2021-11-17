package socialite.logic.commands;

import static java.util.Objects.requireNonNull;

import socialite.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public static final String GENERIC_RESPONSE = "default";

    private final String answer;

    /**
     * Creates a HelpCommand with the formulated response.
     * @param response Response determined by HelpCommandParser
     */
    public HelpCommand(String response) {
        requireNonNull(response);
        answer = response;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (answer.equals(GENERIC_RESPONSE)) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false, false);
        } else {
            return new CommandResult(answer);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && answer.equals(((HelpCommand) other).answer));
    }
}
