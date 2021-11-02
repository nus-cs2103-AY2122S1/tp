package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_SUCCESS;
import static seedu.tuitione.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.tuitione.model.lesson.Lesson.STUDENT_NOT_ENROLLED_CONSTRAINT;

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

    public static final String MESSAGE_USAGE = "Command: "
            + COMMAND_WORD + "\nUnenroll a specified student "
            + "from a given TuitiONE lesson\n\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + "l/LESSON_INDEX (must be a positive integer)\n"
            + "Example: " + "unenroll 1 " + PREFIX_LESSON + "1";

    public static final String MESSAGE_UNENROLL_STUDENT_SUCCESS = HEADER_SUCCESS + "Unenrolled student "
            + "%1$s from lesson %2$s";

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

        if (!lesson.containsStudent(studentToUnenroll)) {
            throw new CommandException(String.format(STUDENT_NOT_ENROLLED_CONSTRAINT,
                    studentToUnenroll.getName(), lesson));
        }
        lesson.unenrollStudent(studentToUnenroll);

        // self update entities
        model.setStudent(studentToUnenroll, studentToUnenroll);
        model.setLesson(lesson, lesson);

        return new CommandResult(String.format(MESSAGE_UNENROLL_STUDENT_SUCCESS, studentToUnenroll.getName(), lesson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnenrollCommand // instanceof handles nulls
                && indexStudent.equals(((UnenrollCommand) other).indexStudent))
                && indexLesson.equals(((UnenrollCommand) other).indexLesson); // state check
    }

}
