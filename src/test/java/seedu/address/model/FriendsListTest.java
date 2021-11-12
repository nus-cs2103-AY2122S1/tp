package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalFriends.ELLE;
import static seedu.address.testutil.TypicalFriends.ELLE_FRIEND_ID;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.GENSHIN_IMPACT;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.exceptions.DuplicateFriendException;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.testutil.FriendBuilder;
import seedu.address.testutil.GameFriendLinkBuilder;

public class FriendsListTest {

    private final FriendsList friendsList = new FriendsList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), friendsList.getFriendsList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendsList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        FriendsList newData = getTypicalFriendsList();
        friendsList.resetData(newData);
        assertEquals(newData, friendsList);
    }

    @Test
    public void linkFriend_withValidGameFriendLink_success() {
        FriendsList newFriendsList = getTypicalFriendsList();
        Friend friend = getTypicalFriendsList().getFriend(ELLE.getFriendId());
        GameFriendLink gameFriendLink = new GameFriendLinkBuilder()
                .withFriendId(ELLE_FRIEND_ID)
                .withGameId(GENSHIN_IMPACT.getGameId().toString())
                .withUserName("GoldNova").build();
        newFriendsList.linkFriend(friend, gameFriendLink);
        Friend linkedFriend = newFriendsList.getFriend(ELLE.getFriendId());
        assertTrue(linkedFriend.hasGameAssociation(GENSHIN_IMPACT));
    }

    @Test
    public void unlinkFriend_withValidGameFriendLink_success() {
        FriendsList friendsList = new FriendsList();
        GameFriendLink gameFriendLink = new GameFriendLinkBuilder()
                .withFriendId(ELLE_FRIEND_ID)
                .withGameId(GENSHIN_IMPACT.getGameId().toString())
                .withUserName("GoldNova").build();
        Friend friendBeforeUnlink =
                new FriendBuilder().withFriendId(ELLE_FRIEND_ID).withGameFriendLinks(gameFriendLink).build();
        friendsList.addFriend(friendBeforeUnlink);
        friendsList.unlinkFriend(friendBeforeUnlink, GENSHIN_IMPACT);
        Friend friendAfterUnlink =
                new FriendBuilder().withFriendId(ELLE_FRIEND_ID).build();
        FriendsList expectedFriendsList = new FriendsList();
        expectedFriendsList.addFriend(friendAfterUnlink);
        assertEquals(friendsList, expectedFriendsList);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Friend editedAlice = new FriendBuilder(ALICE).build();
        List<Friend> newFriends = Arrays.asList(ALICE, editedAlice);
        FriendsListStub newData = new FriendsListStub(newFriends);

        assertThrows(DuplicateFriendException.class, () -> friendsList.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendsList.hasFriend(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(friendsList.hasFriend(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        friendsList.addFriend(ALICE);
        assertTrue(friendsList.hasFriend(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        friendsList.addFriend(ALICE);
        Friend editedAlice = new FriendBuilder(ALICE).build();
        assertTrue(friendsList.hasFriend(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> friendsList.getFriendsList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class FriendsListStub implements ReadOnlyFriendsList {
        private final ObservableList<Friend> friends = FXCollections.observableArrayList();

        FriendsListStub(Collection<Friend> friends) {
            this.friends.setAll(friends);
        }

        @Override
        public ObservableList<Friend> getFriendsList() {
            return friends;
        }
    }

    @Test
    public void equals() {
        // same object
        assertEquals(friendsList, friendsList);

        // null
        assertNotEquals(friendsList, null);

        // different type
        assertNotEquals(friendsList, "String");

        // another new friendsList
        assertEquals(friendsList, new FriendsList());

        // lists with friend with same fields -> equals
        FriendsList first = new FriendsList();
        FriendsList second = new FriendsList();
        first.addFriend(new FriendBuilder().withFriendId("dummy").withFriendName("Jim").build());
        second.addFriend(new FriendBuilder().withFriendId("dummy").withFriendName("Jim").build());
        assertEquals(first, second);
    }
}
