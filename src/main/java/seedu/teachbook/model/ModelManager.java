package seedu.teachbook.model;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_DEFAULT_INITIAL_CLASS;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_LIST_ALL;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_NO_CLASS;
import static seedu.teachbook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.teachbook.commons.core.GuiSettings;
import seedu.teachbook.commons.core.LogsCenter;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.classobject.exceptions.NoClassWithNameException;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.gradeobject.GradingSystem;
import seedu.teachbook.model.student.Student;

/**
 * Represents the in-memory model of the teachbook data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedTeachBook teachBook;
    private final UserPrefs userPrefs;
    private FilteredList<Student> filteredStudents;
    private GeneralIndex currentlySelectedClassIndex;

    /**
     * Initializes a ModelManager with the given teachBook and userPrefs.
     */
    public ModelManager(ReadOnlyTeachBook teachBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(teachBook, userPrefs);

        logger.fine("Initializing with teachbook: " + teachBook + " and user prefs " + userPrefs);

        this.teachBook = new VersionedTeachBook(teachBook);
        this.userPrefs = new UserPrefs(userPrefs);

        initialiseCurrentlySelectedClassIndex();
    }

    public ModelManager() {
        this(new TeachBook(), new UserPrefs());
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
    public Path getTeachBookFilePath() {
        return userPrefs.getTeachBookFilePath();
    }

    @Override
    public void setTeachBookFilePath(Path teachBookFilePath) {
        requireNonNull(teachBookFilePath);
        userPrefs.setTeachBookFilePath(teachBookFilePath);
    }

    //=========== TeachBook ================================================================================

    @Override
    public ReadOnlyTeachBook getTeachBook() {
        return teachBook;
    }

    @Override
    public void setTeachBook(ReadOnlyTeachBook teachBook) {
        this.teachBook.resetData(teachBook);
        initialiseCurrentlySelectedClassIndex();
    }

    @Override
    public GeneralIndex getCurrentlySelectedClassIndex() {
        return currentlySelectedClassIndex;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return teachBook.hasStudent(currentlySelectedClassIndex, student);
    }

    @Override
    public boolean hasClass(Class aClass) {
        requireNonNull(aClass);
        return teachBook.hasClass(aClass);
    }

    @Override
    public void addClass(Class aClass) {
        teachBook.addClass(aClass);

        updateCurrentlySelectedClass(GeneralIndex.fromOneBased(teachBook.getClassList().size()));
    }

    @Override
    public boolean deleteClass(Class target, GeneralIndex targetIndex) {
        assert !targetIndex.equals(INDEX_NO_CLASS);
        assert teachBook.getClassAtIndex(targetIndex).equals(target);
        teachBook.removeClass(target);

        if (currentlySelectedClassIndex.equals(INDEX_LIST_ALL)) {
            updateCurrentlySelectedClass(INDEX_LIST_ALL);
            return true;
        }

        if (teachBook.getNumOfClasses() == 0) {
            updateCurrentlySelectedClass(INDEX_NO_CLASS);
            return true;
        }

        if (targetIndex.equals(currentlySelectedClassIndex)) { // currently selected class is deleted
            if (currentlySelectedClassIndex.getOneBased() > teachBook.getNumOfClasses()) {
                // currently selected class is the last class -> set currently selected class to the last class
                updateCurrentlySelectedClass(GeneralIndex.fromOneBased(teachBook.getNumOfClasses()));
            } else {
                // remain same index
                updateCurrentlySelectedClass(currentlySelectedClassIndex);
            }
            return true;
        } else if (targetIndex.isSmallerThan(currentlySelectedClassIndex)) {
            // minus currently selected class index by one, there is no need to update student list panel
            currentlySelectedClassIndex = currentlySelectedClassIndex.minusOne();
            return false;
        } else {
            // there is no need to update student list panel
            return false;
        }
    }

    @Override
    public void setClassName(ClassName updatedClassName) {
        teachBook.setClassName(currentlySelectedClassIndex, updatedClassName);
    }

    @Override
    public void deleteStudent(Student target) {
        teachBook.removeStudent(currentlySelectedClassIndex, target);
    }

    @Override
    public void addStudent(Student student) {
        assert (!currentlySelectedClassIndex.equals(INDEX_NO_CLASS));
        assert (!currentlySelectedClassIndex.equals(INDEX_LIST_ALL));

        teachBook.addStudent(currentlySelectedClassIndex, student);

        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        teachBook.setStudent(currentlySelectedClassIndex, target, editedStudent);
    }

    @Override
    public void setClassForStudent(Student student) {
        requireNonNull(student);
        assert (!currentlySelectedClassIndex.equals(INDEX_NO_CLASS));
        assert (!currentlySelectedClassIndex.equals(INDEX_LIST_ALL));

        teachBook.setClassForStudent(currentlySelectedClassIndex, student);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public ObservableList<Class> getUniqueClassList() {
        return teachBook.getClassList();
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public GeneralIndex getIndexOfClass(ClassNameDescriptor className) throws NoClassWithNameException {
        return teachBook.getIndexOfClass(className);
    }

    @Override
    public GradingSystem getGradingSystem() {
        return teachBook.getGradingSystem();
    }

    @Override
    public void setGradingSystem(GradingSystem gradingSystem) {
        teachBook.setGradingSystem(gradingSystem);
    }

    @Override
    public void updateCurrentlySelectedClass(GeneralIndex newClassIndex) {
        requireNonNull(newClassIndex);
        currentlySelectedClassIndex = newClassIndex;
        updateSourceOfFilteredStudentList();
    }

    private void updateSourceOfFilteredStudentList() {
        if (currentlySelectedClassIndex.equals(INDEX_LIST_ALL)) {
            filteredStudents = new FilteredList<>(teachBook.getStudentList());
        } else if (currentlySelectedClassIndex.equals(INDEX_NO_CLASS)) {
            filteredStudents = new FilteredList<>(FXCollections.observableArrayList());
        } else {
            filteredStudents = new FilteredList<>(teachBook.getStudentListOfClass(currentlySelectedClassIndex));
        }
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    private void initialiseCurrentlySelectedClassIndex() {
        updateCurrentlySelectedClass(INDEX_NO_CLASS);
        if (this.teachBook.getNumOfClasses() >= 1) {
            updateCurrentlySelectedClass(INDEX_DEFAULT_INITIAL_CLASS);
        }
    }

    @Override
    public void resetGradingSystem() {
        teachBook.resetGradingSystem(currentlySelectedClassIndex);
    }

    @Override
    public boolean hasExistingGradingSystem() {
        return teachBook.hasExistingGradingSystem();
    }

    @Override
    public boolean isValidGrade(Grade grade) {
        return teachBook.isValidGrade(grade);
    }

    @Override
    public void reorderStudents(Comparator<? super Student> comparator) {
        assert (!currentlySelectedClassIndex.equals(INDEX_NO_CLASS));

        teachBook.reorderStudents(currentlySelectedClassIndex, comparator);
    }

    //=========== Undo/Redo =================================================================================


    @Override
    public boolean canUndoAddressBook() {
        return this.teachBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return this.teachBook.canRedo();
    }

    @Override
    public void undoTeachBook() {
        TeachbookDisplayState previousDisplay = this.teachBook.undo();
        this.updateCurrentlySelectedClass(previousDisplay.getIndex());
        this.updateFilteredStudentList(previousDisplay.getPredicate());
    }

    @Override
    public void redoTeachBook() {
        TeachbookDisplayState previousDisplay = this.teachBook.redo();
        this.updateCurrentlySelectedClass(previousDisplay.getIndex());
        this.updateFilteredStudentList(previousDisplay.getPredicate());
    }

    @Override
    public void commitTeachBook() {
        @SuppressWarnings("unchecked")
        Predicate<Student> currentPredicate = (Predicate<Student>) filteredStudents.getPredicate();
        this.teachBook.commit(currentlySelectedClassIndex, currentPredicate);
    }

    @Override
    public boolean isTeachBookEmpty() {
        return this.teachBook.isEmpty();
    }

    @Override
    public boolean isListAll() {
        return currentlySelectedClassIndex.equals(INDEX_LIST_ALL);
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
        return teachBook.equals(other.teachBook)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
