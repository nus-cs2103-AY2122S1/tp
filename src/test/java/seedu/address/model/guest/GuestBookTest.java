package seedu.address.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DELUXE_ROOM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalGuests.ALICE_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalGuestBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.uniquelist.exceptions.DuplicateItemException;
import seedu.address.testutil.guest.GuestBuilder;

public class GuestBookTest {

    private final GuestBook guestBook = new GuestBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), guestBook.getGuestList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> guestBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyGuestBook_replacesData() {
        GuestBook newData = getTypicalGuestBook();
        guestBook.resetData(newData);
        assertEquals(newData, guestBook);
    }

    @Test
    public void resetData_withDuplicateGuests_throwsDuplicateItemException() {
        // Two guests with the same identity fields
        Guest editedAlice =
                new GuestBuilder(ALICE_GUEST).withTags(VALID_TAG_DELUXE_ROOM)
                        .build();
        List<Guest> newGuests = Arrays.asList(ALICE_GUEST, editedAlice);
        GuestBookStub newData = new GuestBookStub(newGuests);

        assertThrows(DuplicateItemException.class, () -> guestBook.resetData(newData));
    }

    @Test
    public void hasGuest_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> guestBook.hasGuest(null));
    }

    @Test
    public void hasGuest_guestNotInGuestBook_returnsFalse() {
        assertFalse(guestBook.hasGuest(ALICE_GUEST));
    }

    @Test
    public void hasGuest_guestInGuestBook_returnsTrue() {
        guestBook.addGuest(ALICE_GUEST);
        assertTrue(guestBook.hasGuest(ALICE_GUEST));
    }

    @Test
    public void hasGuest_guestWithSameIdentityFieldsInGuestBook_returnsTrue() {
        guestBook.addGuest(ALICE_GUEST);
        Guest editedAlice =
                new GuestBuilder(ALICE_GUEST).withTags(VALID_TAG_DELUXE_ROOM)
                        .build();
        assertTrue(guestBook.hasGuest(editedAlice));
    }

    @Test
    public void getGuestList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> guestBook.getGuestList().remove(0));
    }

    /**
     * A stub ReadOnlyGuestBook whose guest list can violate interface constraints.
     */
    private static class GuestBookStub implements ReadOnlyGuestBook {
        private final ObservableList<Guest> guests = FXCollections.observableArrayList();

        GuestBookStub(Collection<Guest> guests) {
            this.guests.setAll(guests);
        }

        @Override
        public ObservableList<Guest> getGuestList() {
            return guests;
        }
    }

}
