package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalFriends.ALICE_FRIEND_ID;
import static seedu.address.testutil.TypicalFriends.BOB;
import static seedu.address.testutil.TypicalFriends.ELLE;
import static seedu.address.testutil.TypicalFriends.ELLE_FRIEND_ID;
import static seedu.address.testutil.TypicalFriends.GEORGE_FRIEND_ID;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.GENSHIN_IMPACT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.DuplicateFriendException;
import seedu.address.model.friend.exceptions.FriendNotFoundException;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.testutil.FriendBuilder;
import seedu.address.testutil.GameFriendLinkBuilder;

public class UniqueFriendsListTest {

    private final UniqueFriendsList uniqueFriendsList = new UniqueFriendsList();

    @Test
    public void contains_nullFriend_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.contains(null));
    }

    @Test
    public void contains_friendNotInList_returnsFalse() {
        assertFalse(uniqueFriendsList.contains(ALICE));
    }

    @Test
    public void contains_friendInList_returnsTrue() {
        uniqueFriendsList.add(ALICE);
        assertTrue(uniqueFriendsList.contains(ALICE));
    }

    @Test
    public void contains_friendWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFriendsList.add(ALICE);
        Friend editedAlice = new FriendBuilder(ALICE).withFriendName("Alice Pauline")
                .withGameFriendLinks(ALICE.getGameFriendLinks().values().toArray(GameFriendLink[]::new)).build();
        assertTrue(uniqueFriendsList.contains(editedAlice));
    }

    @Test
    public void containsId_nullFriendId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.containsId(null));
    }

    @Test
    public void containsId_friendWithSameIdNotInList_returnsFalse() {
        assertFalse(uniqueFriendsList.containsId(ALICE.getFriendId()));
    }

    @Test
    public void containsId_friendWithSameIdInList_returnsTrue() {
        uniqueFriendsList.add(ALICE);
        assertTrue(uniqueFriendsList.containsId(ALICE.getFriendId()));
    }

    @Test
    public void add_nullFriend_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.add(null));
    }

    @Test
    public void add_duplicateFriendWithEverythingSame_throwsDuplicateFriendException() {
        uniqueFriendsList.add(ALICE);
        assertThrows(DuplicateFriendException.class, () -> uniqueFriendsList.add(ALICE));
    }

    @Test
    public void add_duplicateFriendWithDifferentNonIdentityFields_throwsDuplicateFriendException() {
        uniqueFriendsList.add(ALICE);
        Friend editedAlice = new FriendBuilder(ALICE).withFriendName(VALID_NAME_BOB).build();
        assertThrows(DuplicateFriendException.class, () -> uniqueFriendsList.add(editedAlice));
    }

    @Test
    public void setFriend_nullTargetFriend_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.setFriend(null, ALICE));
    }

    @Test
    public void setFriend_nullEditedFriend_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.setFriend(ALICE, null));
    }

    @Test
    public void setFriend_targetFriendNotInList_throwsFriendNotFoundException() {
        assertThrows(FriendNotFoundException.class, () -> uniqueFriendsList.setFriend(ALICE, ALICE));
    }

    @Test
    public void setFriend_editedFriendIsSameFriend_success() {
        uniqueFriendsList.add(ALICE);
        uniqueFriendsList.setFriend(ALICE, ALICE);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        expectedUniqueFriendsList.add(ALICE);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setFriend_editedFriendHasSameIdentity_success() {
        uniqueFriendsList.add(ALICE);
        Friend editedAlice = new FriendBuilder(ALICE).withFriendName(VALID_NAME_BOB).withGameFriendLinks()
                .build();
        uniqueFriendsList.setFriend(ALICE, editedAlice);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        expectedUniqueFriendsList.add(editedAlice);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setFriend_editedFriendHasDifferentIdentity_success() {
        uniqueFriendsList.add(ALICE);
        uniqueFriendsList.setFriend(ALICE, BOB);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        expectedUniqueFriendsList.add(BOB);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setFriend_editedFriendHasNonUniqueIdentity_throwsDuplicateFriendException() {
        uniqueFriendsList.add(ALICE);
        uniqueFriendsList.add(BOB);
        assertThrows(DuplicateFriendException.class, () -> uniqueFriendsList.setFriend(ALICE, BOB));
    }

    @Test
    public void remove_nullFriend_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.remove(null));
    }

    @Test
    public void remove_friendDoesNotExist_throwsFriendNotFoundException() {
        assertThrows(FriendNotFoundException.class, () -> uniqueFriendsList.remove(ALICE));
    }

    @Test
    public void remove_existingFriend_removesFriend() {
        uniqueFriendsList.add(ALICE);
        uniqueFriendsList.remove(ALICE);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setFriends_nullUniqueFriendList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.setFriends((UniqueFriendsList) null));
    }

    @Test
    public void setFriends_uniqueFriendList_replacesOwnListWithProvidedUniqueFriendList() {
        uniqueFriendsList.add(ALICE);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        expectedUniqueFriendsList.add(BOB);
        uniqueFriendsList.setFriends(expectedUniqueFriendsList);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setFriends_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.setFriends((List<Friend>) null));
    }

    @Test
    public void setFriends_list_replacesOwnListWithProvidedList() {
        uniqueFriendsList.add(ALICE);
        List<Friend> friendList = Collections.singletonList(BOB);
        uniqueFriendsList.setFriends(friendList);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        expectedUniqueFriendsList.add(BOB);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setFriends_listWithDuplicateFriends_throwsDuplicateFriendException() {
        List<Friend> listWithDuplicateFriends = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateFriendException.class, () -> uniqueFriendsList.setFriends(listWithDuplicateFriends));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFriendsList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void link_nullFriend_throwsNullPointerException() {
        // null friend input
        GameFriendLink gameFriendLink =
                new GameFriendLinkBuilder()
                        .withFriendId(ELLE_FRIEND_ID)
                        .withGameId(GENSHIN_IMPACT.getGameId().toString())
                        .withUserName("GoldNova").build();
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.link(null, gameFriendLink));
    }

    @Test
    public void link_nullGameFriendLink_throwsNullPointerException() {
        // null gfl input
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.link(ELLE, null));
    }

    @Test
    public void link_validFriendAndGfl_success() {
        // valid input friend and gfl
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        Friend friendToLink = new FriendBuilder().withFriendId(ELLE_FRIEND_ID).build();
        uniqueFriendsList.add(friendToLink);
        GameFriendLink gameFriendLink =
                new GameFriendLinkBuilder()
                        .withFriendId(ELLE_FRIEND_ID)
                        .withGameId(GENSHIN_IMPACT.getGameId().toString())
                        .withUserName("GoldNova").build();
        friendToLink.link(gameFriendLink);
        expectedUniqueFriendsList.add(friendToLink);
        uniqueFriendsList.link(friendToLink, gameFriendLink);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void unlink_nullGame_throwsNullPointerException() {
        // null gfl input
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.unlink(ELLE, null));
    }

    @Test
    public void unlink_nullFriend_throwsNullPointerException() {
        // null gfl input
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.unlink(null, CSGO));
    }

    @Test
    public void unlink_validFriendAndGame_success() {
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        GameFriendLink gameFriendLink =
                new GameFriendLinkBuilder()
                        .withFriendId(ALICE_FRIEND_ID)
                        .withGameId(GENSHIN_IMPACT.getGameId().toString())
                        .withUserName("GoldNova").build();
        Friend friendToUnlink = new FriendBuilder().withFriendId(ELLE_FRIEND_ID)
                .withGameFriendLinks(gameFriendLink).build();
        uniqueFriendsList.add(friendToUnlink);
        uniqueFriendsList.unlink(friendToUnlink, GENSHIN_IMPACT);
        Friend friendAfterUnlink =
                new FriendBuilder().withFriendId(ELLE_FRIEND_ID).build();
        expectedUniqueFriendsList.add(friendAfterUnlink);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void removeLinksAllFriends_validFriendAndGame_success() {
        // remove link from multiple friends with game
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        GameFriendLink gameFriendLinkElle =
                new GameFriendLinkBuilder()
                        .withFriendId(ELLE_FRIEND_ID)
                        .withGameId(GENSHIN_IMPACT.getGameId().toString())
                        .withUserName("GoldNova").build();

        GameFriendLink gameFriendLinkGeorge =
                new GameFriendLinkBuilder()
                        .withFriendId(GEORGE_FRIEND_ID)
                        .withGameId(GENSHIN_IMPACT.getGameId().toString())
                        .withUserName("BensonBeast").build();

        Friend friendToUnlink = new FriendBuilder().withFriendId(ELLE_FRIEND_ID)
                .withGameFriendLinks(gameFriendLinkElle).build();
        Friend friendToUnlink2 =
                new FriendBuilder().withFriendId(GEORGE_FRIEND_ID)
                        .withGameFriendLinks(gameFriendLinkGeorge).build();
        uniqueFriendsList.add(friendToUnlink);
        uniqueFriendsList.add(friendToUnlink2);
        uniqueFriendsList.removeLinkAllFriends(GENSHIN_IMPACT);
        Friend friendAfterUnlink =
                new FriendBuilder().withFriendId(ELLE_FRIEND_ID).build();
        Friend friendAfterUnlink2 =
                new FriendBuilder().withFriendId(GEORGE_FRIEND_ID).build();
        expectedUniqueFriendsList.add(friendAfterUnlink);
        expectedUniqueFriendsList.add(friendAfterUnlink2);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void removeAll_nullGame_throwsNullPointerException() {
        // null game input
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.removeLinkAllFriends(null));
    }

    @Test
    public void equals() {
        // same object -> true
        assertEquals(uniqueFriendsList, uniqueFriendsList);

        // null -> false
        assertNotEquals(uniqueFriendsList, null);

        // different type -> false
        assertNotEquals(uniqueFriendsList, "String");

        // different objects, same values -> true
        UniqueFriendsList sameValues = new UniqueFriendsList();
        uniqueFriendsList.iterator().forEachRemaining(sameValues::add);
        assertEquals(uniqueFriendsList, sameValues);

        // different values -> false
        UniqueFriendsList diffValues = new UniqueFriendsList();
        diffValues.add(new FriendBuilder().withFriendId("NewFriend").build());
        assertNotEquals(uniqueFriendsList, diffValues);
    }
}
