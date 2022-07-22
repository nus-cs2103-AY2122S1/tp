package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getAddressBookWithTypicalStudents(), new UserPrefs());

    @Test
    public void execute_noFieldsEdited_failure() {
        Student student = model.getStudent(INDEX_SECOND);
        EditCommand.EditStudentDescriptor editedDescriptor = new EditStudentDescriptorBuilder(student).build();

        EditCommand editCommand = new EditCommand(INDEX_SECOND, editedDescriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_NO_STUDENT_CHANGES);

        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_someFieldsEdited_success() {
        Student student = model.getStudent(INDEX_FIRST);
        Student editedStudent = new StudentBuilder(student).withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY).build();

        EditCommand.EditStudentDescriptor editedDescriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST, editedDescriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(student, editedStudent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editedDuplicateName_failure() {
        Student firstStudent = model.getStudent(INDEX_FIRST);
        Student secondStudent = model.getStudent(INDEX_SECOND);

        Student editedStudent = new StudentBuilder(firstStudent)
                .withName(secondStudent.getNameString()).build();

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndex_failure() {
        Student student = model.getStudent(INDEX_SECOND);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student)
                .withPhone(VALID_PHONE_BOB).build();

        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditCommand.EditStudentDescriptor copyDescriptor = new EditCommand.EditStudentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST, DESC_BOB)));
    }

}
