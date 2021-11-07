package seedu.address.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DELUXE_ROOM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalGuests.ALICE_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.BENSON_GUEST;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.guest.GuestBuilder;

public class GuestTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Guest guest = new GuestBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> guest.getTags().remove(0));
    }

    @Test
    public void isSame() {
        // same object -> returns true
        assertTrue(ALICE_GUEST.isSame(ALICE_GUEST));

        // null -> returns false
        assertFalse(ALICE_GUEST.isSame(null));

        // same passport number, all other attributes different -> returns true
        Guest editedAlice = new GuestBuilder(ALICE_GUEST)
                .withName(VALID_NAME_BENSON)
                .withEmail(VALID_EMAIL_BENSON)
                .withRoomNumber(VALID_ROOM_NUMBER_BENSON)
                .withTags(VALID_TAG_DELUXE_ROOM)
                .build();

        assertTrue(ALICE_GUEST.isSame(editedAlice));

        // different passport number, all other attributes same -> returns false
        editedAlice = new GuestBuilder(ALICE_GUEST).withPassportNumber(VALID_PASSPORT_NUMBER_BENSON).build();
        assertFalse(ALICE_GUEST.isSame(editedAlice));

        // Passport number has trailing spaces, all other attributes same -> returns false
        String passportWithTrailingSpaces = VALID_PASSPORT_NUMBER_BENSON + "  ";
        editedAlice = new GuestBuilder(ALICE_GUEST).withPassportNumber(passportWithTrailingSpaces).build();
        assertFalse(ALICE_GUEST.isSame(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Guest aliceCopy = new GuestBuilder(ALICE_GUEST).build();
        assertTrue(ALICE_GUEST.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE_GUEST.equals(ALICE_GUEST));

        // null -> returns false
        assertFalse(ALICE_GUEST.equals(null));

        // different type -> returns false
        assertFalse(ALICE_GUEST.equals(5));

        // different guest -> returns false
        assertFalse(ALICE_GUEST.equals(BENSON_GUEST));

        // different passport number -> returns false
        Guest editedFiona = new GuestBuilder(ALICE_GUEST).withPassportNumber(VALID_PASSPORT_NUMBER_BENSON).build();
        assertFalse(ALICE_GUEST.equals(editedFiona));

        // different name -> returns false
        editedFiona = new GuestBuilder(ALICE_GUEST).withName(VALID_NAME_BENSON).build();
        assertFalse(ALICE_GUEST.equals(editedFiona));

        // different room number -> returns false
        editedFiona = new GuestBuilder(ALICE_GUEST).withRoomNumber(VALID_ROOM_NUMBER_BENSON).build();
        assertFalse(ALICE_GUEST.equals(editedFiona));

        // different email -> returns false
        editedFiona = new GuestBuilder(ALICE_GUEST).withEmail(VALID_EMAIL_BENSON).build();
        assertFalse(ALICE_GUEST.equals(editedFiona));

        // different tags -> returns false
        editedFiona = new GuestBuilder(ALICE_GUEST).withTags(VALID_TAG_DELUXE_ROOM).build();
        assertFalse(ALICE_GUEST.equals(editedFiona));
    }


}
