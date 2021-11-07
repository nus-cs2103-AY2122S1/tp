package seedu.address.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DELUXE_ROOM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalGuests.ALICE_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.BENSON_GUEST;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.uniquelist.exceptions.DuplicateItemException;
import seedu.address.model.uniquelist.exceptions.ItemNotFoundException;
import seedu.address.testutil.guest.GuestBuilder;

public class UniqueGuestListTest {

    private final UniqueGuestList uniqueGuestList = new UniqueGuestList();

    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.contains(null));
    }

    @Test
    public void contains_guestNotInList_returnsFalse() {
        assertFalse(uniqueGuestList.contains(ALICE_GUEST));
    }

    @Test
    public void contains_guestInList_returnsTrue() {
        uniqueGuestList.add(ALICE_GUEST);
        assertTrue(uniqueGuestList.contains(ALICE_GUEST));
    }

    @Test
    public void contains_guestWithSameIdentityFieldsInList_returnsTrue() {
        uniqueGuestList.add(ALICE_GUEST);
        Guest editedFiona =
                new GuestBuilder(ALICE_GUEST).withTags(VALID_TAG_DELUXE_ROOM)
                        .build();
        assertTrue(uniqueGuestList.contains(editedFiona));
    }

    @Test
    public void add_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.add(null));
    }

    @Test
    public void add_duplicateGuest_throwsDuplicateGuestException() {
        uniqueGuestList.add(ALICE_GUEST);
        assertThrows(DuplicateItemException.class, () -> uniqueGuestList.add(ALICE_GUEST));
    }

    @Test
    public void setGuest_nullTargetGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.setItem(null, ALICE_GUEST));
    }

    @Test
    public void setGuest_nullEditedGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.setItem(ALICE_GUEST, null));
    }

    @Test
    public void setGuest_targetGuestNotInList_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueGuestList.setItem(ALICE_GUEST, ALICE_GUEST));
    }

    @Test
    public void setGuest_editedGuestIsSameGuest_success() {
        uniqueGuestList.add(ALICE_GUEST);
        uniqueGuestList.setItem(ALICE_GUEST, ALICE_GUEST);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        expectedUniqueGuestList.add(ALICE_GUEST);
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuest_editedGuestHasSameIdentity_success() {
        uniqueGuestList.add(ALICE_GUEST);
        Guest editedAlice =
                new GuestBuilder(ALICE_GUEST).withTags(VALID_TAG_DELUXE_ROOM)
                        .build();
        uniqueGuestList.setItem(ALICE_GUEST, editedAlice);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        expectedUniqueGuestList.add(editedAlice);
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuest_editedGuestHasDifferentIdentity_success() {
        uniqueGuestList.add(ALICE_GUEST);
        uniqueGuestList.setItem(ALICE_GUEST, BENSON_GUEST);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        expectedUniqueGuestList.add(BENSON_GUEST);
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuest_editedGuestHasNonUniqueIdentity_throwsDuplicateItemException() {
        uniqueGuestList.add(ALICE_GUEST);
        uniqueGuestList.add(BENSON_GUEST);
        assertThrows(DuplicateItemException.class, () -> uniqueGuestList.setItem(ALICE_GUEST, BENSON_GUEST));
    }

    @Test
    public void remove_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueGuestList.remove(ALICE_GUEST));
    }

    @Test
    public void remove_existingGuest_removesGuest() {
        uniqueGuestList.add(ALICE_GUEST);
        uniqueGuestList.remove(ALICE_GUEST);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuests_nullUniqueGuestList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.setItems((UniqueGuestList) null));
    }

    @Test
    public void setGuests_uniqueGuestList_replacesOwnListWithProvidedUniqueGuestList() {
        uniqueGuestList.add(ALICE_GUEST);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        expectedUniqueGuestList.add(BENSON_GUEST);
        uniqueGuestList.setItems(expectedUniqueGuestList);
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuests_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGuestList.setItems((List<Guest>) null));
    }

    @Test
    public void setGuests_list_replacesOwnListWithProvidedList() {
        uniqueGuestList.add(ALICE_GUEST);
        List<Guest> personList = Collections.singletonList(BENSON_GUEST);
        uniqueGuestList.setItems(personList);
        UniqueGuestList expectedUniqueGuestList = new UniqueGuestList();
        expectedUniqueGuestList.add(BENSON_GUEST);
        assertEquals(expectedUniqueGuestList, uniqueGuestList);
    }

    @Test
    public void setGuests_listWithDuplicateGuests_throwsDuplicateItemException() {
        List<Guest> listWithDuplicateGuests = Arrays.asList(ALICE_GUEST, ALICE_GUEST);
        assertThrows(DuplicateItemException.class, () -> uniqueGuestList.setItems(listWithDuplicateGuests));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, (
        ) -> uniqueGuestList.asUnmodifiableObservableList().remove(0));
    }
}
