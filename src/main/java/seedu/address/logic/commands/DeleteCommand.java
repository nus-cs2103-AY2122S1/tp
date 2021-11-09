package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Deletes a module/student/task from TAB.
 */
public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete either a module, a student or a task\n"
            + "Parameters: Delete_Type (can be module, student or task)\n"
            + "Example: " + COMMAND_WORD + " module (...additional parameters)";


    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);


}
