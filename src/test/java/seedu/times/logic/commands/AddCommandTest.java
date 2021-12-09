package seedu.times.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.times.commons.core.GuiSettings;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.ReadOnlyUserPrefs;
import seedu.times.model.TimesTable;
import seedu.times.model.person.Name;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Student validStudent = new PersonBuilder().build();

        CommandResult commandResult =
                new AddCommand(validStudent).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validStudent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudent), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student validStudent = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validStudent);
        ModelStub modelStub = new ModelStubWithPerson(validStudent);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Student alice = new PersonBuilder().withName("Alice").build();
        Student bob = new PersonBuilder().withName("Bob").build();
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

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
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
        public Path getTimesTableFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTimesTableFilePath(Path timesTableFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTimesTable(ReadOnlyTimesTable newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTimesTable getTimesTable() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTuitionClass(TuitionClass tuitionClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTuitionClass(TuitionClass tuitionClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTuitionClass(TuitionClass target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClassList(Predicate<TuitionClass> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TuitionClass> getFilteredTuitionClassList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClass(TuitionClass target, TuitionClass editedClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClasses(List<TuitionClass> classes) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudents(List<Student> students) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateClassStudentLists(Name newName, Name oldName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortStudents(Comparator<? super Student> c) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortClasses(Comparator<? super TuitionClass> c) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Student student;

        ModelStubWithPerson(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return this.student.isSamePerson(student);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Student> personsAdded = new ArrayList<>();
        final ArrayList<TuitionClass> tuitionClassesAdded = new ArrayList<>();

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return personsAdded.stream().anyMatch(student::isSamePerson);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            personsAdded.add(student);
        }

        @Override
        public void addTuitionClass(TuitionClass tuitionClass) {
            requireNonNull(tuitionClass);
            tuitionClassesAdded.add(tuitionClass);
        }

        @Override
        public ReadOnlyTimesTable getTimesTable() {
            return new TimesTable();
        }
    }

}
