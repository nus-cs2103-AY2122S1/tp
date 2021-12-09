package seedu.times.model;

import static java.util.Objects.requireNonNull;
import static seedu.times.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.times.commons.core.GuiSettings;
import seedu.times.commons.core.LogsCenter;
import seedu.times.model.person.Name;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;

/**
 * Represents the in-memory model of the timestable data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TimesTable timesTable;
    private final UserPrefs userPrefs;
    private FilteredList<Student> filteredStudents;
    private FilteredList<TuitionClass> filteredTuitionClass;


    /**
     * Initializes a ModelManager with the given timestable and userPrefs.
     */
    public ModelManager(ReadOnlyTimesTable timestable, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(timestable, userPrefs);

        logger.fine("Initializing with timestable: " + timestable + " and user prefs " + userPrefs);

        this.timesTable = new TimesTable(timestable);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.timesTable.getStudentList());
        filteredTuitionClass =
                new FilteredList<>(this.timesTable.getTuitionClassList());
    }

    public ModelManager() {
        this(new TimesTable(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTimesTableFilePath() {
        return userPrefs.getTimesTableFilePath();
    }

    @Override
    public void setTimesTableFilePath(Path timesTableFilePath) {
        requireNonNull(timesTableFilePath);
        userPrefs.setTimesTableFilePath(timesTableFilePath);
    }

    //=========== TimesTable ================================================================================

    @Override
    public ReadOnlyTimesTable getTimesTable() {
        return timesTable;
    }

    @Override
    public void setTimesTable(ReadOnlyTimesTable timestable) {
        this.timesTable.resetData(timestable);
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return timesTable.hasPerson(student);
    }

    @Override
    public boolean hasTuitionClass(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        return timesTable.hasTuitionClass(tuitionClass);
    }

    @Override
    public void deleteStudent(Student target) {
        timesTable.removePerson(target);
    }

    @Override
    public void addStudent(Student student) {
        timesTable.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addTuitionClass(TuitionClass tuitionClass) {
        timesTable.addTuitionClass(tuitionClass);
        updateFilteredClassList(PREDICATE_SHOW_ALL_CLASS);
    }

    @Override
    public void deleteTuitionClass(TuitionClass tuitionClass) {
        timesTable.deleteTuitionClass(tuitionClass);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        timesTable.setPerson(target, editedStudent);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedTimesTable}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredClassList(Predicate<TuitionClass> predicate) {
        requireNonNull(predicate);
        filteredTuitionClass.setPredicate(predicate);
    }

    @Override
    public void updateClassStudentLists(Name newName, Name oldName) {
        requireAllNonNull(newName, oldName);
        if (newName.equals(oldName)) {
            return;
        }
        timesTable.updateClassStudentLists(newName, oldName);
    }

    @Override
    public ObservableList<TuitionClass> getFilteredTuitionClassList() {
        return filteredTuitionClass;
    }

    @Override
    public void setClass(TuitionClass target, TuitionClass editedClass) {
        requireAllNonNull(target, editedClass);
        timesTable.setClass(target, editedClass);
        updateFilteredClassList(PREDICATE_SHOW_ALL_CLASS);
    }

    @Override
    public void setClasses(List<TuitionClass> classes) {
        timesTable.setClasses(classes);
    }

    @Override
    public void setStudents(List<Student> studentsList) {
        timesTable.setStudents(studentsList);
    }

    @Override
    public void sortStudents(Comparator<? super Student> c) {
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        ArrayList<Student> studentsToSort = new ArrayList<>(getFilteredStudentList());
        studentsToSort.sort(c);
        setStudents(studentsToSort);
    }

    @Override
    public void sortClasses(Comparator<? super TuitionClass> c) {
        updateFilteredClassList(PREDICATE_SHOW_ALL_CLASS);
        ArrayList<TuitionClass> classesToSort = new ArrayList<>(getFilteredTuitionClassList());
        classesToSort.sort(c);
        setClasses(classesToSort);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return timesTable.equals(other.timesTable)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents)
                && filteredTuitionClass.equals(other.filteredTuitionClass);
    }

}
