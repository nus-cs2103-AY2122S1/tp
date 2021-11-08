package safeforhall.testutil;

import safeforhall.model.AddressBook;
import safeforhall.model.event.Event;
import safeforhall.model.person.Person;

public class TypicalAddressBook {

    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons and events.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Event event : TypicalEvents.getTypicalEvents()) {
            ab.addEvent(event);
        }
        return ab;
    }
}
