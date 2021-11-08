package seedu.plannermd.logic.commands;

import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;

public class ToggleCommand extends Command {
    public static final String COMMAND_WORD = "toggle";

    public static final String MESSAGE_TOGGLE_SUCCESS = "Showing list of %s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.toggleState();
        return new CommandResult(String.format(MESSAGE_TOGGLE_SUCCESS,
                model.getState().toString().toLowerCase() + "s"), false, true, false);
    }
}
