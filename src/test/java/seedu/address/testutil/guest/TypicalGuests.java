package seedu.address.testutil.guest;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CARL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CARL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_CARL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_CARL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CARL;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FOURTH_PERSON_NOT_ADDED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestBook;

/**
 * A utility class containing a list of {@code Guest} objects to be used in tests.
 */
public class TypicalGuests {
    public static final Guest ALICE_GUEST = new GuestBuilder()
            .withName(VALID_NAME_ALICE)
            .withEmail(VALID_EMAIL_ALICE)
            .withTags(VALID_TAG_ALICE)
            .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
            .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
            .build();

    public static final Guest BENSON_GUEST = new GuestBuilder()
            .withName(VALID_NAME_BENSON)
            .withEmail(VALID_EMAIL_BENSON)
            .withTags(VALID_TAG_BENSON)
            .withRoomNumber(VALID_ROOM_NUMBER_BENSON)
            .withPassportNumber(VALID_PASSPORT_NUMBER_BENSON)
            .build();

    public static final Guest CARL_GUEST = new GuestBuilder()
            .withName(VALID_NAME_CARL)
            .withEmail(VALID_EMAIL_CARL)
            .withTags(VALID_TAG_CARL)
            .withRoomNumber(VALID_ROOM_NUMBER_CARL)
            .withPassportNumber(VALID_PASSPORT_NUMBER_CARL)
            .build();

    public static final Guest JEONGYEON_GUEST = new GuestBuilder()
            .withName("Jeong Yeon")
            .withEmail("jy@example.com")
            .withRoomNumber("22233")
            .withPassportNumber(PASSPORT_NUMBER_FOURTH_PERSON_NOT_ADDED.toString())
            .build();

    public static final Guest BOB_ARCHIVED_GUEST = new GuestBuilder()
            .withName("Bob")
            .withEmail("bob@example.com")
            .withRoomNumber("22113")
            .withPassportNumber("X9995552")
            .build();

    private TypicalGuests() {
    } // prevents instantiation

    /**
     * Returns an {@code GuestBook} with all the typical guests.
     */
    public static GuestBook getTypicalGuestBook() {
        GuestBook guestBook = new GuestBook();
        for (Guest guest : getTypicalGuests()) {
            guestBook.addGuest(guest);
        }
        return guestBook;
    }

    /**
     * Returns an {@code Archive} with all the archived guests.
     */
    public static Archive getTypicalArchive() {
        Archive archive = new Archive();
        archive.addGuest(BOB_ARCHIVED_GUEST);
        return archive;
    }

    public static List<Guest> getTypicalGuests() {
        return new ArrayList<>(Arrays.asList(ALICE_GUEST, BENSON_GUEST, CARL_GUEST));
    }
}
