package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<TutorialClass> PREDICATE_SHOW_ALL_CLASSES = unused -> true;

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
     * Returns the user prefs' ClassMATE file path.
     */
    Path getClassmateFilePath();

    /**
     * Sets the user prefs' ClassMATE file path.
     */
    void setClassmateFilePath(Path classmateFilePath);

    /**
     * Replaces ClassMATE data with the data in {@code classmate}.
     */
    void setClassmate(ReadOnlyClassmate classmate);

    /** Returns the Classmate */
    ReadOnlyClassmate getClassmate();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the Classmate.
     */
    boolean hasStudent(Student student);

    /**
     * Returns true if a tutorial class with the same identity as {@code tutorialClass} exists in Classmate.
     */
    boolean hasTutorialClass(TutorialClass tutorialClass);

    /**
     * Returns true if a tutorial group with the same identity as {@code tutorialGroup} exists in Classmate.
     */
    boolean hasTutorialGroup(TutorialGroup tutorialGroup);

    /**
     * Deletes the given student.
     * The student must exist in Classmate.
     */
    void deleteStudent(Student target);

    /**
     * Deletes the given class.
     * The student must exist in Classmate.
     */
    void deleteTutorialClass(TutorialClass target);

    /**
     * Deletes the given group.
     * The TutorialGroup must exist in Classmate.
     */
    void deleteTutorialGroup(TutorialGroup target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the Classmate.
     */
    void addStudent(Student student);

    /**
     * Adds the given class.
     * {@code tutorialClass} must not already exist in Classmate.
     */
    void addTutorialClass(TutorialClass tutorialClass);

    /**
     * Adds the given group.
     * {@code tutorialGroup} must not already exist in Classmate.
     */
    void addTutorialGroup(TutorialGroup tutorialGroup);

    /**
     * Sorts the tutorial groups.
     */
    void sortTutorialGroups();

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the ClassMATE.
     * The student identity of {@code editedStudent} must not be the same as another existing student in ClassMATE.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the unfiltered student list */
    ObservableList<Student> getUnfilteredStudentList();

    /** Returns unmodifiable view of filtered Tutorial ClassList */
    ObservableList<TutorialClass> getFilteredTutorialClassList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    void updateUnfilteredStudentList(List<Student> students);

    void updateFilteredTutorialClassList(Predicate<TutorialClass> predicate);

}
