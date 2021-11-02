package seedu.address.testutil;

import static seedu.address.testutil.TypicalFacilities.getTypicalFacilities;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsToFind;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsUnsortedName;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsUnsortedTag;

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

    /**
     * Returns an {@code AddressBook} with all the typical persons and facilities.
     */
    public static AddressBook getTypicalAddressBookToFind() {
        AddressBook ab = new AddressBook();
        for (Person person: getTypicalPersonsToFind()) {
            ab.addPerson(person);
        }
        for (Facility facility: getTypicalFacilities()) {
            ab.addFacility(facility);
        }
        return ab;
    }

    /**
     * Returns an unsorted {@code AddressBook} with all the typical persons and facilities.
     */
    public static AddressBook getUnsortedNameAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person: getTypicalPersonsUnsortedName()) {
            ab.addPerson(person);
        }
        for (Facility facility: getTypicalFacilities()) {
            ab.addFacility(facility);
        }
        return ab;
    }

    /**
     * Returns an unsorted {@code AddressBook} with all the typical persons and facilities.
     */
    public static AddressBook getUnsortedTagAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person: getTypicalPersonsUnsortedTag()) {
            ab.addPerson(person);
        }
        for (Facility facility: getTypicalFacilities()) {
            ab.addFacility(facility);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons and no facilities.
     */
    public static AddressBook getTypicalAddressBookEmptyFacilityList() {
        AddressBook ab = new AddressBook();
        for (Person person: getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical facilities and no persons.
     */
    public static AddressBook getTypicalAddressBookEmptyMemberList() {
        AddressBook ab = new AddressBook();
        for (Facility facility: getTypicalFacilities()) {
            ab.addFacility(facility);
        }
        return ab;
    }
}
