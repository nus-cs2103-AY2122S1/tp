package seedu.teachbook.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.teachbook.commons.core.GuiSettings;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.classobject.exceptions.NoClassWithNameException;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.gradeobject.GradingSystem;
import seedu.teachbook.model.student.Student;

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
     * Returns the user prefs' TeachBook file path.
     */
    Path getTeachBookFilePath();

    /**
     * Sets the user prefs' TeachBook file path.
     */
    void setTeachBookFilePath(Path teachBookFilePath);

    /**
     * Replaces TeachBook data with the data in {@code teachBook}.
     */
    void setTeachBook(ReadOnlyTeachBook teachBook);

    /**
     * Returns the TeachBook.
     */
    ReadOnlyTeachBook getTeachBook();

    /**
     * Returns the index of the currently selected class on UI.
     */
    GeneralIndex getCurrentlySelectedClassIndex();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the TeachBook.
     */
    boolean hasStudent(Student student);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the TeachBook.
     */
    boolean hasClass(Class aClass);

    /**
     * Deletes the given class. Returns true if there is a need to update the student list panel.
     * The class must exist in the TeachBook.
     */
    boolean deleteClass(Class target, GeneralIndex targetIndex);

    /**
     * Adds the given class.
     * {@code aClass} must not already exist in the TeachBook.
     */
    void addClass(Class aClass);

    /**
     * Deletes the given student.
     * The student must exist in the TeachBook.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the TeachBook.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedPerson}.
     * {@code target} must exist in the TeachBook.
     * The student identity of {@code editedPerson} must not be the same as another
     * existing student in the TeachBook.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Sets a class of a {@code student}.
     *
     * @param student that you want to set class.
     */
    void setClassForStudent(Student student);

    /**
     * Returns an unmodifiable view of the filtered student list.
     */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns an unmodifiable view of the unique class list.
     */
    ObservableList<Class> getUniqueClassList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Returns the index of the class.
     *
     * @param className the name of the class you want to know the index of.
     * @return the index of the class.
     * @throws NoClassWithNameException
     */
    GeneralIndex getIndexOfClass(ClassNameDescriptor className) throws NoClassWithNameException;

    /**
     * Returns the currently set grading system.
     *
     * @return the currently set grading system.
     */
    GradingSystem getGradingSystem();

    /**
     * Sets the grading system for TeachBook.
     *
     * @param gradingSystem the grading system to be set.
     */
    void setGradingSystem(GradingSystem gradingSystem);

    /**
     * Checks if there is already an existing grading system.
     *
     * @return true if there is an existing grading system.
     */
    boolean hasExistingGradingSystem();

    /**
     * Checks if the grade is valid.
     *
     * @param grade the grade to be checked if valid.
     * @return true if the {@code grade} is valid.
     */
    boolean isValidGrade(Grade grade);

    /**
     * Updates the currently selected class to the class at {@code newClassIndex}.
     *
     * @param newClassIndex the index of the class that to be selected.
     */
    void updateCurrentlySelectedClass(GeneralIndex newClassIndex);

    /**
     * Reorders the students.
     *
     * @param comparator that is used to compare the students to reorder.
     */
    void reorderStudents(Comparator<? super Student> comparator);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoTeachBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoTeachBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitTeachBook();

    /**
     * Resets the existing grading system.
     */
    void resetGradingSystem();

    /**
     * Checks if TeachBook is empty.
     *
     * @return true if TeachBook is empty.
     */
    boolean isTeachBookEmpty();

    /**
     * Checks if TeachBook is currently listing all students.
     *
     * @return true if TeachBook is currently listing all students.
     */
    boolean isListAll();

    /**
     * Sets the currently class to a new name in {@code newClassName}
     *
     * @param newClassName the new class name to be set.
     */
    void setClassName(ClassName newClassName);
}
