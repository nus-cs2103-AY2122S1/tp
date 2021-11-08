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
public class AddMemberCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    // Pass
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        Group groupToBeAdded = model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());
        AddMemberCommand addMemberCommand = new AddMemberCommand(outOfBoundIndex,
                groupToBeAdded.getName());

        assertCommandFailure(addMemberCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void execute_invalidGroupUnfilteredList_throwsCommandException() {
        AddMemberCommand addMemberCommand = new AddMemberCommand(INDEX_FIRST_STUDENT,
                VALID_UNINSTATITATED_GROUP);

        assertCommandFailure(addMemberCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_NAME);
    }

    @Test
    public void execute_invalidIndexAndGroupUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AddMemberCommand addMemberCommand = new AddMemberCommand(outOfBoundIndex,
                VALID_UNINSTATITATED_GROUP);

        assertCommandFailure(addMemberCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGroupAtIndex(model, INDEX_FIRST_GROUP);

        Index outOfBoundIndex = INDEX_SECOND_GROUP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getGroupList().size());

        AddMemberCommand addMemberCommand = new AddMemberCommand(outOfBoundIndex,
                GROUPNAME1);

        assertCommandFailure(addMemberCommand, model, Messages.MESSAGE_INVALID_GROUP_MEMBER_INDEX);
    }

    // Pass
    @Test
    public void equals() {
        AddMemberCommand addMemberCommand = new AddMemberCommand(INDEX_FIRST_STUDENT,
                GROUPNAME1);
        AddMemberCommand addSecondCommand = new AddMemberCommand(INDEX_SECOND_STUDENT,
                GROUPNAME2);

        // same object -> returns true
        assertTrue(addMemberCommand.equals(addMemberCommand));

        // same values -> returns true
        AddMemberCommand addFirstCommandCopy = new AddMemberCommand(INDEX_FIRST_STUDENT,
              GROUPNAME1);
        assertTrue(addMemberCommand.equals(addFirstCommandCopy));

        // different types -> returns false
        assertFalse(addMemberCommand.equals(1));

        // null -> returns false
        assertFalse(addMemberCommand.equals(null));

        // different group -> returns false
        assertFalse(addMemberCommand.equals(addSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoGroup(Model model) {
        model.updateFilteredGroupList(p -> false);

        assertTrue(model.getFilteredGroupList().isEmpty());
    }
}
