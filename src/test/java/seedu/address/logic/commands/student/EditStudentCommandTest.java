package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MONITOR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalAddressBookObjects.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.descriptors.EditStudentDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.Address;
import seedu.address.model.person.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code EditStudentCommand}.
 */
public class EditStudentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        // check if all fields can be edited
        Student editedStudent = new StudentBuilder().buildForEdit();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT, descriptor);

        String expectedMessage = String.format(EditStudentCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);
        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStudent = Index.fromOneBased(7);
        Student lastStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_MONITOR).build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_MONITOR).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(indexLastStudent, descriptor);

        String expectedMessage = String.format(EditStudentCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudent);

        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    /*
    The following test cases test if the edit command will throw an exception if all the fields provided to the
    {@code editDescriptor} are the same as the ones possessed by the current student. We just test a subset of cases.
     */
    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        // if there is nothing to edit, tell the user that there is nothing to edit.
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
                new EditStudentDescriptor());
        assertCommandFailure(editStudentCommand, model, EditStudentCommand.MESSAGE_NOTHING_TO_EDIT);
    }

    @Test
    public void execute_studentAlreadyHasName_failure() {
        // if there is nothing to edit, tell the user that there is nothing to edit.
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        Student student = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        editStudentDescriptor.setName(student.getName());
        // this forces the name of the edited student and current student to be the same
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
                editStudentDescriptor);
        assertCommandFailure(editStudentCommand, model, EditStudentCommand.MESSAGE_NOTHING_TO_EDIT);
    }

    @Test
    public void execute_studentAlreadyHasAddress_failure() {
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        Student student = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        editStudentDescriptor.setAddress(student.getAddress());
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
                editStudentDescriptor);
        assertCommandFailure(editStudentCommand, model, EditStudentCommand.MESSAGE_NOTHING_TO_EDIT);
    }

    @Test
    public void execute_studentAlreadyHasGender_failure() {
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        Student student = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        editStudentDescriptor.setGender(student.getGender());
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
                editStudentDescriptor);
        assertCommandFailure(editStudentCommand, model, EditStudentCommand.MESSAGE_NOTHING_TO_EDIT);
    }

    @Test
    public void execute_studentAlreadyHasEmergencyContact_failure() {
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        Student student = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        editStudentDescriptor.setEmergencyContact(student.getEmergencyContact());
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
                editStudentDescriptor);
        assertCommandFailure(editStudentCommand, model, EditStudentCommand.MESSAGE_NOTHING_TO_EDIT);
    }

    @Test
    public void execute_editTwoSameFields_failure() {
        // should fail when there are two same fields provided to the edit command
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        Student student = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        editStudentDescriptor.setFormClass(student.getFormClass());
        editStudentDescriptor.setAddress(student.getAddress());
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
                editStudentDescriptor);
        assertCommandFailure(editStudentCommand, model, EditStudentCommand.MESSAGE_NOTHING_TO_EDIT);
    }

    @Test
    public void execute_atLeastOneFieldDifferent_success() {
        // should pass when there is at least one field which is provided in the editedStudent which is different
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        Student student = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        editStudentDescriptor.setFormClass(student.getFormClass());
        editStudentDescriptor.setAddress(new Address(VALID_ADDRESS_BOB));
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
                editStudentDescriptor);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Student editedStudent = new StudentBuilder(student)
                .withFormClass(student.getFormClass().toString())
                .withAddress(VALID_ADDRESS_BOB)
                .build();
        expectedModel.setStudent(student, editedStudent);
        String expectedMessage = String.format(EditStudentCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);
        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(VALID_NAME_BOB).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditStudentCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_SECOND_STUDENT, descriptor);

        assertCommandFailure(editStudentCommand, model, EditStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        // edit student in filtered list into a duplicate in address book
        Student studentInList = (Student) model.getAddressBook()
                .getStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
                new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editStudentCommand, model, EditStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditStudentCommand editCommand = new EditStudentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        EditStudentCommand editStudentCommand = new EditStudentCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editStudentCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditStudentCommand standardCommand = new EditStudentCommand(INDEX_FIRST_STUDENT, DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditStudentCommand commandWithSameValues = new EditStudentCommand(INDEX_FIRST_STUDENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearStudentCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(INDEX_SECOND_STUDENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(INDEX_FIRST_STUDENT, DESC_BOB)));
    }

}
