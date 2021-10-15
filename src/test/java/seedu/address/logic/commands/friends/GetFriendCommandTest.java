package seedu.address.logic.commands.friends;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFriends.AMY;
import static seedu.address.testutil.TypicalFriends.BOB;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.util.BitSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.testutil.FriendBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code GetFriendCommand}.
 */
public class GetFriendCommandTest {

    private final Model model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        // command can get friend by friend id
        Friend friendToGet = model.getFilteredFriendsList().get(INDEX_FIRST_ITEM.getZeroBased());
        GetFriendCommand getCommand = new GetFriendCommand(friendToGet.getFriendId());

        String expectedMessage = String.format(GetFriendCommand.MESSAGE_FRIEND_FULL_INFORMATION,
                friendToGet.getFriendId());
        ModelManager expectedModel = new ModelManager(model.getFriendsList(), model.getGamesList(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandType.FRIEND_GET, friendToGet);

        assertCommandSuccess(getCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonExistentIdUnfilteredList_throwsCommandException() {
        // command fails if friend id does not exist in unfiltered list
        GetFriendCommand getFriendCommand = new GetFriendCommand(AMY.getFriendId());
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_FRIEND_ID, AMY.getFriendId());

        assertCommandFailure(getFriendCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIdFilteredList_success() {
        // can get friend by friendId even if not in the currently filtered list
        Friend friendToGet = model.getFilteredFriendsList().get(INDEX_FIRST_ITEM.getZeroBased());
        GetFriendCommand getCommand = new GetFriendCommand(friendToGet.getFriendId());
        showNoFriend(model);

        String expectedMessage = String.format(GetFriendCommand.MESSAGE_FRIEND_FULL_INFORMATION,
                friendToGet.getFriendId());
        Model expectedModel = new ModelManager(model.getFriendsList(), model.getGamesList(), new UserPrefs());
        // show no one
        showNoFriend(expectedModel);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, CommandType.FRIEND_GET, friendToGet);

        assertCommandSuccess(getCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        Friend friendAmy = new FriendBuilder(AMY).build();
        Friend friendBob = new FriendBuilder(BOB).build();
        GetFriendCommand getFirstCommand = new GetFriendCommand(friendAmy.getFriendId());
        GetFriendCommand getSecondCommand = new GetFriendCommand(friendBob.getFriendId());

        // same object -> returns true
        assertTrue(getFirstCommand.equals(getFirstCommand));

        // same values -> returns true
        GetFriendCommand getFirstCommandCopy = new GetFriendCommand(friendAmy.getFriendId());
        assertTrue(getFirstCommand.equals(getFirstCommandCopy));

        // different types -> returns false
        assertFalse(getFirstCommand.equals(new BitSet()));

        // null -> returns false
        assertFalse(getFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(getFirstCommand.equals(getSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered friend list to show no one.
     */
    private void showNoFriend(Model model) {
        model.updateFilteredFriendsList(p -> false);

        assertTrue(model.getFilteredFriendsList().isEmpty());
    }
}
