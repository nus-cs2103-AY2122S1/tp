package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendsList;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.testutil.FriendBuilder;
import seedu.address.testutil.ModelStub;

public class AddFriendCommandTest {

    @Test
    public void constructor_nullFriend_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddFriendCommand(null));
    }

    @Test
    public void execute_friendAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingFriendAdded modelStub = new ModelStubAcceptingFriendAdded();
        Friend validFriendBobId = new FriendBuilder().withFriendId(VALID_FRIEND_ID_BOB).build();

        CommandResult commandResult = new AddFriendCommand(validFriendBobId).execute(modelStub);
        assertEquals(String.format(AddFriendCommand.MESSAGE_SUCCESS_ADD_FRIEND, validFriendBobId.getFriendId()),
                commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validFriendBobId), modelStub.friendsAdded);

        Friend validFriendAmyId = new FriendBuilder().withFriendId(VALID_FRIEND_ID_AMY).build();
        CommandResult commandResultAddAnother = new AddFriendCommand(validFriendAmyId).execute(modelStub);
        assertEquals(String.format(AddFriendCommand.MESSAGE_SUCCESS_ADD_FRIEND, validFriendAmyId.getFriendId()),
                commandResultAddAnother.getFeedbackToUser());
        assertEquals(Arrays.asList(validFriendBobId, validFriendAmyId), modelStub.friendsAdded);
    }

    @Test
    public void execute_duplicateFriend_throwsCommandException() {
        Friend validFriend = new FriendBuilder().build();
        AddFriendCommand addFriendCommand = new AddFriendCommand(validFriend);
        ModelStub modelStub = new ModelStubWithFriend(validFriend);

        assertThrows(CommandException.class, AddFriendCommand.MESSAGE_DUPLICATE_FRIEND_ID, () ->
                addFriendCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Friend alice = new FriendBuilder().withFriendName("Alice").build();
        Friend bob = new FriendBuilder().withFriendName("Bob").build();
        AddFriendCommand addAliceCommand = new AddFriendCommand(alice);
        AddFriendCommand addBobCommand = new AddFriendCommand(bob);

        // same object -> equals
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> equals
        AddFriendCommand addAliceCommandCopy = new AddFriendCommand(alice);
        assertEquals(addAliceCommandCopy, addAliceCommand);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> notEquals
        assertNotEquals(null, addAliceCommand);

        // different friend -> notEquals
        assertNotEquals(addBobCommand, addAliceCommand);
    }

    /**
     * A Model stub that contains a single friend.
     */
    private class ModelStubWithFriend extends ModelStub {
        private final Friend friend;

        ModelStubWithFriend(Friend friend) {
            requireNonNull(friend);
            this.friend = friend;
        }

        @Override
        public boolean hasFriend(Friend friend) {
            requireNonNull(friend);
            return this.friend.equals(friend);
        }

        @Override
        public boolean hasFriendWithId(FriendId idToFind) {
            return this.friend.getFriendId().equals(friend.getFriendId());
        }

    }

    /**
     * A Model stub that always accept the friend being added.
     */
    private class ModelStubAcceptingFriendAdded extends ModelStub {
        final ArrayList<Friend> friendsAdded = new ArrayList<>();

        @Override
        public boolean hasFriend(Friend friend) {
            requireNonNull(friend);
            return friendsAdded.stream().anyMatch(friend::equals);
        }

        @Override
        public boolean hasFriendWithId(FriendId idToFind) {
            return friendsAdded.stream().anyMatch(friend -> friend.getFriendId().equals(idToFind));
        }

        @Override
        public void addFriend(Friend friend) {
            requireNonNull(friend);
            friendsAdded.add(friend);
        }

        @Override
        public ReadOnlyFriendsList getFriendsList() {
            return new FriendsList();
        }
    }

}
