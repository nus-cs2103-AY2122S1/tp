package seedu.address.testutil;

import static seedu.address.testutil.TypicalFacilities.getTypicalFacilities;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import seedu.address.model.AddressBook;
import seedu.address.model.facility.Facility;
import seedu.address.model.person.Person;

public class TypicalAddressBook {
    private TypicalAddressBook() {} // Prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons and facilities.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person: getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Facility facility: getTypicalFacilities()) {
            ab.addFacility(facility);
        }
        return ab;
    }
}
