package seedu.address.logic.commands.friends;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.testutil.FriendBuilder;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code seedu.address.logic.commands.friends.AddFriendCommand}.
 */
public class AddFriendCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendsList(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Friend validFriend = new FriendBuilder().build();

        Model expectedModel = new ModelManager(model.getFriendsList(), new UserPrefs());
        expectedModel.addFriend(validFriend);

        assertCommandSuccess(new AddFriendCommand(validFriend), model,
                String.format(AddFriendCommand.MESSAGE_SUCCESS, validFriend), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Friend friendInList = model.getFriendsList().getFriendsList().get(0);
        assertCommandFailure(new AddFriendCommand(friendInList), model, AddFriendCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
