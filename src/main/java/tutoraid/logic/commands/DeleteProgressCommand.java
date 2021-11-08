package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.student.Progress;
import tutoraid.model.student.Student;


/**
 * Delete progress of an existing student in TutorAid.
 */
public class DeleteProgressCommand extends DeleteCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Deletes a progress entry from a student."
                    + "\nParameters:"
                    + "\nINDEX (must be a positive integer)"
                    + "\nExample:"
                    + "\n%1$s %2$s 1",
            COMMAND_WORD, COMMAND_FLAG);

    public static final String MESSAGE_SUCCESS = "Successfully deleted progress: %1$s from %2$s."
            + "\nShowing %2$s and his/her lessons.";

    private final Index targetIndex;

    /**
     * @param targetIndex of the student in the filtered student list to delete progress
     */
    public DeleteProgressCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());

        if (studentToEdit.isProgressListEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NO_PROGRESS_TO_DELETE);
        }
        Progress progressToDelete = studentToEdit.deleteLatestProgress();

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.viewStudent(studentToEdit);

        return new CommandResult(String.format(MESSAGE_SUCCESS, progressToDelete, studentToEdit.toNameString()));
    }
}
