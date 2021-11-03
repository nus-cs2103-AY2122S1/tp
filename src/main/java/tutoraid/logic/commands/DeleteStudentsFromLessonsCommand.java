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
 * Deletes students from lessons in TutorAid.
 */
public class DeleteStudentsFromLessonsCommand extends DeleteCommand {

    public static final String COMMAND_FLAG = "-sl";

    public static final String MESSAGE_USAGE = COMMAND_FLAG + ": Deletes students from lessons "
            + "by the index numbers used in the last student and lesson listing.\n"
            + "Parameters: "
            + PREFIX_STUDENT + "STUDENT_INDEX(s) (must be a positive integer) "
            + PREFIX_LESSON + "LESSON_INDEX(s) (must be a positive integer) "
            + "Example: " + COMMAND_FLAG
            + " s/ 1 9 11"
            + " l/ 2 5 4";

    public static final String MESSAGE_SUCCESS = "Removed students:\n%1$s\nFrom these lessons:\n%2$s";

    private final ArrayList<Index> targetIndexesForStudents;
    private final ArrayList<Index> targetIndexesForLessons;

    /**
     * Constructs an DeleteStudentsFromLessonsCommand.
     *
     * @param studentIndexes of the students in the filtered student list to add to lessons
     * @param lessonIndexes  of the lessons in the filtered lesson list to add to students
     */
    public DeleteStudentsFromLessonsCommand(ArrayList<Index> studentIndexes, ArrayList<Index> lessonIndexes) {
        requireNonNull(studentIndexes);
        requireNonNull(lessonIndexes);
        targetIndexesForStudents = studentIndexes;
        targetIndexesForLessons = lessonIndexes;
    }

    /**
     * Checks if any of the students does not attend a specified lesson.
     *
     * @param students the students to be checked
     * @param lesson   the lesson to be checked
     * @return true if at least one student does not attend this lesson
     */
    public boolean isAnyOfTheseStudentsNotAttendingThisLesson(ArrayList<Student> students, Lesson lesson) {
        requireNonNull(students);
        requireNonNull(lesson);
        for (Student student : students) {
            requireNonNull(student);
            if (!lesson.hasStudent(student)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if any of the students does not attend any of the specified lessons.
     *
     * @param students the students to be checked
     * @param lessons  the lessons to be checked
     * @return true if at least one of the students does not attend one of the lessons
     */
    public boolean isAnyOfTheseStudentsNotAttendingAnyOfTheseLessons(
            ArrayList<Student> students, ArrayList<Lesson> lessons) {

        requireNonNull(students);
        requireNonNull(lessons);
        for (Lesson lesson : lessons) {
            requireNonNull(lesson);
            if (isAnyOfTheseStudentsNotAttendingThisLesson(students, lesson)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all specified students from a lesson
     *
     * @param students the students to be removed
     * @param lesson   the lesson to have these students removed
     */
    public void removeStudentsFromALesson(ArrayList<Student> students, Lesson lesson) {
        requireNonNull(students);
        requireNonNull(lesson);
        for (Student student : students) {
            requireNonNull(student);
            lesson.removeStudent(student);
            student.removeLesson(lesson);
        }
    }

    /**
     * Returns an arraylist of students to be added to lessons.
     *
     * @param lastShownStudentList     the last list that was shown to the user
     * @param targetIndexesForStudents indexes of students to be added
     */
    public ArrayList<Student> getStudentsToEdit(
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
     * Returns an arraylist of lessons to have students added.
     *
     * @param lastShownLessonList     the last list that was shown to the user
     * @param targetIndexesForLessons indexes of lessons to have students added
     */
    public ArrayList<Lesson> getLessonsToEdit(
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

        ArrayList<Student> studentsToEdit = getStudentsToEdit(lastShownStudentList, targetIndexesForStudents);
        ArrayList<Lesson> lessonsToEdit = getLessonsToEdit(lastShownLessonList, targetIndexesForLessons);


        if (isAnyOfTheseStudentsNotAttendingAnyOfTheseLessons(studentsToEdit, lessonsToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_NOT_IN_LESSON);
        }

        for (Lesson lesson : lessonsToEdit) {
            requireNonNull(lesson);
            removeStudentsFromALesson(studentsToEdit, lesson);
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
