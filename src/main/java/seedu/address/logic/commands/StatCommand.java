package seedu.address.logic.commands;

import seedu.address.model.Model;

public class StatCommand extends Command {
    public static final String COMMAND_WORD = "stat";

    public static final String MESSAGE_SUCCESS = "Showed stats";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, null, true, false);
    }
}
