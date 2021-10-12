package tutoraid.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import tutoraid.commons.core.GuiSettings;
import tutoraid.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

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
     * Returns the user prefs' student book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Sets the user prefs' student book file path.
     */
    void setStudentBookFilePath(Path studentBookFilePath);

    /**
     * Replaces student book data with the data in {@code studentBook}.
     */
    void setStudentBook(ReadOnlyStudentBook studentBook);

    /** Returns the StudentBook */
    ReadOnlyStudentBook getStudentBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the student book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the student book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the student book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * student book.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Views the given student.
     * {@code student} must exist in the student book.
     */
    void viewStudent(Student student);

    /**
     * Views the list of people in the database.
     */
    void viewList();

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();


    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);


}
