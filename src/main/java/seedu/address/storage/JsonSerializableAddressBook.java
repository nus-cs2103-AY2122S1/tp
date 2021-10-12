package seedu.address.storage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.SubGroup;
import seedu.address.model.group.SuperGroup;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedSuperGroup> superGroups = new ArrayList<>();

    private final List<JsonAdaptedSubGroup> subGroups = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
        @JsonProperty("superGroups") List<JsonAdaptedSuperGroup> superGroups,
        @JsonProperty("subGroups") List<JsonAdaptedSubGroup> subGroups) {
        this.persons.addAll(persons);
        this.superGroups.addAll(superGroups);
        this.subGroups.addAll(subGroups);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(
            Collectors.toList()));
        superGroups
            .addAll(source.getSuperGroups().values().stream().map(JsonAdaptedSuperGroup::new).collect(
            Collectors.toList()));
        subGroups
            .addAll(source.getSubGroups().values().stream().map(JsonAdaptedSubGroup::new).collect(
                Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedSuperGroup jsonAdaptedSuperGroup : superGroups) {
            SuperGroup superGroup = jsonAdaptedSuperGroup.toModelType();
            if (!addressBook.hasSuperGroup(superGroup)) {
                addressBook.addSuperGroup(superGroup);
            }
        }
        for (JsonAdaptedSubGroup jsonAdaptedSubGroups : subGroups) {
            SubGroup subGroup = jsonAdaptedSubGroups.toModelType();
            if (!addressBook.hasSubGroup(subGroup)) {
                addressBook.addSubGroup(subGroup);
            }
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            for (String superGroup : person.getSuperGroups()) {
                addressBook.findSuperGroup(superGroup).addPerson(person);
            }
            for (String subGroup : person.getSubGroups()) {
                addressBook.findSubGroup(subGroup).addPerson(person);
            }

            addressBook.addPerson(person);
        }
        return addressBook;
    }
}
