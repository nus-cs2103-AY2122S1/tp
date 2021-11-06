package seedu.address.logic.commands.friends;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFriends.BOB;
import static seedu.address.testutil.TypicalFriends.CARL;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.APEX_LEGENDS;
import static seedu.address.testutil.TypicalGames.GENSHIN_IMPACT;
import static seedu.address.testutil.TypicalGames.MINECRAFT;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.UserName;
import seedu.address.testutil.FriendBuilder;

public class UnlinkFriendCommandTest {

    private Model model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());

    @Test
    public void execute_validFriendIdUnfilteredList_success() {
        Model model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());
        Friend friendToUnlink = model.getFilteredAndSortedFriendsList().get(INDEX_THIRD_ITEM.getZeroBased());
        GameFriendLink gameFriendLink = new GameFriendLink(GENSHIN_IMPACT.getGameId(), friendToUnlink.getFriendId(),
                new UserName("GoldNova"));
        ModelManager expectedModel = new ModelManager(model.getFriendsList(), model.getGamesList(),
                model.getUserPrefs());
        UnlinkFriendCommand unlinkFriendCommand = new UnlinkFriendCommand(friendToUnlink.getFriendId(),
                GENSHIN_IMPACT.getGameId());

        model.linkFriend(friendToUnlink, gameFriendLink);
        assertCommandSuccess(unlinkFriendCommand, model, unlinkFriendCommand.generateSuccessMessage(friendToUnlink),
                expectedModel);
    }

    @Test
    public void execute_nonExistentFriendIdUnfilteredList_throwsCommandException() {
        UnlinkFriendCommand unlinkFriendCommand = new UnlinkFriendCommand(BOB.getFriendId(),
                GENSHIN_IMPACT.getGameId());
        assertCommandFailure(unlinkFriendCommand, model, Messages.MESSAGE_NONEXISTENT_FRIEND_ID);
    }

    @Test
    public void execute_nonExistentGameIdUnfilteredList_throwsCommandException() {
        UnlinkFriendCommand unlinkFriendCommand = new UnlinkFriendCommand(CARL.getFriendId(), APEX_LEGENDS.getGameId());
        assertCommandFailure(unlinkFriendCommand, model, Messages.MESSAGE_NONEXISTENT_GAME_ID);
    }

    @Test
    public void execute_gameNotAssociatedWithFriend_throwsCommandException() {
        UnlinkFriendCommand unlinkFriendCommand = new UnlinkFriendCommand(CARL.getFriendId(),
                GENSHIN_IMPACT.getGameId());

        assertCommandFailure(unlinkFriendCommand, model, Messages.MESSAGE_GAME_NOT_ASSOCIATED);
    }

    @Test
    public void equals() {
        Friend alice = new FriendBuilder().withFriendName("Alice").build();
        Friend bob = new FriendBuilder().withFriendName("Bob").build();
        UnlinkFriendCommand unlinkAliceCommand = new UnlinkFriendCommand(alice.getFriendId(),
                GENSHIN_IMPACT.getGameId());
        UnlinkFriendCommand unlinkBobCommand = new UnlinkFriendCommand(bob.getFriendId(), MINECRAFT.getGameId());

        // same object -> equals
        assertEquals(unlinkAliceCommand, unlinkAliceCommand);

        // same values -> equals
        UnlinkFriendCommand unlinkAliceCommandCopy = new UnlinkFriendCommand(alice.getFriendId(),
                GENSHIN_IMPACT.getGameId());
        assertEquals(unlinkAliceCommandCopy, unlinkAliceCommand);

        // different types -> returns false
        assertNotEquals(1, unlinkAliceCommand);

        // null -> notEquals
        assertNotEquals(null, unlinkAliceCommand);

        assertFalse(unlinkAliceCommand.equals(null));

        // different friend -> notEquals
        assertNotEquals(unlinkBobCommand, unlinkAliceCommand);
    }
}
