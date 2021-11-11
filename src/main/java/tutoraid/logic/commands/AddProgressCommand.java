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
 * Adds a progress entry to an exiting student in TutorAid.
 * A student can have up to 10 progress entries. Adding a new progress entry when the student already has
 * 10 progress entries will result in the deletion of the oldest progress entry of the student while adding the new one
 * to the student.
 */
public class AddProgressCommand extends AddCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Adds a progress entry for a student. "
                    + "The 10 most recent progress entries are stored."
                    + "\nParameters:"
                    + "\nINDEX (must be a positive integer)"
                    + "  PROGRESS"
                    + "\nExample:"
                    + "\n%1$s %2$s 1 Finished Prelims",
            COMMAND_WORD, COMMAND_FLAG);

    public static final String MESSAGE_SUCCESS = "Successfully added progress: %1$s for %2$s. "
            + "\nShowing %2$s and his/her lessons.";

    private final Index targetIndex;
    private final Progress progress;

    /**
     * @param targetIndex of the student in the filtered student list to add a progress entry to
     * @param progress    Progress object to be added to the student
     */
    public AddProgressCommand(Index targetIndex, Progress progress) {
        this.targetIndex = targetIndex;
        this.progress = progress;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());

        studentToEdit.addProgress(this.progress);

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.viewStudent(studentToEdit);

        return new CommandResult(String.format(MESSAGE_SUCCESS, progress, studentToEdit.toNameString()));
    }
}
