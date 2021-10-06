package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a progress to an exiting student in TutorAid. Updates the progress if one already exists.
 */
public class AddProgressCommand extends Command {

    public static final String COMMAND_WORD = "add -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a progress for a student in TutorAid identified "
            + "by the index number used in the last person listing. "
            + "Existing progress will be overwritten by the input.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + "PROGRESS\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "Finishes Prelims.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Add Progress command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
