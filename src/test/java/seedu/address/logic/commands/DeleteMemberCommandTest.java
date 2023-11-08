package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMemberAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalSportsPa.getTypicalSportsPa;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMemberCommand}.
 */
public class DeleteMemberCommandTest {

    private Model model = new ModelManager(getTypicalSportsPa(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Member memberToDelete = model.getFilteredMemberList().get(INDEX_FIRST.getZeroBased());
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteMemberCommand.MESSAGE_DELETE_MEMBER_SUCCESS, memberToDelete);

        ModelManager expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        expectedModel.deleteMember(memberToDelete);

        assertCommandSuccess(deleteMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMemberList().size() + 1);
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(outOfBoundIndex);

        assertCommandFailure(deleteMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMemberAtIndex(model, INDEX_FIRST);

        Member memberToDelete = model.getFilteredMemberList().get(INDEX_FIRST.getZeroBased());
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(INDEX_FIRST);

        String expectedMessage = String.format(deleteMemberCommand.MESSAGE_DELETE_MEMBER_SUCCESS, memberToDelete);

        Model expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        expectedModel.deleteMember(memberToDelete);
        showNoMember(expectedModel);

        assertCommandSuccess(deleteMemberCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMemberAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSportsPa().getMemberList().size());

        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(outOfBoundIndex);

        assertCommandFailure(deleteMemberCommand, model, Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMemberCommand deleteFirstCommand = new DeleteMemberCommand(INDEX_FIRST);
        DeleteMemberCommand deleteSecondCommand = new DeleteMemberCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteMemberCommand deleteFirstCommandCopy = new DeleteMemberCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different member -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void execute_emptyMemberList_failure() {
        showNoMember(model);
        DeleteMemberCommand command = new DeleteMemberCommand(INDEX_FIRST);
        assertCommandFailure(command, model, String.format(Messages.MESSAGE_EMPTY_LIST, Messages.MESSAGE_MEMBER));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoMember(Model model) {
        model.updateFilteredMemberList(p -> false);

        assertTrue(model.getFilteredMemberList().isEmpty());
    }
}
