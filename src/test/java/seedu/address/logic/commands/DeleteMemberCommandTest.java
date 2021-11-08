package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMemberAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEMBER;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteMemberCommandTest {

    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Member memberToDelete = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(INDEX_FIRST_MEMBER);

        String expectedMessage = String.format(DeleteMemberCommand.MESSAGE_SUCCESS, memberToDelete);

        ModelManager expectedModel = new ModelManager(new Account(), model.getEzFoodie(), new UserPrefs());
        expectedModel.deleteMember(memberToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getUpdatedMemberList().size() + 1);
        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);

        Member memberToDelete = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(INDEX_FIRST_MEMBER);

        String expectedMessage = String.format(DeleteMemberCommand.MESSAGE_SUCCESS, memberToDelete);

        Model expectedModel = new ModelManager(new Account(), model.getEzFoodie(), new UserPrefs());
        expectedModel.deleteMember(memberToDelete);
        showNoMember(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMemberAtIndex(model, INDEX_FIRST_MEMBER);

        Index outOfBoundIndex = INDEX_SECOND_MEMBER;
        // ensures that outOfBoundIndex is still in bounds of ezFoodie list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEzFoodie().getMemberList().size());

        DeleteMemberCommand deleteCommand = new DeleteMemberCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMemberCommand deleteFirstCommand = new DeleteMemberCommand(INDEX_FIRST_MEMBER);
        DeleteMemberCommand deleteSecondCommand = new DeleteMemberCommand(INDEX_SECOND_MEMBER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteMemberCommand(INDEX_FIRST_MEMBER);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different member -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoMember(Model model) {
        model.updateFilteredMemberList(p -> false);

        assertTrue(model.getUpdatedMemberList().isEmpty());
    }
}
