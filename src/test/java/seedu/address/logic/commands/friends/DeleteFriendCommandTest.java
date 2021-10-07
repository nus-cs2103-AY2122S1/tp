package seedu.address.logic.commands.friends;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalFriends.AMY;
import static seedu.address.testutil.TypicalFriends.BOB;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.testutil.FriendBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteFriendCommand}.
 */
public class DeleteFriendCommandTest {

    private final Model model = new ModelManager(getTypicalFriendsList(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        Friend friendToDelete = model.getFilteredFriendsList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteFriendCommand deleteCommand = new DeleteFriendCommand(friendToDelete.getFriendId());

        String expectedMessage = String.format(DeleteFriendCommand.MESSAGE_DELETE_PERSON_SUCCESS, friendToDelete);

        ModelManager expectedModel = new ModelManager(model.getFriendsList(), new UserPrefs());
        expectedModel.deleteFriend(friendToDelete);
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistentIdUnfilteredList_throwsCommandException() {
        DeleteFriendCommand deleteFriendCommand = new DeleteFriendCommand(AMY.getFriendId());

        assertCommandFailure(deleteFriendCommand, model, Messages.MESSAGE_NONEXISTENT_FRIEND_ID);
    }

    @Test
    public void execute_validIdFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Friend friendToDelete = model.getFilteredFriendsList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteFriendCommand deleteCommand = new DeleteFriendCommand(friendToDelete.getFriendId());

        String expectedMessage = String.format(DeleteFriendCommand.MESSAGE_DELETE_PERSON_SUCCESS, friendToDelete);

        Model expectedModel = new ModelManager(model.getFriendsList(), new UserPrefs());
        expectedModel.deleteFriend(friendToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFriendsList().getFriendsList().size());

        Friend friendToDelete = model.getFriendsList().getFriendsList().get(outOfBoundIndex.getZeroBased());
        DeleteFriendCommand deleteCommand = new DeleteFriendCommand(friendToDelete.getFriendId());

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_NONEXISTENT_FRIEND_ID);
    }

    @Test
    public void equals() {
        Friend friendAmy = new FriendBuilder(AMY).build();
        Friend friendBob = new FriendBuilder(BOB).build();
        DeleteFriendCommand deleteFirstCommand = new DeleteFriendCommand(friendAmy.getFriendId());
        DeleteFriendCommand deleteSecondCommand = new DeleteFriendCommand(friendBob.getFriendId());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteFriendCommand deleteFirstCommandCopy = new DeleteFriendCommand(friendAmy.getFriendId());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals("123"));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredFriendsList(p -> false);

        assertTrue(model.getFilteredFriendsList().isEmpty());
    }
}
