package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.AIRZONE;
import static seedu.address.testutil.TypicalContacts.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ContactBuilder;

public class ContactTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Contact contact = new ContactBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> contact.getTags().remove(0));
    }

    @Test
    public void isSameContact() {
        // same object -> returns true
        assertTrue(AIRZONE.isSameContact(AIRZONE));

        // null -> returns false
        assertFalse(AIRZONE.isSameContact(null));

        // same name, all other attributes different -> returns true
        Contact editedAirzone = new ContactBuilder(AIRZONE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(AIRZONE.isSameContact(editedAirzone));

        // different name, all other attributes same -> returns false
        editedAirzone = new ContactBuilder(AIRZONE).withName(VALID_NAME_BOB).build();
        assertFalse(AIRZONE.isSameContact(editedAirzone));

        // name differs in case, all other attributes same -> returns false
        Contact editedBob = new ContactBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameContact(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ContactBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameContact(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Contact airzoneCopy = new ContactBuilder(AIRZONE).build();
        assertTrue(AIRZONE.equals(airzoneCopy));

        // same object -> returns true
        assertTrue(AIRZONE.equals(AIRZONE));

        // null -> returns false
        assertFalse(AIRZONE.equals(null));

        // different type -> returns false
        assertFalse(AIRZONE.equals(5));

        // different contact -> returns false
        assertFalse(AIRZONE.equals(BOB));

        // different name -> returns false
        Contact editedAirzone = new ContactBuilder(AIRZONE).withName(VALID_NAME_BOB).build();
        assertFalse(AIRZONE.equals(editedAirzone));

        // different phone -> returns false
        editedAirzone = new ContactBuilder(AIRZONE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(AIRZONE.equals(editedAirzone));

        // different email -> returns false
        editedAirzone = new ContactBuilder(AIRZONE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(AIRZONE.equals(editedAirzone));

        // different address -> returns false
        editedAirzone = new ContactBuilder(AIRZONE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(AIRZONE.equals(editedAirzone));

        // different tags -> returns false
        editedAirzone = new ContactBuilder(AIRZONE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(AIRZONE.equals(editedAirzone));
    }
}
