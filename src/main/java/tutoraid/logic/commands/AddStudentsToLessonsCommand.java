package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT;
import static tutoraid.ui.DetailLevel.HIGH;

import java.util.ArrayList;
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
public class AddStudentsToLessonsCommand extends AddCommand {

    public static final String COMMAND_FLAG = "-sl";

    public static final String MESSAGE_USAGE = COMMAND_FLAG + ": Adds students to lessons "
            + "by the index numbers used in the last student and lesson listing.\n"
            + "Parameters: "
            + PREFIX_STUDENT + "STUDENT_INDEX(s) (must be a positive integer) "
            + PREFIX_LESSON + "LESSON_INDEX(s) (must be a positive integer) "
            + "Example: " + COMMAND_FLAG
            + " s/ 1 9 11 "
            + "l/ 2 5 4 ";

    public static final String MESSAGE_SUCCESS = "Added students:\n%1$s\nTo these lessons:\n%2$s";

    private final ArrayList<Index> targetIndexesForStudents;
    private final ArrayList<Index> targetIndexesForLessons;

    /**
     * Constructs an AddStudentsToLessonsCommand.
     *
     * @param studentIndexes of the students in the filtered student list to add to lessons
     * @param lessonIndexes  of the lessons in the filtered lesson list to add to students
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
     * @param lesson   the lesson to be checked
     * @return true if at least one of the students attends this lesson
     */
    private boolean isAnyOfTheseStudentsAttendingThisLesson(ArrayList<Student> students, Lesson lesson) {
        requireNonNull(students);
        requireNonNull(lesson);
        for (Student student : students) {
            requireNonNull(student);
            if (lesson.hasStudent(student)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if any of the students attends any of the specified lessons.
     *
     * @param students the students to be checked
     * @param lessons  the lessons to be checked
     * @return true if at least one of the students attends one of the lessons
     */
    private boolean isAnyOfTheseStudentsAttendingAnyOfTheseLessons(
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
     * Checks if the lesson can accept an extra number of students without exceeding capacity.
     *
     * @param lesson        the lesson to be checked
     * @param numOfStudents the number of students to be added
     */
    private boolean canAcceptMoreStudents(Lesson lesson, int numOfStudents) {
        requireNonNull(lesson);
        requireNonNull(numOfStudents);
        return !lesson.exceedsCapacity(numOfStudents);
    }

    private boolean canAllLessonsAcceptMoreStudents(ArrayList<Lesson> lessons, ArrayList<Student> students) {
        requireNonNull(students);
        requireNonNull(lessons);
        int numOfStudents = students.size();
        for (Lesson lesson : lessons) {
            requireNonNull(lesson);
            if (!canAcceptMoreStudents(lesson, numOfStudents)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all specified students to a lesson
     *
     * @param students the students to be added
     * @param lesson   the lesson to have these students attend
     */
    private void addStudentsToALesson(ArrayList<Student> students, Lesson lesson) {
        requireNonNull(students);
        requireNonNull(lesson);
        for (Student student : students) {
            requireNonNull(student);
            lesson.addStudent(student);
            student.addLesson(lesson);
        }
    }

    /**
     * Returns an arraylist of students to be added to lessons.
     *
     * @param lastShownStudentList     the last list that was shown to the user
     * @param targetIndexesForStudents indexes of students to be added
     */
    public ArrayList<Student> getStudentsToAdd(
            List<Student> lastShownStudentList, ArrayList<Index> targetIndexesForStudents) throws CommandException {
        ArrayList<Student> studentsToEdit = new ArrayList<>();
        for (Index index : targetIndexesForStudents) {
            requireNonNull(index);
            if (index.getZeroBased() >= lastShownStudentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_MULTIPLE_INDEXES);
            }
            Student studentToEdit = lastShownStudentList.get(index.getZeroBased());
            studentsToEdit.add(studentToEdit);
        }

        return studentsToEdit;
    }

    /**
     * Returns an arraylist of lessons to be added to students
     *
     * @param lastShownLessonList     the last list that was shown to the user
     * @param targetIndexesForLessons indexes of lessons to have students added
     */
    public ArrayList<Lesson> getLessonsToAdd(
            List<Lesson> lastShownLessonList, ArrayList<Index> targetIndexesForLessons) throws CommandException {

        ArrayList<Lesson> lessonsToEdit = new ArrayList<>();
        for (Index index : targetIndexesForLessons) {
            requireNonNull(index);
            if (index.getZeroBased() >= lastShownLessonList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_MULTIPLE_INDEXES);
            }
            Lesson lessonToEdit = lastShownLessonList.get(index.getZeroBased());
            lessonsToEdit.add(lessonToEdit);
        }

        return lessonsToEdit;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();

        ArrayList<Student> studentsToEdit = getStudentsToAdd(lastShownStudentList, targetIndexesForStudents);
        ArrayList<Lesson> lessonsToEdit = getLessonsToAdd(lastShownLessonList, targetIndexesForLessons);

        if (isAnyOfTheseStudentsAttendingAnyOfTheseLessons(studentsToEdit, lessonsToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_ALREADY_ATTEND_LESSON);
        }

        if (!canAllLessonsAcceptMoreStudents(lessonsToEdit, studentsToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_FULL);
        }

        for (Lesson lesson : lessonsToEdit) {
            requireNonNull(lesson);
            addStudentsToALesson(studentsToEdit, lesson);
        }

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
        model.viewList(HIGH);

        StringBuilder namesOfStudents = new StringBuilder();
        StringBuilder namesOfLessons = new StringBuilder();

        for (Student student : studentsToEdit) {
            namesOfStudents.append(student.toNameString()).append("\n");
        }

        for (Lesson lesson : lessonsToEdit) {
            namesOfLessons.append(lesson.nameAsString()).append("\n");
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, namesOfStudents, namesOfLessons));
    }
}
