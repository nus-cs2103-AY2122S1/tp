package safeforhall.logic.commands;

import safeforhall.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class SwitchCommand extends Command {

    public static final String COMMAND_WORD = "switch";
    public static final String PARAMETERS = "";
    public static final String SWITCH_SUCCESS_MESSAGE = "Switched tabs";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SWITCH_SUCCESS_MESSAGE, true);
    }
}
