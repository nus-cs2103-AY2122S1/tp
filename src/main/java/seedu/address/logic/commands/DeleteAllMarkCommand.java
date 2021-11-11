package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentMark;

/**
 * Deletes all marks of an existing student in the ClassMATE.
 */
public class DeleteAllMarkCommand extends Command {

    public static final String COMMAND_WORD = "deleteam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes All of Student's Mark\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_DELETE_ALL_MARK_STUDENT_SUCCESS = "Deleted All Marks of Student: %1$s";
    public static final String MESSAGE_NO_MARKS = "No Marks to Delete!";

    private final Index index;

    /**
     * @param index of the student in the filtered student list to delete marks of.
     */
    public DeleteAllMarkCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = deleteAllStudentMark(studentToEdit);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_DELETE_ALL_MARK_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with all the marks deleted.
     */
    private static Student deleteAllStudentMark(Student studentToEdit) throws CommandException {
        assert studentToEdit != null;
        if (studentToEdit.getMarks().isEmpty()) {
            throw new CommandException(MESSAGE_NO_MARKS);
        }
        List<StudentMark> updatedMarks = new ArrayList<>();
        return new Student(
                studentToEdit.getName(),
                studentToEdit.getPhone(),
                studentToEdit.getEmail(),
                studentToEdit.getAddress(),
                studentToEdit.getClassCode(),
                studentToEdit.getTags(),
                updatedMarks,
                studentToEdit.getTutorialGroups());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteAllMarkCommand)) {
            return false;
        }

        // state check
        DeleteAllMarkCommand e = (DeleteAllMarkCommand) other;
        return index.equals(e.index);
    }
}
