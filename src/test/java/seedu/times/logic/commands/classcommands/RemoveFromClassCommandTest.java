package seedu.times.logic.commands.classcommands;

import static java.util.Objects.requireNonNull;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.logic.commands.classcommands.RemoveFromClassCommand.MESSAGE_REMOVE_SUCCESS;
import static seedu.times.testutil.Assert.assertThrows;
import static seedu.times.testutil.TestUtil.getIndexList;
import static seedu.times.testutil.TypicalTimestable.ALICE;
import static seedu.times.testutil.TypicalTimestable.CARL;
import static seedu.times.testutil.TypicalTimestable.getTypicalStudents;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.times.commons.core.GuiSettings;
import seedu.times.commons.core.Messages;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.ReadOnlyUserPrefs;
import seedu.times.model.person.Name;
import seedu.times.model.person.Person;
import seedu.times.model.person.Student;
import seedu.times.model.person.UniqueStudentList;
import seedu.times.model.tuitionclass.ClassName;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;
import seedu.times.model.tuitionclass.StudentNameList;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.model.tuitionclass.UniqueClassList;

public class RemoveFromClassCommandTest {


    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveFromClassCommand(null));
    }

    @Test
    public void execute_removeSingleStudent_success() {
        // model stub with a class containing ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE.
        ModelStubWithClass modelStubWithClass = new ModelStubWithClass(getTypicalStudents());

        // command to execute
        RemoveFromClassCommand command = new RemoveFromClassCommand(getIndexList(1, 1));

        // expected model
        List<Student> studentList = getTypicalStudents();
        studentList.remove(ALICE);
        ModelStubWithClass expectedModelStub = new ModelStubWithClass(studentList);

        String expectedMessage = MESSAGE_REMOVE_SUCCESS;
        assertCommandSuccess(command, modelStubWithClass, expectedMessage, expectedModelStub);
    }

    @Test
    public void execute_removeMultipleStudents_success() {
        // model stub with a class containing ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE.
        ModelStubWithClass modelStubWithClass = new ModelStubWithClass(getTypicalStudents());

        // command to execute
        RemoveFromClassCommand command = new RemoveFromClassCommand(getIndexList(1, 1, 3));

        // expected model
        List<Student> studentList = getTypicalStudents();
        studentList.remove(ALICE);
        studentList.remove(CARL);
        ModelStubWithClass expectedModelStub = new ModelStubWithClass(studentList);

        String expectedMessage = MESSAGE_REMOVE_SUCCESS;
        assertCommandSuccess(command, modelStubWithClass, expectedMessage, expectedModelStub);
    }

    @Test
    public void execute_duplicateStudentIndexes_success() {
        // model stub with a class containing ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE.
        ModelStubWithClass modelStubWithClass = new ModelStubWithClass(getTypicalStudents());

        // command to execute
        RemoveFromClassCommand command = new RemoveFromClassCommand(getIndexList(1, 1, 3, 1));

        // expected model
        List<Student> studentList = getTypicalStudents();
        studentList.remove(ALICE);
        studentList.remove(CARL);
        ModelStubWithClass expectedModelStub = new ModelStubWithClass(studentList);

        String expectedMessage = MESSAGE_REMOVE_SUCCESS;
        assertCommandSuccess(command, modelStubWithClass, expectedMessage, expectedModelStub);

    }

    @Test
    public void execute_studentIndexGreaterThanNumberOfStudents_throwsCommandException() {
        // model stub with a class containing ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE.
        ModelStubWithClass modelStubWithClass = new ModelStubWithClass(getTypicalStudents());

        // command to execute
        RemoveFromClassCommand command = new RemoveFromClassCommand(getIndexList(1, 8));

        String expectedMessage = Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(modelStubWithClass));
    }


    @Test
    public void execute_classIndexGreaterThanClassNumber_throwsCommandException() {
        // model stub with a class containing ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE.
        ModelStubWithClass modelStubWithClass = new ModelStubWithClass(getTypicalStudents());

        // command to execute
        RemoveFromClassCommand command = new RemoveFromClassCommand(getIndexList(2, 1));

        String expectedMessage = Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX;
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(modelStubWithClass));
    }

    @Test
    public void execute_removeSingleStudentFromEmptyClass_failure() {
        // model stub with a empty class;
        ModelStubWithClass modelStubWithClass = new ModelStubWithClass(new ArrayList<Student>());

        // command to execute
        RemoveFromClassCommand command = new RemoveFromClassCommand(getIndexList(1, 1));

        String expectedMessage = Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(modelStubWithClass));
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
     * A Model stub that contains a single tuition class.
     */
    private class ModelStubWithClass extends ModelStub {
        private TuitionClass tuitionClass;
        private List<Student> studentList;

        ModelStubWithClass(List<Student> studentList) {
            requireNonNull(studentList);
            this.studentList = studentList;
            Name[] names = studentList.stream().map(Person::getName)
                    .collect(Collectors.toList()).toArray(new Name[]{});
            StudentNameList nameList = new StudentNameList(names);

            this.tuitionClass = new TuitionClass(new ClassName("class"), new ClassTiming("MON 13:30-14:30"),
                    new Location("Home"), new Rate("60"), nameList);
        }

        @Override
        public ObservableList<TuitionClass> getFilteredTuitionClassList() {
            UniqueClassList classList = new UniqueClassList();
            classList.add(this.tuitionClass);

            return new FilteredList<>(classList.asUnmodifiableObservableList());
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            UniqueStudentList studentList = new UniqueStudentList();
            for (Student student : this.studentList) {
                studentList.add(student);
            }
            return new FilteredList<>(studentList.asUnmodifiableObservableList());
        }

        @Override
        public void setClass(TuitionClass classToRemoveFrom, TuitionClass editedClass) {
            this.tuitionClass = editedClass;
        }

        @Override
        public boolean equals(Object obj) {
            // short circuit if same object
            if (obj == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(obj instanceof ModelStubWithClass)) {
                return false;
            }

            // state check
            ModelStubWithClass other = (ModelStubWithClass) obj;
            return this.tuitionClass.equals(other.tuitionClass);
        }
    }
}
