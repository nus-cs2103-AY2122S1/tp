package seedu.tuitione.model;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.tuitione.commons.core.GuiSettings;
import seedu.tuitione.commons.core.LogsCenter;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.Student;

/**
 * Represents the in-memory model of the tuitione book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Tuitione tuitione;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Lesson> filteredLessons;

    /**
     * Initializes a ModelManager with the given tuitione and userPrefs.
     */
    public ModelManager(ReadOnlyTuitione tuitione, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(tuitione, userPrefs);

        logger.fine("Initializing with tuitione book: " + tuitione + " and user prefs " + userPrefs);

        this.tuitione = new Tuitione(tuitione);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.tuitione.getStudentList());
        filteredLessons = new FilteredList<>(this.tuitione.getLessonList());
    }

    public ModelManager() {
        this(new Tuitione(), new UserPrefs());
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
    public Path getTuitioneFilePath() {
        return userPrefs.getTuitioneFilePath();
    }

    @Override
    public void setTuitioneFilePath(Path tuitioneFilePath) {
        requireNonNull(tuitioneFilePath);
        userPrefs.setTuitioneFilePath(tuitioneFilePath);
    }

    //=========== Tuitione ================================================================================

    @Override
    public void setTuitione(ReadOnlyTuitione tuitione) {
        this.tuitione.resetData(tuitione);
    }

    @Override
    public ReadOnlyTuitione getTuitione() {
        return tuitione;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return tuitione.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        tuitione.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        tuitione.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        tuitione.setStudent(target, editedStudent);
    }

    @Override
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return tuitione.hasLesson(lesson);
    }

    @Override
    public void deleteLesson(Lesson target) {
        tuitione.removeLesson(target);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public void addLesson(Lesson lesson) {
        tuitione.addLesson(lesson);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireAllNonNull(target, editedLesson);
        tuitione.setLesson(target, editedLesson);
    }

    //=========== Filtered Student and Lesson List Accessors ======================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedTuitione}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Lesson} backed by the internal list of
     * {@code versionedTuitiONE}
     */
    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        return filteredLessons;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredLessonList(Predicate<Lesson> predicate) {
        requireNonNull(predicate);
        filteredLessons.setPredicate(predicate);
    }

    @Override
    public Optional<Student> searchStudents(Name arg) {
        requireAllNonNull(arg);

        ObservableList<Student> students = tuitione.getStudentList();
        for (Student current : students) {
            if (arg.equals(current.getName())) {
                return Optional.of(current);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Lesson> searchLessons(LessonCode arg) {
        requireAllNonNull(arg);

        ObservableList<Lesson> lessons = tuitione.getLessonList();
        for (Lesson current : lessons) {
            if (arg.equals(current.getLessonCode())) {
                return Optional.of(current);
            }
        }
        return Optional.empty();
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
        return tuitione.equals(other.tuitione)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents)
                && filteredLessons.equals(other.filteredLessons);
    }

}
