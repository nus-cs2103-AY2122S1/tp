package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.teachbook.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_DEFAULT_INITIAL_CLASS;
import static seedu.teachbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.teachbook.commons.core.GuiSettings;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.ReadOnlyUserPrefs;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.gradeobject.GradingSystem;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.student.UniqueStudentList;
import seedu.teachbook.testutil.StudentBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_studentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingStudentAdded modelStub = new ModelStubAcceptingStudentAdded();
        Student validStudent = new StudentBuilder().build();

        CommandResult commandResult = new AddCommand(validStudent).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_ADD_STUDENT_SUCCESS, validStudent),
                commandResult.getFeedbackToUser());
        UniqueStudentList expectedList = new UniqueStudentList();
        expectedList.add(validStudent);
        assertEquals(expectedList, modelStub.classObj.getUniqueStudentListOfThisClass());
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student validStudent = new StudentBuilder().build();
        AddCommand addCommand = new AddCommand(validStudent);
        ModelStub modelStub = new ModelStubWithStudent(validStudent);
        assertThrows(CommandException.class, MESSAGE_DUPLICATE_STUDENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Student alice = new StudentBuilder().withName("Alice").build();
        Student bob = new StudentBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different student -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTeachBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTeachBookFilePath(Path teachBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTeachBook(ReadOnlyTeachBook teachBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTeachBook getTeachBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClass(Class aClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClass(Class aClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean deleteClass(Class target, GeneralIndex targetIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClassForStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Class> getUniqueClassList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GeneralIndex getIndexOfClass(ClassNameDescriptor newClassName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GradingSystem getGradingSystem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGradingSystem(GradingSystem gradingSystem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExistingGradingSystem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isValidGrade(Grade grade) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateCurrentlySelectedClass(GeneralIndex newClassIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void reorderStudents(Comparator<? super Student> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GeneralIndex getCurrentlySelectedClassIndex() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoTeachBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoTeachBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitTeachBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetGradingSystem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isTeachBookEmpty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isListAll() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClassName(ClassName updatedClassName) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithStudent extends ModelStub {
        private final Student student;

        ModelStubWithStudent(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public GeneralIndex getCurrentlySelectedClassIndex() {
            // called by {@code AddCommand#execute()}
            return INDEX_DEFAULT_INITIAL_CLASS;
        }

        @Override
        public void setClassForStudent(Student student) {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return this.student.isSameStudent(student);
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingStudentAdded extends ModelStub {
        final Class classObj = new Class(new ClassName("A"));

        @Override
        public GeneralIndex getCurrentlySelectedClassIndex() {
            // called by {@code AddCommand#execute()}
            return INDEX_DEFAULT_INITIAL_CLASS;
        }

        @Override
        public void setClassForStudent(Student student) {
            student.setStudentClass(classObj);
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return classObj.containsStudent(student);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            classObj.addStudent(student);
        }

        @Override
        public ReadOnlyTeachBook getTeachBook() {
            return new TeachBook();
        }

        @Override
        public void commitTeachBook() {
            // called by {@code AddCommand#execute()}
        }
    }

}
