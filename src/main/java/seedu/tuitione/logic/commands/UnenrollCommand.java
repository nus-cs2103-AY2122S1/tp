package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.tuitione.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static seedu.tuitione.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;

/**
 * Unenrolls a student from a specified lesson.
 */
public class UnenrollCommand extends Command {

    public static final String COMMAND_WORD = "unenroll";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unenroll a specified student "
            + "from a given TuitiONE lesson\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + "l/LESSON_INDEX\n"
            + "Example: " + "unenroll 1 " + PREFIX_LESSON + "1";

    public static final String MESSAGE_UNENROLL_STUDENT_SUCCESS = "Unenrolled Student: %1$s from lesson: %2$s";
    public static final String MESSAGE_STUDENT_NOT_IN_LESSON = "%1$s is not currently enrolled in the lesson: %2$s";

    private final Index indexStudent;

    private final Index indexLesson;

    /**
     * Creates an UnenrollCommand for a Student with a given index and a specified {@code Lesson}.
     */
    public UnenrollCommand(Index indexStudent, Index indexLesson) {
        requireAllNonNull(indexStudent, indexLesson);

        this.indexStudent = indexStudent;
        this.indexLesson = indexLesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownStudentList = model.getFilteredStudentList();
        ObservableList<Lesson> lastShownLessonList = model.getFilteredLessonList();
        if (indexStudent.getZeroBased() >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Student studentToUnenroll = lastShownStudentList.get(indexStudent.getZeroBased());

        if (indexLesson.getZeroBased() >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
        Lesson lesson = lastShownLessonList.get(indexLesson.getZeroBased());
        Lesson newLesson = lesson.createClone();

        if (!newLesson.containsStudent(studentToUnenroll)) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_IN_LESSON,
                    studentToUnenroll.getName(),
                    newLesson));
        }

        Student newStudent = studentToUnenroll.createClone();

        newLesson.removeStudent(studentToUnenroll);
        model.setStudent(studentToUnenroll, newStudent);
        model.setLesson(lesson, newLesson);

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);

        return new CommandResult(String.format(MESSAGE_UNENROLL_STUDENT_SUCCESS, newStudent.getName(), newLesson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnenrollCommand // instanceof handles nulls
                && indexStudent.equals(((UnenrollCommand) other).indexStudent))
                && indexLesson.equals(((UnenrollCommand) other).indexLesson); // state check
    }

}
