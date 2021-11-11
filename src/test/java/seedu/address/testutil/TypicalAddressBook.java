package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalRooms.getTypicalRooms;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.room.Room;

/**
 * A utility class for constructing typical address books to be used in tests.
 */
public class TypicalAddressBook {

    /**
     * Returns an {@code AddressBook} with all the typical persons and rooms.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Room room : getTypicalRooms()) {
            ab.addRoom(room);
        }
        return ab;
    }
}
