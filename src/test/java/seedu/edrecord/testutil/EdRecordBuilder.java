package seedu.edrecord.testutil;

import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code EdRecord ab = new EdRecordBuilder().withPerson("John", "Doe").build();}
 */
public class EdRecordBuilder {

    private EdRecord edRecord;

    public EdRecordBuilder() {
        edRecord = new EdRecord();
    }

    public EdRecordBuilder(EdRecord edRecord) {
        this.edRecord = edRecord;
    }

    /**
     * Adds a new {@code Person} to the {@code EdRecord} that we are building.
     */
    public EdRecordBuilder withPerson(Person person) {
        edRecord.addPerson(person);
        return this;
    }

    public EdRecord build() {
        return edRecord;
    }
}
