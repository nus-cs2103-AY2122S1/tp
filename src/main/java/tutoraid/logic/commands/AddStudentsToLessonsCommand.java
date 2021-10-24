package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT;

import java.util.ArrayList;
import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;


/**
 * Adds progress string to an exiting student in TutorAid. Updates the progress if one already exists.
 */
public class AddStudentsToLessonsCommand extends AddCommand {

    public static final String COMMAND_FLAG = "-sl";

    public static final String MESSAGE_USAGE = COMMAND_FLAG + ": Adds students to lessons "
            + "by the index numbers used in the last student and lesson listing.\n"
            + "Parameters: "
            + PREFIX_STUDENT + "STUDENT_INDEX(s) (must be a positive integer) "
            + PREFIX_LESSON + "LESSON_INDEX(s) (must be a positive integer) "
            + "Example: " + COMMAND_FLAG
            + "s/ 1 9 11 "
            + "l/ 2 5 4 ";

    public static final String MESSAGE_SUCCESS = "Added students: %1$s\nTo these lessons: %2$s";

    private final ArrayList<Index> targetIndexesForStudents;
    private final ArrayList<Index> targetIndexesForLessons;

    /**
     * Constructs an AddStudentsToLessonsCommand.
     *
     * @param studentIndexes of the students in the filtered student list to add to lessons
     * @param lessonIndexes of the lessons in the filtered lesson list to add to students
     */
    public AddStudentsToLessonsCommand(ArrayList<Index> studentIndexes, ArrayList<Index> lessonIndexes) {
        requireNonNull(studentIndexes);
        requireNonNull(lessonIndexes);
        targetIndexesForStudents = studentIndexes;
        targetIndexesForLessons = lessonIndexes;
    }

    /**
     * Checks if any of the students attends a specified lesson.
     *
     * @param students the students to be checked
     * @param lesson the lesson to be checked
     * @return true if at least one of the students attends this lesson
     */
    public boolean isAnyOfTheseStudentsAttendingThisLesson(ArrayList<Student> students, Lesson lesson) {
        requireNonNull(students);
        requireNonNull(lesson);
        for (Student student : students) {
            requireNonNull(student);
            if (lesson.containsStudent(student)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if any of the students attends any of the specified lessons.
     *
     * @param students the students to be checked
     * @param lessons the lessons to be checked
     * @return true if at least one of the students attends one of the lessons
     */
    public boolean isAnyOfTheseStudentsAttendingAnyOfTheseLessons(
            ArrayList<Student> students, ArrayList<Lesson> lessons) {

        requireNonNull(students);
        requireNonNull(lessons);
        for (Lesson lesson : lessons) {
            requireNonNull(lesson);
            if (isAnyOfTheseStudentsAttendingThisLesson(students, lesson)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds all specified students to a lesson
     *
     * @param students the students to be added
     * @param lesson the lesson to have these students attend
     */
    public void addStudentsToALesson(ArrayList<Student> students, Lesson lesson) {
        requireNonNull(students);
        requireNonNull(lesson);
        for (Student student : students) {
            requireNonNull(student);
            lesson.addStudent(student);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();

        ArrayList<Student> studentsToEdit = new ArrayList<>();
        ArrayList<Lesson> lessonsToEdit = new ArrayList<>();

        for (Index index : targetIndexesForStudents) {
            requireNonNull(index);
            if (index.getZeroBased() >= lastShownStudentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_MULTIPLE_INDEXES);
            }
            Student studentToEdit = lastShownStudentList.get(index.getZeroBased());
            studentsToEdit.add(studentToEdit);
        }

        for (Index index : targetIndexesForLessons) {
            requireNonNull(index);
            if (index.getZeroBased() >= lastShownLessonList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_MULTIPLE_INDEXES);
            }
            Lesson lessonToEdit = lastShownLessonList.get(index.getZeroBased());
            lessonsToEdit.add(lessonToEdit);
        }

        if (isAnyOfTheseStudentsAttendingAnyOfTheseLessons(studentsToEdit, lessonsToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_ALREADY_ATTEND_LESSON);
        }

        for (Lesson lesson : lessonsToEdit) {
            requireNonNull(lesson);
            addStudentsToALesson(studentsToEdit, lesson);
        }

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentsToEdit, lessonsToEdit));
    }
}
