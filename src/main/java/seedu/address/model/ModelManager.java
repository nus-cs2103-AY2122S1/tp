package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.UniqueAssessmentList;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupGroupNameEqualsPredicate;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.ContainsStudentNamePredicate;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.Student;
import seedu.address.model.student.TelegramHandle;

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
        return hasStudent(student.getName());
    }

    @Override
    public boolean hasStudent(Name name) {
        requireNonNull(name);
        return getStudentByName(name) != null;
    }

    @Override
    public void changeStudentGroup(Student student, Group newGroup) {
        requireNonNull(student);
        Student foundStudent = getStudentByName(student.getName());
        Name name = foundStudent.getName();
        TelegramHandle telegramHandle = foundStudent.getTelegramHandle();
        Email email = foundStudent.getEmail();
        Note note = foundStudent.getNote();
        GroupName groupName = newGroup.getGroupName();
        UniqueAssessmentList assessments = foundStudent.getUniqueAssessmentList();
        Student updatedStudent = new Student(name, telegramHandle, email, note, groupName, assessments);
        csBook.setStudent(foundStudent, updatedStudent);
    }

    @Override
    public Student updateStudentNote(Student student, Note updatedNote) {
        Name name = student.getName();
        TelegramHandle telegramHandle = student.getTelegramHandle();
        Email email = student.getEmail();
        GroupName groupName = student.getGroupName();
        UniqueAssessmentList assessments = student.getUniqueAssessmentList();
        Student editedStudent = new Student(name, telegramHandle, email, updatedNote, groupName, assessments);
        csBook.setStudent(student, editedStudent);
        return editedStudent;
    }

    @Override
    public void deleteStudent(Student target) {
        // Retrieve existing group in model
        GroupName groupName = target.getGroupName();
        Group retrievedGroup = getGroupByGroupName(groupName);

        // Remove reference to student from the group that the student belonged to
        retrievedGroup.removeStudentName(target.getName());

        csBook.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        // Retrieve existing group in model
        GroupName groupName = student.getGroupName();
        Group retrievedGroup = getGroupByGroupName(groupName);

        // Add reference to student into the group
        retrievedGroup.addStudentName(student.getName());

        csBook.addStudent(student);
    }

    @Override
    public Student getStudentByName(Name studentName) {
        FilteredList<Student> tempFilteredStudents = new FilteredList<>(this.csBook.getStudentList());
        tempFilteredStudents.setPredicate(new ContainsStudentNamePredicate(studentName));

        // return null if the student is not found
        if (tempFilteredStudents.isEmpty()) {
            return null;
        }

        assert tempFilteredStudents.size() == 1 : "Students name should be unique";

        Student retrievedStudent = tempFilteredStudents.get(0);

        return retrievedStudent;
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        csBook.setStudent(target, editedStudent);

        // remove old student name from its group
        getGroupByGroupName(target.getGroupName()).removeStudentName(target.getName());

        // add new student name to its group
        getGroupByGroupName(editedStudent.getGroupName()).addStudentName(editedStudent.getName());
    }

    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return hasGroup(group.getGroupName());
    }

    @Override
    public boolean hasGroup(GroupName groupName) {
        requireNonNull(groupName);
        return getGroupByGroupName(groupName) != null;
    }

    @Override
    public void updateGroupStudent(Group group, Student student) {
        requireAllNonNull(group, student);
        // get students original group
        GroupName oldGroupName = student.getGroupName();
        Group oldGroup = getGroupByGroupName(oldGroupName);

        // remove reference to student in old group
        oldGroup.removeStudentName(student.getName());

        // get new group
        GroupName newGroupName = group.getGroupName();
        Group newGroup = getGroupByGroupName(newGroupName);

        // add reference to student in new group
        newGroup.addStudentName(student.getName());
    }

    @Override
    public void deleteGroup(Group target) {
        Set<Name> namesOfStudentsToDelete = target.getStudentNames();

        // Delete all students associated with the group
        for (Name studentName : namesOfStudentsToDelete) {
            updateFilteredStudentList((student -> student.getName().equals(studentName)));
            // Should have only 1 student found since we cant have students with same name
            assert(filteredStudents.size() == 1);
            csBook.removeStudent(filteredStudents.get(0));
        }
        //Resets filtered student list to show all students
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        csBook.removeGroup(target);
    }

    @Override
    public void addGroup(Group group) {
        csBook.addGroup(group);
        updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
    }

    @Override
    public Group getGroupByGroupName(GroupName groupName) {
        FilteredList<Group> tempFilteredGroups = new FilteredList<>(this.csBook.getGroupList());
        tempFilteredGroups.setPredicate(new GroupGroupNameEqualsPredicate(groupName));

        // return null if the group is not found
        if (tempFilteredGroups.isEmpty()) {
            return null;
        }

        assert tempFilteredGroups.size() == 1 : "Group names should be unique";

        Group retrievedGroup = tempFilteredGroups.get(0);
        return retrievedGroup;
    }

    @Override
    public boolean hasAssessment(Student student, Assessment assessment) {
        requireNonNull(student);
        requireNonNull(assessment);
        return csBook.hasAssessment(student, assessment);
    }

    @Override
    public void addAssessment(Student student, Assessment assessment) {
        csBook.addAssessment(student, assessment);
    }

    @Override
    public void deleteAssessment(Student student, Assessment assessment) {
        csBook.deleteAssessment(student, assessment);
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
