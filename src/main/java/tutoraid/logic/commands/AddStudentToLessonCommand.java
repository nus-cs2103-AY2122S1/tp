package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT;
import static tutoraid.ui.DetailLevel.HIGH;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;


/**
 * Adds a student to a lesson in TutorAid.
 */
public class AddStudentToLessonCommand extends AddCommand {

    public static final String COMMAND_FLAG = "-sl";

    public static final String MESSAGE_USAGE = COMMAND_FLAG + ": Adds a student to a lesson "
            + "by the student index number used in the last student listing "
            + "and the lesson index used in the last lesson listing.\n"
            + "Parameters: "
            + PREFIX_STUDENT + "STUDENT_INDEX (must be a positive integer) "
            + PREFIX_LESSON + "LESSON_INDEX (must be a positive integer) "
            + "Example: " + COMMAND_FLAG
            + " s/1"
            + " l/2";

    public static final String MESSAGE_SUCCESS = "Added student:\n%1$s\nTo this lesson:\n%2$s";

    private final Index studentIndex;
    private final Index lessonIndex;

    /**
     * Constructs an AddStudentToLessonCommand.
     *
     * @param studentIndex of the student in the filtered student list to add to the lesson
     * @param lessonIndex  of the lesson in the filtered lesson list to add to the student
     */
    public AddStudentToLessonCommand(Index studentIndex, Index lessonIndex) {
        requireNonNull(studentIndex);
        requireNonNull(lessonIndex);
        this.studentIndex = studentIndex;
        this.lessonIndex = lessonIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();

        if (studentIndex.getZeroBased() >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        if (lessonIndex.getZeroBased() >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Student studentToAddToLesson = lastShownStudentList.get(studentIndex.getZeroBased());
        Lesson lessonToAddToStudent = lastShownLessonList.get(lessonIndex.getZeroBased());

        if (studentToAddToLesson.hasLesson(lessonToAddToStudent)) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_ALREADY_ATTEND_LESSON);
        }

        if (lessonToAddToStudent.isFull()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_FULL);
        }

        lessonToAddToStudent.addStudent(studentToAddToLesson);
        studentToAddToLesson.addLesson(lessonToAddToStudent);

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
        model.viewList(HIGH);

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToAddToLesson, lessonToAddToStudent));
    }
}
