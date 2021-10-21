package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final CsBook csBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Group> filteredGroups;

    /**
     * Initializes a ModelManager with the given csBook and userPrefs.
     */
    public ModelManager(ReadOnlyCsBook csBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(csBook, userPrefs);

        logger.fine("Initializing with address book: " + csBook + " and user prefs " + userPrefs);

        this.csBook = new CsBook(csBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.csBook.getStudentList());
        filteredGroups = new FilteredList<>(this.csBook.getGroupList());
    }

    public ModelManager() {
        this(new CsBook(), new UserPrefs());
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
    public Path getCsBookFilePath() {
        return userPrefs.getCsBookFilePath();
    }

    @Override
    public void setCsBookFilePath(Path csBookFilePath) {
        requireNonNull(csBookFilePath);
        userPrefs.setCsBookFilePath(csBookFilePath);
    }

    //=========== CsBook ================================================================================

    @Override
    public void setCsBook(ReadOnlyCsBook csBook) {
        this.csBook.resetData(csBook);
    }

    @Override
    public ReadOnlyCsBook getCsBook() {
        return csBook;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return csBook.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        // Retrieve existing group in model
        GroupName groupName = target.getGroupName();
        updateFilteredGroupList(new GroupContainsKeywordsPredicate(List.of(groupName.toString())));
        Group retrievedGroup = getFilteredGroupList().get(0);
        updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);

        // Remove reference to student from the group that the student belonged to
        retrievedGroup.removeStudent(target);

        csBook.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        // Retrieve existing group in model
        GroupName groupName = student.getGroupName();
        updateFilteredGroupList(new GroupContainsKeywordsPredicate(List.of(groupName.toString())));
        Group retrievedGroup = getFilteredGroupList().get(0);
        updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);

        // Add reference to student into the group
        retrievedGroup.addStudent(student);

        csBook.addStudent(student);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        csBook.setStudent(target, editedStudent);
    }

    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return csBook.hasGroup(group);
    }

    @Override
    public void deleteGroup(Group target) {
        Set<Student> studentsToDelete = target.getStudents();

        // Delete all students associated with the group
        for (Student student : studentsToDelete) {
            csBook.removeStudent(student);
        }

        csBook.removeGroup(target);
    }

    @Override
    public void addGroup(Group group) {
        csBook.addGroup(group);
        updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedCsBook}
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

    //=========== Filtered Group List Accessors =============================================================

    @Override
    public ObservableList<Group> getFilteredGroupList() {
        return filteredGroups;
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        requireNonNull(predicate);
        filteredGroups.setPredicate(predicate);
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
        return csBook.equals(other.csBook)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents)
                && filteredGroups.equals(other.filteredGroups);
    }

}
