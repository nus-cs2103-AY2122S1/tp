package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showGroupAtIndex;
import static seedu.address.testutil.TypicalGroups.GROUPNAME1;
import static seedu.address.testutil.TypicalGroups.GROUPNAME2;
import static seedu.address.testutil.TypicalGroups.VALID_UNINSTATITATED_GROUP;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GROUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class AddStudentGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    // Pass
    /*@Test
    public void execute_validIndexAndGroupUnfilteredList_success() {
        Student studentToAdd = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Group groupToBeAdded = model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());
        AddStudentGroupCommand addStudentGroupCommand = new AddStudentGroupCommand(INDEX_FIRST_STUDENT,
                groupToBeAdded.getName());

        String expectedMessage = String.format(AddStudentGroupCommand.MESSAGE_ADD_STUDENT_SUCCESS,
                studentToAdd, groupToBeAdded);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
        expectedModel.addStudentGroup(studentToAdd, groupToBeAdded);

        assertCommandSuccess(addStudentGroupCommand, model, expectedMessage, expectedModel);
    }*/

    // Pass
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Group groupToBeAdded = model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());
        AddStudentGroupCommand addStudentGroupCommand = new AddStudentGroupCommand(outOfBoundIndex,
                groupToBeAdded.getName());

        assertCommandFailure(addStudentGroupCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void execute_invalidGroupUnfilteredList_throwsCommandException() {
        AddStudentGroupCommand addStudentGroupCommand = new AddStudentGroupCommand(INDEX_FIRST_STUDENT,
                VALID_UNINSTATITATED_GROUP);

        assertCommandFailure(addStudentGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_NAME);
    }

    @Test
    public void execute_invalidIndexAndGroupUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AddStudentGroupCommand addStudentGroupCommand = new AddStudentGroupCommand(outOfBoundIndex,
                VALID_UNINSTATITATED_GROUP);

        assertCommandFailure(addStudentGroupCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    // Pass
    /*@Test
    public void execute_validIndexFilteredList_success() {
        showGroupAtIndex(model, INDEX_FIRST_GROUP);

        Student studentToAdd = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Group groupToBeAdded = model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());
        AddStudentGroupCommand addStudentGroupCommand = new AddStudentGroupCommand(INDEX_FIRST_GROUP,
                groupToBeAdded.getName());

        String expectedMessage = String.format(AddStudentGroupCommand.MESSAGE_ADD_STUDENT_SUCCESS, studentToAdd,
                groupToBeAdded);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addStudentGroup(studentToAdd, groupToBeAdded);
        showNoGroup(expectedModel);

        assertCommandSuccess(addStudentGroupCommand, model, expectedMessage, expectedModel);
    }*/

    // Pass
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGroupAtIndex(model, INDEX_FIRST_GROUP);

        Index outOfBoundIndex = INDEX_SECOND_GROUP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getGroupList().size());

        AddStudentGroupCommand addStudentGroupCommand = new AddStudentGroupCommand(outOfBoundIndex,
                GROUPNAME1);

        assertCommandFailure(addStudentGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_MEMBER_INDEX);
    }

    // Pass
    @Test
    public void equals() {
        AddStudentGroupCommand addStudentGroupCommand = new AddStudentGroupCommand(INDEX_FIRST_STUDENT,
                GROUPNAME1);
        AddStudentGroupCommand addSecondCommand = new AddStudentGroupCommand(INDEX_SECOND_STUDENT,
                GROUPNAME2);

        // same object -> returns true
        assertTrue(addStudentGroupCommand.equals(addStudentGroupCommand));

        // same values -> returns true
        AddStudentGroupCommand addFirstCommandCopy = new AddStudentGroupCommand(INDEX_FIRST_STUDENT,
              GROUPNAME1);
        assertTrue(addStudentGroupCommand.equals(addFirstCommandCopy));

        // different types -> returns false
        assertFalse(addStudentGroupCommand.equals(1));

        // null -> returns false
        assertFalse(addStudentGroupCommand.equals(null));

        // different group -> returns false
        assertFalse(addStudentGroupCommand.equals(addSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoGroup(Model model) {
        model.updateFilteredGroupList(p -> false);

        assertTrue(model.getFilteredGroupList().isEmpty());
    }
}
