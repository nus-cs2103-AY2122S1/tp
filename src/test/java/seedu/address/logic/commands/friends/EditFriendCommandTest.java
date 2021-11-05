package seedu.address.logic.commands.friends;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.exceptions.FriendNotFoundException;
import seedu.address.testutil.EditFriendDescriptorBuilder;
import seedu.address.testutil.FriendBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditFriendCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());
        expectedModel = new ModelManager(model.getFriendsList(), getTypicalGamesList(), new UserPrefs());
    }

    @Test
    public void execute_allFieldsSpecified_success() {
        Friend friendToEdit = model.getFriendsList().getFriendsList().get(0);

        Friend editedFriend = new FriendBuilder(friendToEdit).withFriendName("1234 5678 9101112").build();

        EditFriendCommand.EditFriendDescriptor editFriendDescriptor =
            new EditFriendDescriptorBuilder(editedFriend).build();

        EditFriendCommand editFriendCommand = new EditFriendCommand(friendToEdit.getFriendId(), editFriendDescriptor);

        String expectedMessage = String.format(EditFriendCommand.MESSAGE_EDIT_FRIEND_SUCCESS,
            editedFriend.getFriendId(), editedFriend.getFriendName());

        expectedModel.setFriend(expectedModel.getFriend(friendToEdit.getFriendId()), editedFriend);

        assertCommandSuccess(editFriendCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Edit FriendId which does not exist in the friends list.
     */
    @Test
    public void execute_friendIdNotInFriendsList_failure() {
        FriendId notInListFriendId = new FriendId("NOTINLIST");
        assertThrows(FriendNotFoundException.class, () -> model.getFriend(notInListFriendId));
        EditFriendCommand editFriendCommand = new EditFriendCommand(notInListFriendId,
            new EditFriendDescriptorBuilder().withFriendName(VALID_NAME_BOB).build());

        assertCommandFailure(editFriendCommand, model,
            String.format(Messages.MESSAGE_FRIEND_ID_NOT_FOUND, notInListFriendId));
    }

    @Test
    public void equals() {
        Friend friendToEdit = model.getFriendsList().getFriendsList().get(0);
        EditFriendCommand standardCommand = new EditFriendCommand(friendToEdit.getFriendId(), DESC_AMY);

        // same values -> equal
        EditFriendCommand.EditFriendDescriptor copyDescriptor = new EditFriendCommand.EditFriendDescriptor(DESC_AMY);
        EditFriendCommand commandWithSameValues = new EditFriendCommand(friendToEdit.getFriendId(), copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // same object -> equal
        assertEquals(standardCommand, standardCommand);

        // null -> not equal
        assertNotEquals(standardCommand, null);

        // different types -> not equal
        assertNotEquals(new ClearCommand(), standardCommand);

        // different index -> not equal
        assertNotEquals(new EditFriendCommand(new FriendId(friendToEdit.getFriendId().value + "diff"), DESC_AMY),
            standardCommand);

        // different descriptor -> not equal
        assertNotEquals(new EditFriendCommand(friendToEdit.getFriendId(), DESC_BOB), standardCommand);
    }

}
