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

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Shows the student identified by the index "
                    + "number as shown in the Student Panel."
                    + "\nParameters:"
                    + "\nINDEX (must be a positive integer)"
                    + "\nExample:"
                    + "\n%1$s %2$s 1",
            COMMAND_WORD, COMMAND_FLAG);

    public static final String MESSAGE_VIEW_STUDENT_SUCCESS = "Showing %s and his/her lessons.";

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
        return new CommandResult(String.format(MESSAGE_VIEW_STUDENT_SUCCESS, studentToView.toNameString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewStudentCommand // instanceof handles nulls
                && targetIndex.equals(((ViewStudentCommand) other).targetIndex)); // state check
    }
}
