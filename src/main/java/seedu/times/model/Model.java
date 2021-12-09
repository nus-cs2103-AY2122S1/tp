package seedu.times.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.times.commons.core.GuiSettings;
import seedu.times.model.person.Name;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<TuitionClass> PREDICATE_SHOW_ALL_CLASS = unused -> true;



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
     * Returns the user prefs' address book file path.
     */
    Path getTimesTableFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setTimesTableFilePath(Path timesTableFilePath);

    /**
     * Replaces address book data with the data in {@code timestable}.
     */
    void setTimesTable(ReadOnlyTimesTable timesTable);

    /** Returns the TimesTable */
    ReadOnlyTimesTable getTimesTable();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the TimesTable.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The person must exist in the TimesTable.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the TimesTable.
     */
    void addStudent(Student student);

    /**
     * Returns true if a class with the same identity as {@code TuitionClass} exists in the TimesTable.
     */
    boolean hasTuitionClass(TuitionClass tuitionClass);

    /**
     * Adds the given tuition class.
     * {@code TuitionClass} must not already exist in the address book.
     */
    void addTuitionClass(TuitionClass tuitionClass);

    /**
     * Deletes the given tuition class.
     * The class must exist in the TimesTable.
     */
    void deleteTuitionClass(TuitionClass target);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the TimesTable.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the TimesTable.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    void updateFilteredClassList(Predicate<TuitionClass> predicate);

    /** Returns an unmodifiable view of the filtered tuition class list */
    ObservableList<TuitionClass> getFilteredTuitionClassList();

    /**
     * Replaces the given class {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The Class identity of {@code editedClass} must not be the same as another existing class in the address book.
     */
    void setClass(TuitionClass target, TuitionClass editedClass);

    /**
     * Replaces the filteredTuitionClass with the classes.
     */
    void setClasses(List<TuitionClass> classes);

    /**
     * Replaces the filtered student list with the classes.
     */
    void setStudents(List<Student> students);

    /** Executes update cascade after change of student name for {@code StudentNameList}. */
    void updateClassStudentLists(Name newName, Name oldName);

    /**
     * Sorts the {@code Student} according to comparator c.
     */
    void sortStudents(Comparator<? super Student> c);

    /**
     * Sorts the {@code TuitionClass} according to comparator c.
     */
    void sortClasses(Comparator<? super TuitionClass> c);
}
