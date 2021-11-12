package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;

/**
 * Removes a currently used grading system. Existing grading system must be reset
 * before setting a new grading system.
 */
public class ResetGradeCommand extends Command {

    public static final String COMMAND_WORD = "resetGrade";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Resets the grading system. "
            + "All existing grades will be deleted.";

    public static final String MESSAGE_GRADING_SYSTEM_NOT_SET = "No grading system is in use currently!";
    public static final String MESSAGE_SUCCESS = "Grading system successfully reset";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasExistingGradingSystem()) {
            throw new CommandException(MESSAGE_GRADING_SYSTEM_NOT_SET);
        }

        model.resetGradingSystem();
        model.commitTeachBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
