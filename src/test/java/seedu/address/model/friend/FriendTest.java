package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalFriends.ALICE_FRIEND_ID;
import static seedu.address.testutil.TypicalFriends.BOB;
import static seedu.address.testutil.TypicalFriends.CARL;
import static seedu.address.testutil.TypicalGameFriendLinks.APEX_AMY_DRACO_LINK;
import static seedu.address.testutil.TypicalGameFriendLinks.CSGO_AMY_DRACO_LINK;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.game.Game;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.testutil.FriendBuilder;
import seedu.address.testutil.GameBuilder;
import seedu.address.testutil.GameFriendLinkBuilder;


public class FriendTest {

    @Test
    public void constructor_nullFriendName_defaultName() {
        Friend friend = new Friend(CARL.getFriendId(), null, CARL.getGameFriendLinks(), CARL.getSchedule());
        assertEquals(FriendName.DEFAULT_FRIEND_NAME, friend.getFriendName());
    }

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
        Friend aliceNoGfl = new FriendBuilder().withFriendId(ALICE_FRIEND_ID).build();
        GameFriendLink gameFriendLink =
                new GameFriendLinkBuilder()
                        .withFriendId(ALICE_FRIEND_ID)
                        .withGameId(VALID_GAME_ID_CSGO).build();
        Friend aliceWithGfl =
                new FriendBuilder().withFriendId(ALICE_FRIEND_ID).withGameFriendLinks(gameFriendLink).build();
        aliceNoGfl.link(gameFriendLink);
        assertEquals(aliceNoGfl, aliceWithGfl);
    }

    @Test
    public void link_nullGfl_throwsNullPointerException() {
        Friend alice = new FriendBuilder().withFriendId(ALICE_FRIEND_ID).build();
        assertThrows(NullPointerException.class, () -> alice.link(null));
    }

    @Test
    public void unlink_validGflExistInFriend_success() {
        GameFriendLink gameFriendLink =
                new GameFriendLinkBuilder()
                        .withFriendId(ALICE_FRIEND_ID)
                        .withGameId(VALID_GAME_ID_CSGO).build();
        Friend aliceWithGfl =
                new FriendBuilder()
                        .withFriendId(ALICE_FRIEND_ID)
                        .withGameFriendLinks(gameFriendLink).build();
        Game csgo = new GameBuilder().withGameId(VALID_GAME_ID_CSGO).build();
        Friend aliceWithNoGfl =
                new FriendBuilder()
                        .withFriendId(ALICE_FRIEND_ID)
                        .build();
        aliceWithGfl.unlink(csgo);
        assertEquals(aliceWithNoGfl, aliceWithGfl);
    }

    @Test
    public void unlink_nullGame_throwsNullPointerException() {
        Friend alice = new FriendBuilder().withFriendId(ALICE_FRIEND_ID).build();
        assertThrows(NullPointerException.class, () -> alice.unlink(null));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(ALICE, null);

        // different type -> returns false
        assertNotEquals(ALICE, "String");

        // same values -> returns true
        Friend aliceCopy = new FriendBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

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
        assertNotEquals(ALICE, editedAlice);

        // different schedule -> false
        Schedule newSchedule = new Schedule();
        try {
            newSchedule.setScheduleDay(1, "1", "2", true);
        } catch (InvalidDayTimeException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        editedAlice = new FriendBuilder(ALICE).withSchedule(newSchedule).build();
        assertNotEquals(ALICE, editedAlice);
    }

    @Test
    public void toString_emptyGameFriendLinks_correctString() {
        String expectedMessage = "Friend ID: amyawesome; Name: Amy Bee; \n"
                + "Day: MONDAY; Free TimeSlots: ;\n"
                + "Day: TUESDAY; Free TimeSlots: ;\n"
                + "Day: WEDNESDAY; Free TimeSlots: ;\n"
                + "Day: THURSDAY; Free TimeSlots: ;\n"
                + "Day: FRIDAY; Free TimeSlots: ;\n"
                + "Day: SATURDAY; Free TimeSlots: ;\n"
                + "Day: SUNDAY; Free TimeSlots: ;";

        Friend editedAmy = new FriendBuilder().withFriendId(VALID_FRIEND_ID_AMY).withFriendName(VALID_NAME_AMY)
                .withGameFriendLinks().build();
        assertEquals(expectedMessage, editedAmy.toString());
    }
}
