package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.DisplayType.GROUPS;
import static seedu.address.model.Model.DisplayType.STUDENTS;
import static seedu.address.model.Model.DisplayType.TASKS;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.group.Group;
import seedu.address.model.student.Student;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Group> filteredGroups;
    private DisplayType displayType;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        displayType = STUDENTS;
        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
        filteredTasks = new FilteredList<>(this.addressBook.getTaskList());
        filteredGroups = new FilteredList<>(this.addressBook.getGroupList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    public DisplayType getDisplayType() {
        return displayType;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    public void clearTasks() {
        this.addressBook.clearAllTask();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return addressBook.hasStudent(student);
    }

    @Override
    public boolean hasAnotherStudent(Student student, Student toIgnore) {
        requireAllNonNull(student, toIgnore);
        return addressBook.hasAnotherStudent(student, toIgnore);
    }

    @Override
    public void deleteStudent(Student target) {
        requireNonNull(target);
        addressBook.deleteStudent(target);
        if (target.hasGroupName()) {
            List<Group> groupList = getAllGroupList();
            Group group = groupList.stream()
                                          .filter(g -> g.getName().equals(target.getGroupName()))
                                          .findAny()
                                          .orElse(null);
            addressBook.deleteStudentFromGroup(target, group);
        }
    }

    @Override
    public void addStudent(Student student) {
        addressBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    /**
     * Updates when a member of a group changes.
     *
     * @param target current Student to be updated
     * @param editedStudent updated Student object
     */
    private void updateGroup(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        if (target.hasGroupName()) {
            List<Group> groupList = getAllGroupList();
            Group updatedGroup = groupList.stream()
                    .filter(g -> g.getName().equals(target.getGroupName()))
                    .findAny()
                    .orElse(null);
            assert updatedGroup != null;
            updatedGroup.updateMember(target, editedStudent);
        }
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        updateGroup(target, editedStudent);
        addressBook.setStudent(target, editedStudent);
    }

    @Override
    public void markStudentAttendance(Student target, int week) {
        requireAllNonNull(target, week);
        Student newStudent = target.clone();
        newStudent.toggleAttendance(week);
        setStudent(target, newStudent);
    }

    @Override
    public String getStudentAttendance(Student target, int week) {
        requireAllNonNull(target, week);
        return target.checkPresent(week) ? "present" : "absent";
    }

    @Override
    public void markStudentParticipation(Student target, int week) {
        requireAllNonNull(target, week);
        Student newStudent = target.clone();
        newStudent.toggleParticipation(week);
        setStudent(target, newStudent);
    }

    @Override
    public String getStudentParticipation(Student target, int week) {
        requireAllNonNull(target, week);
        return target.checkParticipated(week) ? "participated" : "not participated";
    }

    @Override
    public void addMember(Student student, Group group) {
        requireAllNonNull(student, group);
        Student updatedStudent = new Student(student, group.getName());
        group.addMember(updatedStudent);
        addressBook.setStudent(student, updatedStudent);
    }

    @Override
    public void deleteMember(Student student, Group group) {
        requireAllNonNull(student, group);
        addressBook.deleteStudentFromGroup(student, group);
        addressBook.removeGroupFromStudent(student);
    }

    /**
     * Checks if a task exists
     *
     * @param task task to check
     * @return true if task exists, false otherwise
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return addressBook.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        addressBook.removeTask(target);
    }

    @Override
    public void addTask(Task student) {
        addressBook.addTask(student);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        addressBook.setTask(target, editedTask);
    }

    @Override
    public void toggleTaskIsDone(Task target) {
        requireAllNonNull(target, target);
        target.toggleIsDone();
        addressBook.sortTasks();
        displayType = TASKS;
    }

    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return addressBook.hasGroup(group);
    }

    @Override
    public void deleteGroup(Group target) {
        requireNonNull(target);
        List<Student> students = target.getMembersList();
        addressBook.clearGroupFromStudents(students);
        addressBook.deleteGroup(target);
    }

    @Override
    public void addGroup(Group group) {
        addressBook.addGroup(group);
        updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
    }

    @Override
    public void setGroup(Group target, Group editedGroup) {
        requireAllNonNull(target, editedGroup);

        for (Student student : target.getMembers().studentList) {
            Student updatedStudent = new Student(student, editedGroup.getName());
            editedGroup.updateMember(student, updatedStudent);
            addressBook.setStudent(student, updatedStudent);
        }

        addressBook.setGroup(target, editedGroup);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    public ObservableList<Student> getAllStudentList() {
        filteredStudents.setPredicate(PREDICATE_SHOW_ALL_STUDENTS);
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        displayType = STUDENTS;
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        displayType = TASKS;
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    //=========== Filtered Group List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Group} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Group> getFilteredGroupList() {
        return filteredGroups;
    }

    @Override
    public ObservableList<Group> getAllGroupList() {
        filteredGroups.setPredicate(PREDICATE_SHOW_ALL_GROUPS);
        return filteredGroups;
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        displayType = GROUPS;
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents)
                && filteredTasks.equals(other.filteredTasks)
                && filteredGroups.equals(other.filteredGroups);
    }
}
