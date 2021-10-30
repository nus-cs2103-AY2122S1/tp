package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.student.Student;

/**
 * Lists all details of a student in TutorAid to the user.
 */
public class ViewStudentCommand extends ViewCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_FLAG
            + ": Shows the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_FLAG + " 1";

    public static final String MESSAGE_VIEW_STUDENT_SUCCESS = "Viewing requested student";

    private final Index targetIndex;

    public ViewStudentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToView = lastShownList.get(targetIndex.getZeroBased());
        model.viewStudent(studentToView);
        return new CommandResult(MESSAGE_VIEW_STUDENT_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewStudentCommand // instanceof handles nulls
                && targetIndex.equals(((ViewStudentCommand) other).targetIndex)); // state check
    }
}
