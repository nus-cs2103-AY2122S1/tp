package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFriends.*;
import static seedu.address.testutil.TypicalGames.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.gamefriendlink.GameFriendLink;
import seedu.address.model.friend.gamefriendlink.UserName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteFriendCommand}.
 */
public class LinkCommandTest {

    private final Model model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());

    @Test
    public void execute_validFriendIdUnfilteredList_success() {
        Friend friendToLink = model.getFilteredFriendsList().get(INDEX_FIRST_PERSON.getZeroBased());
        UserName userName = new UserName("GoldNova");
        LinkCommand linkCommand = new LinkCommand(friendToLink.getFriendId(), GENSHIN_IMPACT.getGameId(), userName);
        ModelManager expectedModel = new ModelManager(model.getFriendsList(), model.getGamesList(),
                model.getUserPrefs());
        GameFriendLink gameFriendLink = new GameFriendLink(GENSHIN_IMPACT.getGameId(), friendToLink.getFriendId(),
                userName);

        expectedModel.linkFriend(friendToLink, gameFriendLink);
        assertCommandSuccess(linkCommand, model, linkCommand.generateSuccessMessage(friendToLink), expectedModel);
    }

    @Test
    public void execute_nonExistentFriendIdUnfilteredList_throwsCommandException() {
        UserName userName = new UserName("Smurf");
        LinkCommand linkCommand = new LinkCommand(AMY.getFriendId(), GENSHIN_IMPACT.getGameId(), userName);

        assertCommandFailure(linkCommand, model, Messages.MESSAGE_NONEXISTENT_FRIEND_ID);
    }

    @Test
    public void execute_nonExistentGameIdUnfilteredList_throwsCommandException() {
        UserName userName = new UserName("GoldNova");
        LinkCommand linkCommand = new LinkCommand(ALICE.getFriendId(), CSGO.getGameId(), userName);

        assertCommandFailure(linkCommand, model, Messages.MESSAGE_NONEXISTENT_GAME_ID);
    }

}
