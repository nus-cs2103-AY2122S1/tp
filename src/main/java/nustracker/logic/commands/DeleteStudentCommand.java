package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_STUDENTID;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Deletes a student identified by his/her student ID.
 */
public class DeleteStudentCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = DeleteCommand.COMMAND_WORD
            + ": Deletes a student identified by Student ID.\n"
            + "Parameters: " + DeleteCommand.COMMAND_WORD + "[Student ID]\n"
            + "Example: " + DeleteCommand.COMMAND_WORD + PREFIX_STUDENTID + "e1234567";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final StudentId studentId;

    public DeleteStudentCommand(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model,
                                 CurrentlyShownList currentlyShownList) throws CommandException {
        requireNonNull(model);

        Student toDelete = model.getStudent(studentId);

        if (toDelete == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_STUDENTID, studentId));
        }

        model.deleteStudent(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStudentCommand // instanceof handles nulls
                && studentId.equals(((DeleteStudentCommand) other).studentId)); // state check
    }
}
