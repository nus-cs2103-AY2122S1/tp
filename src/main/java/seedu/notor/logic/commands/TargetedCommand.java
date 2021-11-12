package seedu.notor.logic.commands;

import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.Model;

public abstract class TargetedCommand implements Command {
    public abstract CommandResult execute() throws CommandException, ExecuteException;

    @Override
    public CommandResult execute(Model model) throws CommandException, ExecuteException {
        return this.execute();
    }
}
