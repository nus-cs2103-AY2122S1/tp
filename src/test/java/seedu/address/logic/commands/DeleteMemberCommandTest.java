package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showGroupAtIndex;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GROUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_GROUP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class DeleteMemberCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());

    // Pass
    @Test
    public void execute_invalidGroupIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGroupList().size() + 1);
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(outOfBoundIndex,
                INDEX_FIRST_STUDENT);

        assertCommandFailure(deleteMemberCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void execute_invalidMemberIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGroupList()
                .get(INDEX_THIRD_GROUP.getZeroBased()).getMembersList().size() + 1);
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(INDEX_THIRD_GROUP,
                outOfBoundIndex);

        assertCommandFailure(deleteMemberCommand, model, Messages.MESSAGE_INVALID_GROUP_MEMBER_REMOVAL_INDEX);
    }

    @Test
    public void execute_invalidGroupAndMemberIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGroupList().size() + 1);
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(outOfBoundIndex,
                outOfBoundIndex);

        assertCommandFailure(deleteMemberCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void execute_invalidGroupIndexFilteredList_throwsCommandException() {
        showGroupAtIndex(model, INDEX_FIRST_GROUP);

        Index outOfBoundIndex = INDEX_SECOND_GROUP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getGroupList().size());

        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(outOfBoundIndex,
                outOfBoundIndex);

        assertCommandFailure(deleteMemberCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }



    // Pass
    @Test
    public void equals() {
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(INDEX_FIRST_GROUP,
                INDEX_FIRST_STUDENT);
        DeleteMemberCommand addSecondCommand = new DeleteMemberCommand(INDEX_SECOND_GROUP,
                INDEX_SECOND_STUDENT);

        // same object -> returns true
        assertTrue(deleteMemberCommand.equals(deleteMemberCommand));

        // same values -> returns true
        DeleteMemberCommand addFirstCommandCopy = new DeleteMemberCommand(INDEX_FIRST_GROUP,
                INDEX_FIRST_STUDENT);
        assertTrue(deleteMemberCommand.equals(addFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteMemberCommand.equals(1));

        // null -> returns false
        assertFalse(deleteMemberCommand.equals(null));

        // different group -> returns false
        assertFalse(deleteMemberCommand.equals(addSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoGroup(Model model) {
        model.updateFilteredGroupList(p -> false);

        assertTrue(model.getFilteredGroupList().isEmpty());
    }
}
