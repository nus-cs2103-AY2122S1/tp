package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.programmer.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.programmer.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.programmer.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_CLASS_ID_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.programmer.logic.commands.EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS;
import static seedu.programmer.logic.commands.EditCommand.MESSAGE_NO_LAB_EDITED;
import static seedu.programmer.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.programmer.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;
import static seedu.programmer.testutil.TypicalStudents.getTypicalStudents;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.programmer.commons.core.Messages;
import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.ReadOnlyProgrammerError;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.DisplayableObject;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.comparator.SortByClass;
import seedu.programmer.model.student.comparator.SortByLabNumber;
import seedu.programmer.model.student.comparator.SortByStudentName;
import seedu.programmer.model.student.exceptions.StudentNotFoundException;
import seedu.programmer.testutil.EditStudentDescriptorBuilder;
import seedu.programmer.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Model modelStubWithStudents = new ModelStubWithStudents(getTypicalStudents());
        Student editedStudent = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT, descriptor);
        String expectedMessage = String.format(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent) + "\n"
                + MESSAGE_NO_LAB_EDITED, editedStudent);
        Model expectedModel = new ModelStubWithStudents(getTypicalStudents());
        expectedModel.setStudent(expectedModel.getAllStudents().get(INDEX_FIRST_STUDENT.getZeroBased()), editedStudent);
        assertCommandSuccess(editCommand, modelStubWithStudents, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Model modelStubWithStudents = new ModelStubWithStudents(getTypicalStudents());

        Index indexLastStudent = Index.fromOneBased(modelStubWithStudents.getFilteredStudentList().size());
        Student lastStudent = modelStubWithStudents.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BOB).withClassId(VALID_CLASS_ID_BOB).build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withClassId(VALID_CLASS_ID_BOB).build();
        EditCommand editCommand = new EditCommand(indexLastStudent, descriptor);

        String expectedMessage = String.format(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent)
                + "\n" + MESSAGE_NO_LAB_EDITED, editedStudent);

        Model expectedModel = new ModelStubWithStudents(getTypicalStudents());
        expectedModel.setStudent(lastStudent, editedStudent);

        assertCommandSuccess(editCommand, modelStubWithStudents, expectedMessage, expectedModel);

    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT, new EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_NO_NEW_FIELDS, editedStudent);

        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_STUDENT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT_ID);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        // edit student in filtered list into a duplicate in ProgrammerError
        Student studentInList = model.getProgrammerError().getStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT,
                new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT_ID);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of ProgrammerError
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of ProgrammerError list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProgrammerError().getStudentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void test_equals_returnsCorrectly() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_STUDENT, DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_STUDENT, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new PurgeCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_SECOND_STUDENT, DESC_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_FIRST_STUDENT, DESC_BOB));
    }


    /**
     * A Model stub that contains a list of student.
     */
    private static class ModelStubWithStudents extends ModelStub {
        private final List<Student> students;

        ModelStubWithStudents(List<Student> students) {
            requireNonNull(students);
            this.students = students;
        }


        @Override
        public boolean hasOtherStudent(Student studentToEdit, Student editedStudent) {
            requireAllNonNull(studentToEdit, editedStudent);
            List<Student> studentListCopy = new ArrayList<>();
            for (Student student : students) {
                studentListCopy.add(student.copy());
            }
            studentListCopy.remove(studentToEdit);
            return studentListCopy.stream().anyMatch(editedStudent::isSameStudent);
        }

        @Override
        public boolean hasOtherSameStudentId(Student studentToEdit, Student editedStudent) {
            requireAllNonNull(studentToEdit, editedStudent);
            List<Student> studentListCopy = new ArrayList<>();
            for (Student student : students) {
                studentListCopy.add(student.copy());
            }
            studentListCopy.remove(studentToEdit);
            return studentListCopy.stream().anyMatch(editedStudent::isSameStudentId);
        }

        @Override
        public boolean hasOtherSameStudentEmail(Student studentToEdit, Student editedStudent) {
            requireAllNonNull(studentToEdit, editedStudent);
            List<Student> studentListCopy = new ArrayList<>();
            for (Student student : students) {
                studentListCopy.add(student.copy());
            }
            studentListCopy.remove(studentToEdit);
            return studentListCopy.stream().anyMatch(editedStudent::isSameStudentEmail);
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            requireAllNonNull(target, editedStudent);

            int index = students.indexOf(target);
            if (index == -1) {
                throw new StudentNotFoundException();
            }

            students.set(index, editedStudent);
            students.sort(new SortByClass().thenComparing(new SortByStudentName()));
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return FXCollections.observableArrayList(students);
        }

        @Override
        public ObservableList<Student> getAllStudents() {
            return FXCollections.observableArrayList(students);
        }

        @Override
        public void setSelectedStudent(Student target) {

        }

        @Override
        public void setSelectedLabs(List<Lab> labs) {

        }

        @Override
        public void setLabsTracker(List<Lab> labs) {

        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof ModelStubWithStudents// instanceof handles nulls
                    && students.equals(((ModelStubWithStudents) other).students)); // state check
        }
    }

    /**
     * A Model stub that always accept the student being edited.
     */
    private static class ModelStubAcceptingStudentEdited extends ModelStub {
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
