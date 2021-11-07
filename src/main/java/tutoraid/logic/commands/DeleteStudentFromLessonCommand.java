package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT;
import static tutoraid.ui.DetailLevel.MED;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;


/**
 * Deletes students from lessons in TutorAid.
 */
public class DeleteStudentFromLessonCommand extends DeleteCommand {

    public static final String COMMAND_FLAG = "-sl";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Removes student(s) from lesson(s)."
                    + "\nParameters: "
                    + "\n%3$sSTUDENT INDEX [MORE INDEXES...] (must all be positive integers)"
                    + "  %4$sLESSON INDEX [MORE INDEXES...] (must all be positive integers)"
                    + "\nExample:"
                    + "\n%1$s %2$s %3$s1 2 3 4 %4$s2",
            COMMAND_WORD, COMMAND_FLAG, PREFIX_STUDENT, PREFIX_LESSON);

    public static final String MESSAGE_SUCCESS = "Successfully removed %s from %s.\n";
    public static final String WARNING_STUDENT_DOES_NOT_ATTEND_LESSON = "Warning: %s does not attend %s.";


    private final ArrayList<Index> studentIndexes;
    private final ArrayList<Index> lessonIndexes;

    /**
     * Constructs a DeleteStudentFromLessonCommand.
     *
     * @param studentIndexes of the student in the filtered student list to add to the lesson
     * @param lessonIndexes  of the lesson in the filtered lesson list to add to the student
     */
    public DeleteStudentFromLessonCommand(ArrayList<Index> studentIndexes, ArrayList<Index> lessonIndexes) {
        // Indexes are guaranteed to be distinct from ParserUtil
        requireNonNull(studentIndexes);
        requireNonNull(lessonIndexes);
        this.studentIndexes = studentIndexes;
        this.lessonIndexes = lessonIndexes;
    }

    /**
     * Removes the link between a single Student and Lesson.
     *
     * @param model The Model object containing all the data
     * @param studentIndex The index of the Student to be unlinked from the Lesson
     * @param lessonIndex The index of the Lesson to be unlinked from the Student
     * @return A string containing feedback or warnings
     */
    public String executeSingle(Model model, Index studentIndex, Index lessonIndex) throws CommandException {
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();
        Student student = lastShownStudentList.get(studentIndex.getZeroBased());
        Lesson lesson = lastShownLessonList.get(lessonIndex.getZeroBased());
        if (!student.hasLesson(lesson)) {
            return String.format(WARNING_STUDENT_DOES_NOT_ATTEND_LESSON,
                    student.toNameString(), lesson.toNameString()) + "\n";
        }
        lesson.removeStudent(student);
        student.removeLesson(lesson);
        return String.format(MESSAGE_SUCCESS, student.toNameString(), lesson.toNameString());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder result = new StringBuilder();
        checkIndexesAreValid(model, lessonIndexes, studentIndexes);
        for (Index lessonIndex : lessonIndexes) {
            for (Index studentIndex : studentIndexes) {
                result.append(executeSingle(model, studentIndex, lessonIndex));
            }
        }
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
        model.viewList(MED);

        return new CommandResult(result.toString());
    }

    private void checkIndexesAreValid(Model model, ArrayList<Index> lessonIndexes, ArrayList<Index> studentIndexes)
            throws CommandException {
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();
        int maxStudentIndex = studentIndexes.stream().max(
                Comparator.comparingInt(Index::getZeroBased)).get().getZeroBased();
        int maxLessonIndex = lessonIndexes.stream().max(
                Comparator.comparingInt(Index::getZeroBased)).get().getZeroBased();
        if (maxStudentIndex >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        if (maxLessonIndex >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
    }
}
