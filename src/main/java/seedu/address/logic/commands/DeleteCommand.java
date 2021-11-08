package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.TuitionClass;

/**
 * Deletes students from TutAssistor.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String SHORTCUT = "del";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the students identified by the index numbers used in the Students list.\n"
            + "Parameters: STUDENT_INDEX [STUDENT INDEX]... (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_STUDENTS_SUCCESS = "Deleted Students: %1$s.\n";
    public static final String MESSAGE_DELETE_STUDENTS_FAILURE = "Students at index: %1$s are not found.";
    private List<Index> studentIndices = new ArrayList<>();
    private List<String> removed = new ArrayList<>();
    private List<Integer> invalidStudents = new ArrayList<>();

    /**
     * Constructor for DeleteCommand using a list of student indexes.
     *
     * @param studentIndices List of student indexes.
     */
    public DeleteCommand(List<Index> studentIndices) {
        this.studentIndices = studentIndices;
        this.removed = new ArrayList<>();
        this.invalidStudents = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        for (Index studentIndex: studentIndices) {
            Student studentToDelete = model.getStudent(studentIndex);
            if (studentToDelete == null) {
                invalidStudents.add(studentIndex.getOneBased());
                continue;
            }
            for (Integer tuitionClassId : studentToDelete.getClassesArray()) {
                TuitionClass tuitionClass = model.getClassById(tuitionClassId);
                if (tuitionClass != null) {
                    TuitionClass updatedClass = tuitionClass.removeStudent(studentToDelete);
                    model.setTuition(tuitionClass, updatedClass);
                }
            }
            removed.add(studentToDelete.getName().fullName);
            model.deleteStudent(studentToDelete);
        }
        if (!invalidStudents.isEmpty() && removed.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_DELETE_STUDENTS_FAILURE, invalidStudents));
        }
        String feedback = (!removed.isEmpty()
                ? String.format(MESSAGE_DELETE_STUDENTS_SUCCESS, removed) : "")
                + (!invalidStudents.isEmpty()
                        ? String.format(MESSAGE_DELETE_STUDENTS_FAILURE, invalidStudents) : "");
        return new CommandResult(feedback);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand e = (DeleteCommand) other;
        List<Index> otherIndex = e.studentIndices;
        if (studentIndices.size() != otherIndex.size()) {
            return false;
        }
        for (int i = 0; i < studentIndices.size(); i++) {
            if (!studentIndices.get(i).equals(otherIndex.get(i))) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
