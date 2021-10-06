package seedu.siasa.testutil;

import seedu.siasa.model.Siasa;
import seedu.siasa.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Siasa siasa;

    public AddressBookBuilder() {
        siasa = new Siasa();
    }

    public AddressBookBuilder(Siasa siasa) {
        this.siasa = siasa;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        siasa.addPerson(person);
        return this;
    }

    public Siasa build() {
        return siasa;
    }
}
