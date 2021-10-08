package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFriends.AMY;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteFriendCommand}.
 */
public class LinkCommandTest {

    private final Model model = new ModelManager(getTypicalFriendsList(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        HashMap<String, String> games = new HashMap<>();
        games.put("CSGO", "GoldNova");
        games.put("Valorant", "PlatSmurf");
        Friend friendToLink = model.getFilteredFriendsList().get(INDEX_FIRST_PERSON.getZeroBased());
        LinkCommand linkCommand = new LinkCommand(friendToLink.getFriendId(), games);

        ModelManager expectedModel = new ModelManager(model.getFriendsList(), new UserPrefs());
        expectedModel.linkFriend(friendToLink, games);
        assertCommandSuccess(linkCommand, model, linkCommand.generateSuccessMessage(friendToLink), expectedModel);
    }

    @Test
    public void execute_nonExistentIdUnfilteredList_throwsCommandException() {
        HashMap<String, String> games = new HashMap<>();
        games.put("CSGO", "GoldNova");
        games.put("Valorant", "PlatSmurf");
        LinkCommand linkCommand = new LinkCommand(AMY.getFriendId(), games);

        assertCommandFailure(linkCommand, model, Messages.MESSAGE_NONEXISTENT_FRIEND_ID);
    }

}
