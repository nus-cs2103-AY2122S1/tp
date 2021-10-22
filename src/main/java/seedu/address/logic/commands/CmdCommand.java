package seedu.address.logic.commands;

import seedu.address.model.Model;

public class CmdCommand extends Command {
    public static final String COMMAND_WORD = "cmd";

    public static final String SHOWING_CMD_MESSAGE = "Opened Command Summary.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CMD_MESSAGE, true, false, false);
    }
}
