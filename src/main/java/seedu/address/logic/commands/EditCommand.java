package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Edits the details of an existing student in the studentId book.
 */
public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits either a module, a student or a task\n"
            + "Parameters: Edit_Type (can be module, student or task)\n"
            + "Example: " + COMMAND_WORD + " module (...additional parameters)";


    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
