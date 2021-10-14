package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalFriends.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.DuplicateFriendException;
import seedu.address.model.friend.exceptions.FriendNotFoundException;
import seedu.address.testutil.FriendBuilder;

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
        Friend editedAlice = new FriendBuilder(ALICE).withFriendName("Alice Pauline").withGameFriendLinks().build();
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
}
