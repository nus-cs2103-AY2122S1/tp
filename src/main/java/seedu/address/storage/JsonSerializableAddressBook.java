package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.Tuition.TuitionClass;
import seedu.address.model.person.Person;
import seedu.address.ui.UiManager;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TUITION = "Tuition list contains duplicate tuition(s).";


    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedTuition> tuitions = new ArrayList<>();

    private static final Logger logger = LogsCenter.getLogger(JsonSerializableAddressBook.class);


    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons, @JsonProperty("tuitions") List<JsonAdaptedTuition> tuitions) {
        this.persons.addAll(persons);
        this.tuitions.addAll(tuitions);
    }


//    @JsonCreator
//    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
//        this.persons.addAll(persons);
//    }
//
//    @JsonCreator
//    public JsonSerializableAddressBook(@JsonProperty("tuition") List<JsonAdaptedPerson> persons) {
//        this.persons.addAll(persons);
//    }



    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tuitions.addAll(source.getTuitionList().stream().map(JsonAdaptedTuition::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            logger.info("JSAPerson: " + person.toString());
            addressBook.addPerson(person);
        }

        for (JsonAdaptedTuition jsonAdaptedTuition: tuitions) {
            TuitionClass tuitionClass = jsonAdaptedTuition.toModelType();
            if (addressBook.hasTuition(tuitionClass)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TUITION);
            }
            logger.info("JSATuition: " + tuitionClass.toString());

            addressBook.addTuition(tuitionClass);
        }

        return addressBook;
    }

}
