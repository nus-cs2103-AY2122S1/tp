package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Delete a progress of an exiting student in TutorAid.
 */
public class DeleteProgressCommand extends Command {

    public static final String COMMAND_WORD = "del -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a progress from a student in TutorAid "
            + "identified by the index number used in the last person listing.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Delete Progress command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
