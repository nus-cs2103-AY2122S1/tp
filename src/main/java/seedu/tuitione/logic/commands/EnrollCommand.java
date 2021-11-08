package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_ALERT;
import static seedu.tuitione.commons.core.Messages.HEADER_SUCCESS;
import static seedu.tuitione.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.tuitione.model.lesson.Lesson.CONFLICTING_TIMINGS_CONSTRAINT;
import static seedu.tuitione.model.lesson.Lesson.DIFFERENT_GRADE_CONSTRAINT;
import static seedu.tuitione.model.lesson.Lesson.LESSON_ENROLLMENT_MESSAGE_CONSTRAINT;
import static seedu.tuitione.model.lesson.Lesson.STUDENT_ALREADY_ENROLLED_CONSTRAINT;
import static seedu.tuitione.model.student.Student.STUDENT_ENROLLMENT_MESSAGE_CONSTRAINT;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;

public class EnrollCommand extends Command {

    public static final String COMMAND_WORD = "enroll";

    public static final String MESSAGE_USAGE = "Command: "
            + COMMAND_WORD + "\nEnrolls a specified student "
            + "from a given TuitiONE lesson\n\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + "l/LESSON_INDEX (must be a positive integer)\n"
            + "Example: " + "enroll 1 " + PREFIX_LESSON + "1";

    public static final String MESSAGE_SUCCESS = HEADER_SUCCESS + "%1$s enrolled into lesson:\n%2$s";

    private final Index indexStudent;
    private final Index indexLesson;

    /**
     * Creates an EnrollCommand for a Student with a given index to a specified {@code Lesson}.
     */
    public EnrollCommand(Index indexStudent, Index indexLesson) {
        requireAllNonNull(indexStudent, indexLesson);

        this.indexStudent = indexStudent;
        this.indexLesson = indexLesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();
        ObservableList<Lesson> lessonList = model.getFilteredLessonList();

        if (indexStudent.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Student studentToEnroll = lastShownList.get(indexStudent.getZeroBased());

        if (indexLesson.getZeroBased() >= lessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
        Lesson lesson = lessonList.get(indexLesson.getZeroBased());

        // run checks
        String alertMessageContainer = null;
        if (!studentToEnroll.isAbleToEnrollForMoreLessons()) {
            alertMessageContainer = String.format(STUDENT_ENROLLMENT_MESSAGE_CONSTRAINT, studentToEnroll.getName());

        } else if (lesson.containsStudent(studentToEnroll)) {
            alertMessageContainer = String.format(STUDENT_ALREADY_ENROLLED_CONSTRAINT,
                    studentToEnroll.getName(), lesson);

        } else if (!lesson.isStudentOfSameGrade(studentToEnroll)) {
            alertMessageContainer = String.format(DIFFERENT_GRADE_CONSTRAINT, studentToEnroll.getName(), lesson);

        } else if (lesson.doesStudentHaveConflictingTimings(studentToEnroll)) {
            alertMessageContainer = String.format(CONFLICTING_TIMINGS_CONSTRAINT, studentToEnroll.getName(), lesson);

        } else if (!lesson.isAbleToEnrollMoreStudents()) {
            alertMessageContainer = String.format(LESSON_ENROLLMENT_MESSAGE_CONSTRAINT, lesson);
        }
        if (alertMessageContainer != null) {
            throw new CommandException(HEADER_ALERT + alertMessageContainer);
        }

        lesson.enrollStudent(studentToEnroll);
        model.setLesson(lesson, lesson);
        model.setStudent(studentToEnroll, studentToEnroll);

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToEnroll.getName(), lesson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EnrollCommand
                && indexStudent.equals(((EnrollCommand) other).indexStudent)
                && indexLesson.equals(((EnrollCommand) other).indexLesson));
    }
}
