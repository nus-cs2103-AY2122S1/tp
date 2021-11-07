package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.programmer.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.model.Model;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.ReadOnlyUserPrefs;
import seedu.programmer.model.student.DisplayableObject;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.comparator.SortByLabNumber;
import seedu.programmer.testutil.StudentBuilder;

public class AddCommandTest {
    private static Student validStudent;
    private static Student sampleStudentA;
    private static AddCommand sampleCommandA;
    private static AddCommand sampleCommandB;

    @BeforeAll
    public static void oneTimeSetUp() {
        // Initialize sample students and Commands once before all tests
        validStudent = new StudentBuilder().build();
        sampleStudentA = new StudentBuilder().withName("Alice").build();
        Student sampleStudentB = new StudentBuilder().withName("Bob").build();
        sampleCommandA = new AddCommand(sampleStudentA);
        sampleCommandB = new AddCommand(sampleStudentB);
    }

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_studentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingStudentAdded modelStub = new ModelStubAcceptingStudentAdded();
        CommandResult commandResult = new AddCommand(validStudent).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validStudent), commandResult.getFeedbackToUser());
        assertEquals(List.of(validStudent), modelStub.studentsAdded);

        ObservableList<DisplayableObject> expectedSelectedInformation = FXCollections.observableArrayList();
        expectedSelectedInformation.add(validStudent);
        expectedSelectedInformation.addAll(validStudent.getLabList());
        assertEquals(expectedSelectedInformation, modelStub.getSelectedInformation());
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        AddCommand addCommand = new AddCommand(validStudent);
        ModelStub modelStub = new ModelStubWithStudent(validStudent);
        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_STUDENT_ID, () ->
            addCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        AddCommand addCommand = new AddCommand(validStudent);
        ModelStub modelStub = new ModelStubWithStudent(validStudent);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_STUDENT_ID, () ->
            addCommand.execute(modelStub));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        AddCommand sampleCommandACopy = new AddCommand(sampleStudentA);
        assertEquals(sampleCommandA, sampleCommandACopy);
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        assertNotEquals(1, sampleCommandA);
    }

    @Test
    public void equals_nullValue_returnsFalse() {
        assertNotEquals(null, sampleCommandA);
    }

    @Test
    public void equals_differentStudent_returnsFalse() {
        assertNotEquals(sampleCommandA, sampleCommandB);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private static class ModelStub implements Model {

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
        public Path getProgrammerErrorFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProgrammerErrorFilePath(Path programmerErrorFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) throws CommandException {
            throw new CommandException(AddCommand.MESSAGE_DUPLICATE_STUDENT_ID);
        }

        @Override
        public void setProgrammerError(ReadOnlyProgrammerError programmerError) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProgrammerError getProgrammerError() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            return false;
        }

        @Override
        public boolean hasSameStudentId(Student student) {
            return false;
        }

        @Override
        public boolean hasSameStudentEmail(Student student) {
            return false;
        }

        @Override
        public boolean hasOtherStudent(Student studentToEdit, Student editedStudent) {
            return false;
        }

        @Override
        public boolean hasOtherSameStudentId(Student studentToEdit, Student editedStudent) {
            return false;
        }

        @Override
        public boolean hasOtherSameStudentEmail(Student studentToEdit, Student editedStudent) {
            return false;
        }

        @Override
        public boolean hasLab(Lab lab) {
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
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getAllStudents() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DisplayableObject> getSelectedInformation() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student getSelectedStudent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedLabs(List<Lab> labs) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void clearSelectedInformation() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLabsTracker(List<Lab> labs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearLabsTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private static class ModelStubWithStudent extends ModelStub {
        private final Student student;

        ModelStubWithStudent(Student student) {
            requireNonNull(student);
            this.student = student;
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
    private static class ModelStubAcceptingStudentAdded extends ModelStub {
        final ArrayList<Student> studentsAdded = new ArrayList<>();
        private ObservableList<DisplayableObject> selectedInformation = FXCollections.observableArrayList();
        private Student selectedStudent;
        private List<Lab> labsTracker = new ArrayList<>();

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return studentsAdded.stream().anyMatch(student::isSameStudent);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            studentsAdded.add(student);
        }

        @Override
        public ObservableList<DisplayableObject> getSelectedInformation() {
            return selectedInformation;
        }

        @Override
        public Student getSelectedStudent() {
            return selectedStudent;
        }

        @Override
        public void setSelectedStudent(Student target) {
            this.selectedStudent = target;
            if (selectedInformation.isEmpty()) {
                this.selectedInformation.add(target);
            } else {
                ObservableList<Lab> labList = target.getLabList();
                labList.sort(new SortByLabNumber());
                this.selectedInformation.set(0, target);
            }
        }

        @Override
        public void setSelectedLabs(List<Lab> labs) {
            assert selectedInformation != null;
            if (!(selectedInformation.size() == 1)) {
                selectedInformation.remove(1, selectedInformation.size());
            }
            labs.sort(new SortByLabNumber());
            selectedInformation.addAll(labs);
        }

        @Override
        public ReadOnlyProgrammerError getProgrammerError() {
            return new ProgrammerError();
        }
    }

}
