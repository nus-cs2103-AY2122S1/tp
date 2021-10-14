package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.exceptions.DuplicateFriendException;
import seedu.address.testutil.FriendBuilder;

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
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Friend editedAlice = new FriendBuilder(ALICE).withGameFriendLinks()
                .build();
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
        Friend editedAlice = new FriendBuilder(ALICE).withGameFriendLinks()
                .build();
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

}
