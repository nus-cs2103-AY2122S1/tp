package nustracker.logic.commands;

import static nustracker.testutil.TypicalAddressBook.getTypicalAddressBook;
import static nustracker.testutil.TypicalStudents.STUDENTID_ONE;
import static nustracker.testutil.TypicalStudents.STUDENTID_TWO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.commons.core.index.Index;
import nustracker.model.AddressBook;
import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.UserPrefs;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.testutil.EditStudentDescriptorBuilder;
import nustracker.testutil.StudentBuilder;
import nustracker.testutil.TypicalIndexes;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder().withMajor("IS").build();
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent)
                .withMajor("IS").build();
        EditCommand editCommand = new EditCommand(
                STUDENTID_ONE,
                descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(CommandTestUtil.VALID_NAME_BOB).withPhone(
                        CommandTestUtil.VALID_PHONE_BOB).build();

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(
                        CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(
                model.getFilteredStudentList().get(indexLastStudent.getZeroBased()).getStudentId(),
                descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudent);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(
                STUDENTID_ONE,
                new EditCommand.EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(TypicalIndexes.INDEX_FIRST_STUDENT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showStudentAtIndex(model, TypicalIndexes.INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(
                TypicalIndexes.INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(
                CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(
                STUDENTID_ONE,
                new EditStudentDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Student firstStudent = model.getFilteredStudentList().get(TypicalIndexes.INDEX_FIRST_STUDENT.getZeroBased());
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditCommand editCommand = new EditCommand(
                STUDENTID_TWO,
                descriptor);

        CommandTestUtil.assertCommandFailureShownStudentList(editCommand, model,
                String.format(EditCommand.MESSAGE_DUPLICATE_STUDENT, "the STUDENT ID " + firstStudent.getStudentId()));
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        CommandTestUtil.showStudentAtIndex(model, TypicalIndexes.INDEX_FIRST_STUDENT);

        // edit student in filtered list into a duplicate in address book
        Student studentInList = model.getAddressBook().getStudentList().get(
                TypicalIndexes.INDEX_SECOND_STUDENT.getZeroBased());
        EditCommand editCommand = new EditCommand(
                STUDENTID_ONE,
                new EditStudentDescriptorBuilder(studentInList).build());

        CommandTestUtil.assertCommandFailureShownStudentList(editCommand, model,
                String.format(EditCommand.MESSAGE_DUPLICATE_STUDENT, "the STUDENT ID " + studentInList.getStudentId()));
    }

    @Test
    public void execute_invalidStudentIdUnfilteredList_failure() {
        StudentId studentId = new StudentId("e9999999");
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(
                CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(
                studentId,
                descriptor);

        CommandTestUtil.assertCommandFailureShownStudentList(editCommand,
                model,
                String.format(Messages.MESSAGE_INVALID_STUDENTID, studentId.getStudentIdString()));
    }


    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(
                STUDENTID_ONE,
                CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditCommand.EditStudentDescriptor copyDescriptor = new EditCommand.EditStudentDescriptor(
                CommandTestUtil.DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(
                STUDENTID_ONE,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new StudentsCommand()));

        // different student ID -> returns false
        assertFalse(standardCommand.equals(new EditCommand(
                STUDENTID_TWO,
                CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(
                STUDENTID_ONE,
                CommandTestUtil.DESC_BOB)));
    }

}
