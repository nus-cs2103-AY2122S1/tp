package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK;
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
 * Edits the details of an existing student in the ClassMATE.
 */
public class AddLastMarkCommand extends Command {

    public static final String COMMAND_WORD = "addlm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds Mark to Student's Weekly Marks\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MARK + "LOW";

    public static final String MESSAGE_ADD_MARK_STUDENT_SUCCESS = "Added Mark to Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "Mark to add must be provided.";

    private final Index index;
    private final StudentMark mark;

    /**
     * @param index of the student in the filtered student list to add last mark.
     * @param studentMark marks to add to Student.
     */
    public AddLastMarkCommand(Index index, StudentMark studentMark) {
        requireNonNull(index);
        requireNonNull(studentMark);

        this.index = index;
        this.mark = studentMark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = addStudentMark(studentToEdit, mark);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_ADD_MARK_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the {@code StudentMark} added.
     */
    private static Student addStudentMark(Student studentToEdit, StudentMark mark) {
        assert studentToEdit != null;
        List<StudentMark> updatedMarks = new ArrayList<>(studentToEdit.getMarks());
        updatedMarks.add(mark);
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
        if (!(other instanceof AddLastMarkCommand)) {
            return false;
        }

        // state check
        AddLastMarkCommand e = (AddLastMarkCommand) other;
        return index.equals(e.index)
                && mark.equals(e.mark);
    }
}
