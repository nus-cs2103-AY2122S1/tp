package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.tuitione.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static seedu.tuitione.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.tuitione.model.lesson.LessonCode.isValidLessonCode;

import java.util.List;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.student.Student;

public class EnrollCommand extends Command {

    public static final String COMMAND_WORD = "enroll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrolls a specified student "
            + "from a given TuitiONE lesson\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "LESSON_CODE\n"
            + "Example: " + "enroll 1 " + PREFIX_LESSON + "Science-P5-Wed-1230";

    public static final String MESSAGE_STUDENT_IN_LESSON = "%1$s is already enrolled in the existing %2$s";
    public static final String MESSAGE_UNABLE_TO_ENROLL = "%1$s cannot be enrolled into %2$s";
    public static final String MESSAGE_LESSON_NOT_FOUND = "Lesson does not exist, please try again";
    public static final String MESSAGE_SUCCESS = "%1$s enrolled into lesson: %2$s";

    private final Index targetIndex;
    private final String lessonCode;

    /**
     * Creates an EnrollCommand for a Student with a given index to a specified {@code Lesson}.
     */
    public EnrollCommand(Index targetIndex, String lessonCode) {
        requireNonNull(targetIndex, lessonCode);

        this.targetIndex = targetIndex;
        this.lessonCode = lessonCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!isValidLessonCode(lessonCode)) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_CODE);
        }
        LessonCode code = new LessonCode(lessonCode);
        Lesson lesson = model.searchLessons(code)
                .orElseThrow(() -> new CommandException(MESSAGE_LESSON_NOT_FOUND));

        List<Student> lastShownList = model.getFilteredStudentList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Student studentToEnroll = lastShownList.get(targetIndex.getZeroBased());

        if (lesson.containsStudent(studentToEnroll)) {
            throw new CommandException(String.format(MESSAGE_STUDENT_IN_LESSON, studentToEnroll.getName(), lesson));
        }
        if (!lesson.isAbleToEnroll(studentToEnroll)) {
            throw new CommandException(String.format(MESSAGE_UNABLE_TO_ENROLL, studentToEnroll.getName(), lesson));
        }

        Student newStudent = studentToEnroll.createClone();
        lesson.addStudent(newStudent);
        model.setStudent(studentToEnroll, newStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, newStudent.getName(), lesson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EnrollCommand
                && targetIndex.equals(((EnrollCommand) other).targetIndex)
                && lessonCode.equals(((EnrollCommand) other).lessonCode));
    }
}
