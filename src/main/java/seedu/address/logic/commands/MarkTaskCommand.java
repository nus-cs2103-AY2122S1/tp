package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Mark a task of a student as done or undone.
 */
public abstract class MarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_SUCCESS = "Task %1$s has been marked";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark a task of a student as done or undone\n"
            + "Parameters: Mark a task as either done or undone\n"
            + "Example: " + COMMAND_WORD + " done (...additional parameters)";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
