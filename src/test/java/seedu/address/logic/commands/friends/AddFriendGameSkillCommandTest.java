package seedu.address.logic.commands.friends;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USER_NAME_OMEGA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGames.APEX_LEGENDS;
import static seedu.address.testutil.TypicalGames.CSGO;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.SkillValue;
import seedu.address.model.gamefriendlink.UserName;
import seedu.address.testutil.FriendBuilder;
import seedu.address.testutil.ModelStub;

public class AddFriendGameSkillCommandTest {

    @Test
    public void execute_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddFriendGameSkillCommand(null, CSGO.getGameId(),
                new SkillValue(3)));
        assertThrows(NullPointerException.class, () -> new AddFriendGameSkillCommand(new FriendId("Alice"),
                null, new SkillValue(3)));
        assertThrows(NullPointerException.class, () -> new AddFriendGameSkillCommand(new FriendId("Alice"),
                CSGO.getGameId(), null));
    }

    @Test
    public void execute_validGameFriendLinkAndSkillValue_success() throws CommandException {
        ModelStubAcceptingFriendGameSkillUpdate modelStub = new ModelStubAcceptingFriendGameSkillUpdate();

        GameFriendLink gameFriendLink = new GameFriendLink(CSGO.getGameId(), new FriendId(VALID_FRIEND_ID_BOB),
                new UserName(VALID_USER_NAME_OMEGA));
        Friend validFriendBob = new FriendBuilder().withFriendId(VALID_FRIEND_ID_BOB)
                .withGameFriendLinks(gameFriendLink).build();

        modelStub.addFriend(validFriendBob);
        CommandResult commandResult = new AddFriendGameSkillCommand(validFriendBob.getFriendId(),
                gameFriendLink.getGameId(), new SkillValue(3)).execute(modelStub);

        assertEquals(String.format(AddFriendGameSkillCommand.MESSAGE_SUCCESS_ADD_FRIEND_GAME_SKILL,
                validFriendBob.getFriendId(), gameFriendLink.getGameId(), new SkillValue(3)),
                commandResult.getFeedbackToUser());

        ModelStubAcceptingFriendGameSkillUpdate expectedModelStub = new ModelStubAcceptingFriendGameSkillUpdate();
        expectedModelStub.addFriend(new FriendBuilder().withFriendId(VALID_FRIEND_ID_BOB).withGameFriendLinks(
                new GameFriendLink(CSGO.getGameId(), new FriendId(VALID_FRIEND_ID_BOB),
                        new UserName(VALID_USER_NAME_OMEGA), new SkillValue(3))).build());

        assertEquals(modelStub, expectedModelStub);

        CommandResult commandResultTwo = new AddFriendGameSkillCommand(validFriendBob.getFriendId(),
                gameFriendLink.getGameId(), new SkillValue(5)).execute(modelStub);

        assertEquals(String.format(AddFriendGameSkillCommand.MESSAGE_SUCCESS_ADD_FRIEND_GAME_SKILL,
                validFriendBob.getFriendId(), gameFriendLink.getGameId(), new SkillValue(5)),
                commandResultTwo.getFeedbackToUser());

        ModelStubAcceptingFriendGameSkillUpdate expectedModelStubTwo = new ModelStubAcceptingFriendGameSkillUpdate();
        expectedModelStubTwo.addFriend(new FriendBuilder().withFriendId(VALID_FRIEND_ID_BOB).withGameFriendLinks(
                new GameFriendLink(CSGO.getGameId(), new FriendId(VALID_FRIEND_ID_BOB),
                        new UserName(VALID_USER_NAME_OMEGA), new SkillValue(5))).build());

        assertEquals(modelStub, expectedModelStubTwo);
    }

    @Test
    public void execute_noGameFriendLinkFound_throwsCommandException() {
        Friend validFriend = new FriendBuilder().build();
        AddFriendGameSkillCommand commandUnderTest = new AddFriendGameSkillCommand(validFriend.getFriendId(),
                CSGO.getGameId(), new SkillValue(9));

        ModelStub modelStub = new ModelStubAcceptingFriendGameSkillUpdate();
        modelStub.addFriend(validFriend);

        assertThrows(CommandException.class, AddFriendGameSkillCommand.MESSAGE_NO_GAME_LINK_FOUND, () ->
                commandUnderTest.execute(modelStub));
    }

    @Test
    public void equals() {
        Friend alice = new FriendBuilder().withFriendId("Alice").withFriendName("Alice").build();
        Friend bob = new FriendBuilder().withFriendId("Bob").withFriendName("Bob").build();
        AddFriendGameSkillCommand addFriendGameSkillAliceCsgoThreeCommand =
                new AddFriendGameSkillCommand(alice.getFriendId(), CSGO.getGameId(), new SkillValue(3));
        AddFriendGameSkillCommand addFriendGameSkillBobCsgoThreeCommand =
                new AddFriendGameSkillCommand(bob.getFriendId(), CSGO.getGameId(), new SkillValue(3));
        AddFriendGameSkillCommand addFriendGameSkillAliceApexLegendsThreeCommand =
                new AddFriendGameSkillCommand(alice.getFriendId(), APEX_LEGENDS.getGameId(), new SkillValue(3));
        AddFriendGameSkillCommand addFriendGameSkillAliceCsgoFiveCommand =
                new AddFriendGameSkillCommand(alice.getFriendId(), CSGO.getGameId(), new SkillValue(5));

        // same object -> equals
        assertEquals(addFriendGameSkillAliceCsgoThreeCommand, addFriendGameSkillAliceCsgoThreeCommand);

        // same values -> equals
        AddFriendGameSkillCommand addFriendGameSkillAliceCsgoThreeCommandCopy =
                new AddFriendGameSkillCommand(alice.getFriendId(), CSGO.getGameId(), new SkillValue(3));
        assertEquals(addFriendGameSkillAliceCsgoThreeCommandCopy, addFriendGameSkillAliceCsgoThreeCommand);

        // different types -> notEquals
        assertNotEquals(1, addFriendGameSkillAliceCsgoThreeCommand);

        // null -> notEquals
        assertNotEquals(null, addFriendGameSkillAliceCsgoThreeCommand);

        // different friendId -> notEquals
        assertNotEquals(addFriendGameSkillBobCsgoThreeCommand, addFriendGameSkillAliceCsgoThreeCommand);

        // different gameId -> notEquals
        assertNotEquals(addFriendGameSkillAliceApexLegendsThreeCommand, addFriendGameSkillAliceCsgoThreeCommand);

        // different skillValue -> notEquals
        assertNotEquals(addFriendGameSkillAliceCsgoFiveCommand, addFriendGameSkillAliceCsgoThreeCommand);
    }

    private class ModelStubAcceptingFriendGameSkillUpdate extends ModelStub {
        final ArrayList<Friend> friendsInModel = new ArrayList<>();

        @Override
        public boolean hasFriendWithId(FriendId friendId) {
            return friendsInModel.stream().anyMatch(friend -> friend.getFriendId().equals(friendId));
        }

        @Override
        public Friend getFriend(FriendId friendId) {
            return friendsInModel.stream().filter(friend -> friend.getFriendId().equals(friendId))
                    .findFirst().orElse(null);
        }

        @Override
        public void addFriend(Friend friend) {
            friendsInModel.add(friend);
        }

        @Override
        public void setFriend(Friend target, Friend editedFriend) {
            int index = friendsInModel.indexOf(target);
            if (index < 0 || index >= friendsInModel.size()) {
                return;
            }
            friendsInModel.set(index, editedFriend);
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            } else if (!(other instanceof ModelStubAcceptingFriendGameSkillUpdate)) {
                return false;
            } else {
                ModelStubAcceptingFriendGameSkillUpdate otherStub =
                        (ModelStubAcceptingFriendGameSkillUpdate) other;
                return otherStub.friendsInModel.size() == this.friendsInModel.size()
                        && otherStub.friendsInModel.containsAll(this.friendsInModel);
            }
        }
    }
}
