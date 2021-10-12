package seedu.academydirectory.model;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.academydirectory.commons.core.GuiSettings;
import seedu.academydirectory.commons.core.LogsCenter;
import seedu.academydirectory.model.student.Student;

/**
 * Represents the in-memory model of the academy directory data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AcademyDirectory academyDirectory;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given academyDirectory and userPrefs.
     */
    public ModelManager(ReadOnlyAcademyDirectory academyDirectory, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(academyDirectory, userPrefs);

        logger.fine("Initializing with academy directory: " + academyDirectory + " and user prefs " + userPrefs);

        this.academyDirectory = new AcademyDirectory(academyDirectory);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.academyDirectory.getStudentList());
    }

    public ModelManager() {
        this(new AcademyDirectory(), new UserPrefs());
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
    public Path getAcademyDirectoryFilePath() {
        return userPrefs.getAcademyDirectoryFilePath();
    }

    @Override
    public void setAcademyDirectoryFilePath(Path academyDirectoryFilePath) {
        requireNonNull(academyDirectoryFilePath);
        userPrefs.setAcademyDirectoryFilePath(academyDirectoryFilePath);
    }

    //=========== AcademyDirectory ================================================================================

    @Override
    public void setAcademyDirectory(ReadOnlyAcademyDirectory academyDirectory) {
        this.academyDirectory.resetData(academyDirectory);
    }

    @Override
    public ReadOnlyAcademyDirectory getAcademyDirectory() {
        return academyDirectory;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return academyDirectory.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        academyDirectory.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        academyDirectory.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override

    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        academyDirectory.setStudent(target, editedStudent);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAcademyDirectory}
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
    public <T> ObservableList<T> getFilteredStudentListView(Function<? super Student, ? extends T> function) {
        requireNonNull(function);
        return filteredStudents
                .stream()
                .map(function)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
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
        return academyDirectory.equals(other.academyDirectory)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
