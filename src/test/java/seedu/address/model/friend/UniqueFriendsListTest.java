package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueFriendsList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueFriendsList.add(ALICE);
        assertTrue(uniqueFriendsList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFriendsList.add(ALICE);
        Friend editedAlice = new FriendBuilder(ALICE).withGameFriendLinks()
                .build();
        assertTrue(uniqueFriendsList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueFriendsList.add(ALICE);
        assertThrows(DuplicateFriendException.class, () -> uniqueFriendsList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.setFriend(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.setFriend(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(FriendNotFoundException.class, () -> uniqueFriendsList.setFriend(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueFriendsList.add(ALICE);
        uniqueFriendsList.setFriend(ALICE, ALICE);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        expectedUniqueFriendsList.add(ALICE);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueFriendsList.add(ALICE);
        Friend editedAlice = new FriendBuilder(ALICE).withGameFriendLinks()
                .build();
        uniqueFriendsList.setFriend(ALICE, editedAlice);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        expectedUniqueFriendsList.add(editedAlice);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueFriendsList.add(ALICE);
        uniqueFriendsList.setFriend(ALICE, BOB);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        expectedUniqueFriendsList.add(BOB);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueFriendsList.add(ALICE);
        uniqueFriendsList.add(BOB);
        assertThrows(DuplicateFriendException.class, () -> uniqueFriendsList.setFriend(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(FriendNotFoundException.class, () -> uniqueFriendsList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueFriendsList.add(ALICE);
        uniqueFriendsList.remove(ALICE);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.setFriends((UniqueFriendsList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueFriendsList.add(ALICE);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        expectedUniqueFriendsList.add(BOB);
        uniqueFriendsList.setFriends(expectedUniqueFriendsList);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFriendsList.setFriends((List<Friend>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueFriendsList.add(ALICE);
        List<Friend> friendList = Collections.singletonList(BOB);
        uniqueFriendsList.setFriends(friendList);
        UniqueFriendsList expectedUniqueFriendsList = new UniqueFriendsList();
        expectedUniqueFriendsList.add(BOB);
        assertEquals(expectedUniqueFriendsList, uniqueFriendsList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Friend> listWithDuplicateFriends = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateFriendException.class, () -> uniqueFriendsList.setFriends(listWithDuplicateFriends));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFriendsList.asUnmodifiableObservableList().remove(0));
    }
}
