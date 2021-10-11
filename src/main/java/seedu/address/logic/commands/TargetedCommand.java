package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.executors.exceptions.ExecuteException;
import seedu.address.model.Model;

public abstract class TargetedCommand extends Command {
    public abstract CommandResult execute() throws CommandException, ExecuteException;

    @Override
    public CommandResult execute(Model model) throws CommandException, ExecuteException {
        return this.execute();
    }
}
