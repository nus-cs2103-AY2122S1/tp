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
 * Adds students to lessons in TutorAid.
 */
public class AddStudentToLessonCommand extends AddCommand {

    public static final String COMMAND_FLAG = "-sl";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Adds student(s) to lesson(s). "
                    + "\nParameters:"
                    + "\n%3$sSTUDENT INDEX(ES)... (must all be positive integers)"
                    + "  %4$sLESSON INDEX(ES)... (must all be positive integers)"
                    + "\nExample:"
                    + "\n%1$s %2$s %3$s1 2 3 4 %4$s2",
            COMMAND_WORD, COMMAND_FLAG, PREFIX_STUDENT, PREFIX_LESSON);

    public static final String MESSAGE_SUCCESS = "Successfully added %s to %s.\n";
    public static final String MESSAGE_FAILURE_FULL = "%s does not have enough capacity.";
    public static final String WARNING_STUDENT_ALREADY_ATTENDS_LESSON = "Warning: %s already attends %s.";


    private final ArrayList<Index> studentIndexes;
    private final ArrayList<Index> lessonIndexes;

    /**
     * Constructs an AddStudentToLessonCommand.
     *
     * @param studentIndexes of the students in the filtered student list to add to the lessons
     * @param lessonIndexes  of the lessons in the filtered lesson list to add to the students
     */
    public AddStudentToLessonCommand(ArrayList<Index> studentIndexes, ArrayList<Index> lessonIndexes) {
        // Indexes are guaranteed to be distinct from ParserUtil
        requireNonNull(studentIndexes);
        requireNonNull(lessonIndexes);
        this.studentIndexes = studentIndexes;
        this.lessonIndexes = lessonIndexes;
    }

    /**
     * Adds the link between a single Student and Lesson.
     *
     * @param model The Model object containing all the data
     * @param studentIndex The index of the Student to be linked to the Lesson
     * @param lessonIndex The index of the Lesson to be linked to the Student
     * @return A string containing feedback or warnings
     */
    public String executeSingle(Model model, Index studentIndex, Index lessonIndex) {
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();
        Student student = lastShownStudentList.get(studentIndex.getZeroBased());
        Lesson lesson = lastShownLessonList.get(lessonIndex.getZeroBased());
        if (student.hasLesson(lesson)) {
            return String.format(WARNING_STUDENT_ALREADY_ATTENDS_LESSON,
                    student.toNameString(), lesson.toNameString()) + "\n";
        }
        lesson.addStudent(student);
        student.addLesson(lesson);
        return String.format(MESSAGE_SUCCESS, student.toNameString(), lesson.toNameString());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder result = new StringBuilder();
        checkIndexesAreValid(model, lessonIndexes, studentIndexes);
        checkLessonsHaveCapacity(model, lessonIndexes, studentIndexes);
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
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENTS_DISPLAYED_INDEX);
        }
        if (maxLessonIndex >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSONS_DISPLAYED_INDEX);
        }
    }

    private void checkLessonsHaveCapacity(Model model, ArrayList<Index> lessonIndexes, ArrayList<Index> studentIndexes)
            throws CommandException {
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        for (Index lessonIndex : lessonIndexes) {
            Lesson lesson = lastShownLessonList.get(lessonIndex.getZeroBased());
            long requiredCapacity = studentIndexes.stream()
                    .map(index -> lastShownStudentList.get(index.getZeroBased()))
                    .filter(student -> !student.hasLesson(lesson))
                    .count();
            if (lesson.getStudents().numberOfStudents() + requiredCapacity > lesson.getCapacityValue()) {
                throw new CommandException(String.format(MESSAGE_FAILURE_FULL, lesson.toNameString()));
            }
        }
    }
}
