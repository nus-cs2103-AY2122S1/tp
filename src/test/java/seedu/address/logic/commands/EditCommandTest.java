package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.CsBook;
import seedu.address.model.ReadOnlyCsBook;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.StudentBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private ModelStubAllowingEditStudent model = new ModelStubAllowingEditStudent();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        model.addStudent(ALICE);
        model.addStudent(BENSON);

        Student editedStudent = new StudentBuilder(ALICE).build();
        // get descriptor of new student with all same fields
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        ModelStubAllowingEditStudent expectedModel = new ModelStubAllowingEditStudent();
        expectedModel.addStudent(ALICE);
        expectedModel.addStudent(BENSON);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        model.addStudent(ALICE);
        model.addStudent(BENSON);

        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BOB)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).build();

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
            .withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).build();
        EditCommand editCommand = new EditCommand(indexLastStudent, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        ModelStubAllowingEditStudent expectedModel = new ModelStubAllowingEditStudent();
        expectedModel.addStudent(ALICE);
        expectedModel.addStudent(BENSON);
        expectedModel.setStudent(lastStudent, editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        model.addStudent(ALICE);
        model.addStudent(BENSON);

        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT, new EditCommand.EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        ModelStubAllowingEditStudent expectedModel = new ModelStubAllowingEditStudent();
        expectedModel.addStudent(ALICE);
        expectedModel.addStudent(BENSON);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        model.addStudent(ALICE);
        model.addStudent(BENSON);

        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT,
            new EditStudentDescriptorBuilder(editedStudent).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        ModelStubAllowingEditStudent expectedModel = new ModelStubAllowingEditStudent();
        expectedModel.addStudent(ALICE);
        expectedModel.addStudent(BENSON);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        model.addStudent(ALICE);
        model.addStudent(BENSON);

        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_STUDENT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        model.addStudent(ALICE);
        model.addStudent(BENSON);

        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        // edit student in filtered list into a duplicate in address book, although out of bounds of the filtered list
        Student studentInList = BENSON;
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT,
            new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        model.addStudent(ALICE);
        model.addStudent(BENSON);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
            .build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        model.addStudent(ALICE);
        model.addStudent(BENSON);

        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        // outOfBoundIndex is still in bounds of address book list
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_STUDENT, DESC_AMY);

        // same values -> returns true
        EditCommand.EditStudentDescriptor copyDescriptor = new EditCommand.EditStudentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_STUDENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_STUDENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_STUDENT, DESC_BOB)));
    }


    /**
     * A Model stub that contains students
     */
    private class ModelStubWithStudents extends ModelStub {
        protected final ObservableList<Student> students;
        protected final FilteredList<Student> filteredStudents;

        ModelStubWithStudents() {
            students = FXCollections.observableArrayList();
            filteredStudents = new FilteredList<>(students);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            students.add(student);
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return students.stream().anyMatch(student::isSameStudent);
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            requireNonNull(predicate);
            this.filteredStudents.setPredicate(predicate);
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return this.filteredStudents;
        }

        @Override
        public ReadOnlyCsBook getCsBook() {
            return new CsBook();
        }


        @Override
        public boolean equals(Object obj) {
            // short circuit if same object
            if (obj == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(obj instanceof ModelStubWithStudents)) {
                return false;
            }

            // state check
            ModelStubWithStudents other = (ModelStubWithStudents) obj;
            return students.equals(other.students)
                    && filteredStudents.equals(other.filteredStudents);
        }
    }

    private class ModelStubAllowingEditStudent extends ModelStubWithStudents {
        @Override
        public void setStudent(Student target, Student editedStudent) {
            requireAllNonNull(target, editedStudent);

            int index = students.indexOf(target);
            // whether target exists or not should be checked by the command beforehand
            this.students.set(index, editedStudent);
        }
    }
}
