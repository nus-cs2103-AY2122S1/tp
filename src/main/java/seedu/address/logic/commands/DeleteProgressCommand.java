package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.Progress;

/**
 * Delete a progress of an exiting student in TutorAid.
 */
public class DeleteProgressCommand extends Command {

    public static final String COMMAND_WORD = "del -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a progress from a student in TutorAid "
            + "identified by the index number used in the last student listing.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted progress: %1$s\nOf this student: %2$s";

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
        List<Student> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());
        Progress deletedProgress = studentToEdit.getProgress();

        Student editedStudent = new Student(
                studentToEdit.getStudentName(), studentToEdit.getStudentPhone(), studentToEdit.getParentName(),
                studentToEdit.getParentPhone(), new Progress("No Progress"), studentToEdit.getPaymentStatus());

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, deletedProgress, studentToEdit));
    }
}
