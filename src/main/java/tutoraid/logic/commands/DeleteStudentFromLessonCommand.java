package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT;
import static tutoraid.ui.DetailLevel.MED;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;


/**
 * Deletes a student from a lesson in TutorAid.
 */
public class DeleteStudentFromLessonCommand extends DeleteCommand {

    public static final String COMMAND_FLAG = "-sl";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Removes a student from a lesson."
                    + "\nParameters: "
                    + "\n%3$sSTUDENT INDEX (must be a positive integer)"
                    + "  %4$sLESSON INDEX (must be a positive integer)"
                    + "\nExample:"
                    + "\n%1$s %2$s %3$s1 %4$s2",
            COMMAND_WORD, COMMAND_FLAG, PREFIX_STUDENT, PREFIX_LESSON);

    public static final String MESSAGE_SUCCESS = "Successfully removed %s from %s.";

    private final Index studentIndex;
    private final Index lessonIndex;

    /**
     * Constructs a DeleteStudentFromLessonCommand.
     *
     * @param studentIndex of the student in the filtered student list to add to the lesson
     * @param lessonIndex  of the lesson in the filtered lesson list to add to the student
     */
    public DeleteStudentFromLessonCommand(Index studentIndex, Index lessonIndex) {
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

        Student studentToDeleteFromLesson = lastShownStudentList.get(studentIndex.getZeroBased());
        Lesson lessonToDeleteFromStudent = lastShownLessonList.get(lessonIndex.getZeroBased());

        if (!studentToDeleteFromLesson.hasLesson(lessonToDeleteFromStudent)) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_NOT_IN_LESSON);
        }

        lessonToDeleteFromStudent.removeStudent(studentToDeleteFromLesson);
        studentToDeleteFromLesson.removeLesson(lessonToDeleteFromStudent);

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
        model.viewList(MED);

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                studentToDeleteFromLesson.toNameString(),
                lessonToDeleteFromStudent.toNameString()));
    }
}
