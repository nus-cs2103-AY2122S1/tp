package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Represents the in-memory model of the ClassMATE data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Classmate classmate;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<TutorialClass> filteredTutorialClasses;

    /**
     * Initializes a ModelManager with the given classmate and userPrefs.
     */
    public ModelManager(ReadOnlyClassmate classmate, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(classmate, userPrefs);

        logger.fine("Initializing with ClassMATE: " + classmate + " and user prefs " + userPrefs);

        this.classmate = new Classmate(classmate);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.classmate.getStudentList());
        filteredTutorialClasses = new FilteredList<>(this.classmate.getTutorialClassList());
    }

    public ModelManager() {
        this(new Classmate(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
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
    public Path getClassmateFilePath() {
        return userPrefs.getClassmateFilePath();
    }

    @Override
    public void setClassmateFilePath(Path classmateFilePath) {
        requireNonNull(classmateFilePath);
        userPrefs.setClassmateFilePath(classmateFilePath);
    }

    //=========== Classmate ================================================================================

    @Override
    public void setClassmate(ReadOnlyClassmate classmate) {
        this.classmate.resetData(classmate);
    }

    @Override
    public ReadOnlyClassmate getClassmate() {
        return classmate;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return classmate.hasStudent(student);
    }

    @Override
    public boolean hasTutorialClass(TutorialClass tutorialClass) {
        requireAllNonNull(tutorialClass);
        return classmate.hasTutorialClass(tutorialClass);
    }

    @Override
    public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
        requireAllNonNull(tutorialGroup);
        return classmate.hasTutorialGroup(tutorialGroup);
    }

    @Override
    public void deleteStudent(Student target) {
        classmate.removeStudent(target);
    }

    @Override
    public void deleteTutorialClass(TutorialClass target) {
        classmate.removeTutorialClass(target);
    }

    @Override
    public void deleteTutorialGroup(TutorialGroup target) {
        classmate.removeTutorialGroup(target);
    }

    @Override
    public void addStudent(Student student) {
        classmate.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void addTutorialClass(TutorialClass tutorialClass) {
        classmate.addTutorialClass(tutorialClass);
    }

    @Override
    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        classmate.addTutorialGroup(tutorialGroup);
    }

    @Override
    public void sortTutorialGroups() {
        classmate.sortTutorialGroups();
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        classmate.setStudent(target, editedStudent);
    }

    @Override
    public ObservableList<Student> getUnfilteredStudentList() {
        return classmate.getStudentList();
    }

    @Override
    public ObservableList<TutorialClass> getFilteredTutorialClassList() {
        return filteredTutorialClasses;
    }

    @Override
    public void updateUnfilteredStudentList(List<Student> students) {
        classmate.setStudents(students);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedClassmate}
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
    public void updateFilteredTutorialClassList(Predicate<TutorialClass> predicate) {
        requireNonNull(predicate);
        filteredTutorialClasses.setPredicate(predicate);
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
        return classmate.equals(other.classmate)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
