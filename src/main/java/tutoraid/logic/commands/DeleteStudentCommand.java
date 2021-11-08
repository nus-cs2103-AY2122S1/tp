package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.student.Student;

/**
 * Deletes a student identified using its displayed index from the TutorAid.
 */
public class DeleteStudentCommand extends DeleteCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Deletes a student from TutorAid."
                    + "\nParameters: "
                    + "\nINDEX (must be a positive integer)"
                    + "\nExample:"
                    + "\n%1$s %2$s 1",
            COMMAND_WORD, COMMAND_FLAG);

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Successfully deleted %s.";

    private final Index targetIndex;

    public DeleteStudentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStudent(studentToDelete);
        model.deleteStudentFromLessons(studentToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete.toNameString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStudentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteStudentCommand) other).targetIndex)); // state check
    }
}
