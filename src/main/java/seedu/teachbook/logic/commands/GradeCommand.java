package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.teachbook.model.gradeobject.GradingSystem.NOT_GRADED;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.teachbook.commons.core.Messages;
import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.student.Student;

/**
 * Edits a student's grade. A grading system must be present in order to edit the grades.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the grade of one or more students identified "
            + "by the index number used in the displayed student list.\n"
            + "Parameters: INDEX...||all "
            + PREFIX_GRADE + "[GRADE]\n"
            + "Examples: "
            + COMMAND_WORD + " 1 " + PREFIX_GRADE + "C, "
            + COMMAND_WORD + " 2 4 5 " + PREFIX_GRADE + "B, "
            + COMMAND_WORD + " all " + PREFIX_GRADE + "A";

    public static final String MESSAGE_ADD_GRADE_SUCCESS = "Given Grade %1$s to Student(s): %2$s";
    public static final String MESSAGE_CLEAR_GRADE_SUCCESS = "Cleared grade(s) of Student(s): %2$s";
    public static final String MESSAGE_GRADING_SYSTEM_NOT_SET = "Set a grading system before editing any grade";
    public static final String MESSAGE_INVALID_GRADE = "Invalid grade\nCurrent grading system: %1$s";
    public static final String MESSAGE_NOTHING_TO_GRADE = "There is nothing to grade as there are no students.";

    private final Grade grade;
    private final List<Index> targetIndices;
    private final boolean isAll;

    /**
     * Creates a new GradeCommand when the index of the student to grade is given along with the grade.
     * @param targetIndices Index of the student to grade.
     * @param grade Grade to be given to the student.
     */
    public GradeCommand(List<Index> targetIndices, Grade grade) {
        this.grade = grade;
        this.targetIndices = targetIndices;
        this.isAll = false;
    }

    /**
     * Creates a new GradeCommand when there is no student to grade.
     * @param grade Grade to be assigned to the specified student.
     */
    public GradeCommand(Grade grade) {
        this.grade = grade;
        targetIndices = new ArrayList<>();
        this.isAll = true;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Although there is a noticeable code duplication to MarkCommand, we decided to not extract out the
        // similarities because delete, grade, mark, and unmark are different commands after all
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (isAll) {
            if (lastShownList.size() == 0) {
                throw new CommandException(MESSAGE_NOTHING_TO_GRADE);
            }
            for (int i = lastShownList.size() - 1; i >= 0; i--) {
                targetIndices.add(Index.fromZeroBased(i));
            }
        } else {
            // Ensures all indices are valid before executing the command
            for (Index targetIndex : targetIndices) {
                if (targetIndex.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
                }
            }
        }

        if (!model.hasExistingGradingSystem()) {
            throw new CommandException(MESSAGE_GRADING_SYSTEM_NOT_SET);
        }

        if (!model.isValidGrade(grade)) {
            throw new CommandException(String.format(MESSAGE_INVALID_GRADE, model.getGradingSystem()));
        }

        List<String> studentToGradeNames = new ArrayList<>();
        for (Index targetIndex : targetIndices) {
            Student studentToGrade = lastShownList.get(targetIndex.getZeroBased());
            studentToGradeNames.add(studentToGrade.getName().toString());
            Student editedStudent = new Student(studentToGrade.getName(), studentToGrade.getPhone(),
                    studentToGrade.getStudentClass(), studentToGrade.getEmail(),
                    studentToGrade.getAddress(), studentToGrade.getRemark(), studentToGrade.getTags(),
                    studentToGrade.getAttendance(), grade);
            model.setStudent(studentToGrade, editedStudent);
        }
        Collections.reverse(studentToGradeNames);

        model.commitTeachBook();
        return new CommandResult(generateSuccessMessage(studentToGradeNames));
    }

    /**
     * Generates a command execution success message based on whether grades are added to or removed from students.
     */
    private String generateSuccessMessage(List<String> studentToGradeNames) {
        String message = !grade.equals(NOT_GRADED) ? MESSAGE_ADD_GRADE_SUCCESS : MESSAGE_CLEAR_GRADE_SUCCESS;
        return String.format(message, grade, String.join(", ", studentToGradeNames));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradeCommand // instanceof handles nulls
                && targetIndices.equals(((GradeCommand) other).targetIndices)
                && grade.equals(((GradeCommand) other).grade));
    }

}
