package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalFriends.AMY;
import static seedu.address.testutil.TypicalFriends.BOB;
import static seedu.address.testutil.TypicalGameFriendLinks.APEX_AMY_DRACO_LINK;
import static seedu.address.testutil.TypicalGameFriendLinks.CSGO_AMY_DRACO_LINK;

import org.junit.jupiter.api.Test;

import seedu.address.model.game.Game;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.testutil.FriendBuilder;
import seedu.address.testutil.GameBuilder;

public class FriendTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Friend friend = new FriendBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> friend.getGameFriendLinks().remove(0));
    }

    @Test
    public void isSameFriendId() {
        // same object -> returns true
        assertTrue(ALICE.isSameFriendId(ALICE));

        // same name, all other attributes different -> returns true
        Friend editedAlice = new FriendBuilder(ALICE).withFriendName(VALID_NAME_BOB).withGameFriendLinks().build();
        assertTrue(ALICE.isSameFriendId(editedAlice));

        // different id, all other attributes same -> returns false
        editedAlice = new FriendBuilder(ALICE).withFriendId(VALID_FRIEND_ID_BOB).build();
        assertFalse(ALICE.isSameFriendId(editedAlice));

        // id differs in case, all other attributes same -> returns false
        Friend editedBob = new FriendBuilder(BOB).withFriendName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameFriendId(editedBob));
    }

    @Test
    public void getNumberOfGames() {
        // add two games, should return 2
        Friend copyAlice = new FriendBuilder(ALICE)
                .withGameFriendLinks(CSGO_AMY_DRACO_LINK, APEX_AMY_DRACO_LINK).build();
        assertEquals(2, copyAlice.getNumberOfGames());
    }

    @Test
    public void link_validGfl_success() {
        Friend amy = AMY;
        GameFriendLink gameFriendLink = CSGO_AMY_DRACO_LINK;
        amy.link(gameFriendLink);
        Game csgo = new GameBuilder().withGameId(VALID_GAME_ID_CSGO).build();
        assertTrue(amy.getGameFriendLinks().containsKey(csgo.getGameId()));
    }

    @Test
    public void link_nullGfl_throwsNullPointerException() {
        Friend amy = AMY;
        assertThrows(NullPointerException.class, () -> amy.link(null));
    }

    @Test
    public void unlink_validGflExistInFriend_success() {
        Friend alice =
                new FriendBuilder()
                        .withFriendId(AMY.getFriendId().toString())
                        .withGameFriendLinks(CSGO_AMY_DRACO_LINK).build();
        Game csgo = new GameBuilder().withGameId(VALID_GAME_ID_CSGO).build();
        alice.unlink(csgo);
        assertFalse(alice.getGameFriendLinks().containsKey(csgo.getGameId()));
    }

    @Test
    public void unlink_nullGame_throwsNullPointerException() {
        Friend amy = AMY;
        assertThrows(NullPointerException.class, () -> amy.unlink(null));
    }


    @Test
    public void equals() {
        // same values -> returns true
        Friend aliceCopy = new FriendBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(null, ALICE);

        // different type -> returns false
        assertNotEquals(5, ALICE);

        // different friend -> returns false
        assertNotEquals(BOB, ALICE);

        // different friendId -> returns false
        Friend editedAlice = new FriendBuilder(ALICE).withFriendId(VALID_FRIEND_ID_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different name -> returns false
        editedAlice = new FriendBuilder(ALICE).withFriendName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different games -> returns false
        editedAlice = new FriendBuilder(ALICE).withGameFriendLinks(CSGO_AMY_DRACO_LINK).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
