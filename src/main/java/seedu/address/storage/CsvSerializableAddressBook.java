package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

/**
 * An immutable AddressBook that is serializable to CSV format.
 */
public class CsvSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<CsvAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@link CsvSerializableAddressBook} with the given persons.
     *
     * @param persons List of {@link CsvAdaptedPerson}.
     */
    public CsvSerializableAddressBook(List<CsvAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Constructs a {@link CsvSerializableAddressBook} with using an existing {@link ReadOnlyAddressBook}.
     *
     * @param source Existing ReadOnlyAddressBook
     */
    public CsvSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(CsvAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this {@link CsvSerializableAddressBook} into a {@link AddressBook}.
     *
     * @return Converted AddressBook
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (CsvAdaptedPerson csvAdaptedPerson : persons) {
            Person person = csvAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

    public List<CsvAdaptedPerson> getPersons() {
        return persons;
    }
}
