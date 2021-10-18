package seedu.tuitione.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.tuitione.commons.core.GuiSettings;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
    Predicate<Lesson> PREDICATE_SHOW_ALL_LESSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' tuitione book file path.
     */
    Path getTuitioneFilePath();

    /**
     * Sets the user prefs' tuitione book file path.
     */
    void setTuitioneFilePath(Path tuitioneFilePath);

    /**
     * Replaces tuitione book data with the data in {@code tuitione}.
     */
    void setTuitione(ReadOnlyTuitione tuitione);

    /** Returns the Tuitione */
    ReadOnlyTuitione getTuitione();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the tuitione book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the tuitione book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the tuitione book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the tuitione book.
     * The student identity of {@code editedStudent}
     * must not be the same as another existing student in the tuitione book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered lesson list */
    ObservableList<Lesson> getFilteredLessonList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLessonList(Predicate<Lesson> predicate);

    /**
     * Returns true if a lesson with the same values as {@code lesson} exists in TuitiONE.
     */
    boolean hasLesson(Lesson lesson);

    /**
     * Deletes the given lesson.
     * The lesson must exist in TuitiONE.
     */
    void deleteLesson(Lesson lesson);

    /**
     * Adds the given lesson.
     * {@code lesson} must not already exist in TuitiONE.
     */
    void addLesson(Lesson lesson);

    /**
     * Replaces the given Lesson {@code target} with {@code editedLesson}.
     * {@code target} must exist in the tuitione book.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in the tuitione book.
     */
    void setLesson(Lesson target, Lesson editedLesson);

    /**
     * Returns lesson corresponding to String lessonCode, wrapped in an optional.
     */
    Optional<Lesson> searchLessons(LessonCode lessonCode);

    /**
     * Returns Student corresponding to String lessonCode, wrapped in an optional.
     */
    Optional<Student> searchStudents(Name lessonCode);
}
