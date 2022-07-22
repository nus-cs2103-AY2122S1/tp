package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_PHY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_SUN;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.testutil.EditClassDescriptorBuilder;
import seedu.address.testutil.TuitionClassBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditClassCommand.
 */
public class EditClassCommandTest {

    private Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()) ,
            new UserPrefs());

    @Test
    public void execute_allFieldsEdited_success() {
        TuitionClass tuitionClass = model.getTuitionClass(INDEX_FIRST);

        TuitionClass edited = new TuitionClassBuilder(tuitionClass).withName(VALID_CLASS_PHY)
                .withClassLimit(10).withTimeslot(VALID_CLASS_SUN).build();
        EditClassCommand.EditClassDescriptor editDescriptor = new EditClassDescriptorBuilder(edited).build();

        EditClassCommand editCommand = new EditClassCommand(INDEX_FIRST, editDescriptor);

        String expectedMessage = String.format(EditClassCommand.MESSAGE_EDIT_CLASS_SUCCESS, edited);

        Model expectedModel = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
                new UserPrefs());
        expectedModel.setTuition(expectedModel.getTuitionClass(INDEX_FIRST), edited);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldsEdited_failure() {
        TuitionClass tuitionClass = model.getTuitionClass(INDEX_SECOND);
        EditClassCommand.EditClassDescriptor editedDescriptor = new EditClassDescriptorBuilder(tuitionClass).build();

        EditClassCommand editCommand = new EditClassCommand(INDEX_SECOND, editedDescriptor);

        String expectedMessage = String.format(EditClassCommand.MESSAGE_NO_CLASS_CHANGES);

        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_someFieldsEdited_success() {
        TuitionClass tuitionClass = model.getTuitionClass(INDEX_FIRST);
        TuitionClass edited = new TuitionClassBuilder(tuitionClass).withName(VALID_CLASS_PHY).build();

        EditClassCommand.EditClassDescriptor editDescriptor = new EditClassDescriptorBuilder(edited).build();

        EditClassCommand editCommand = new EditClassCommand(INDEX_FIRST, editDescriptor);

        String expectedMessage = String.format(EditClassCommand.MESSAGE_EDIT_CLASS_SUCCESS, edited);

        assertCommandSuccess(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_timetableConflict_failure() {
        TuitionClass tuitionClass = model.getTuitionClass(INDEX_FIRST);
        TuitionClass tuitionClass1 = model.getTuitionClass(INDEX_SECOND);
        TuitionClass edited = new TuitionClassBuilder(tuitionClass).withTimeslot(tuitionClass1.getTimeslot()).build();

        EditClassCommand.EditClassDescriptor editDescriptor = new EditClassDescriptorBuilder(edited).build();

        EditClassCommand editCommand = new EditClassCommand(INDEX_FIRST, editDescriptor);

        assertCommandFailure(editCommand, model, EditClassCommand.MESSAGE_INVALID_CLASS_SLOT);
    }

    @Test
    public void execute_invalidStudentIndex_failure() {
        TuitionClass tuitionClass = model.getTuitionClass(INDEX_FIRST);
        TuitionClass edited = new TuitionClassBuilder(tuitionClass).withName(VALID_CLASS_PHY).build();

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTuitionList().size() + 1);
        EditClassCommand.EditClassDescriptor editDescriptor = new EditClassDescriptorBuilder(edited).build();

        EditClassCommand editCommand = new EditClassCommand(outOfBoundIndex, editDescriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
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
