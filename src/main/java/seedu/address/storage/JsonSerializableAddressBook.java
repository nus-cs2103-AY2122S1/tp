package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Module;
import seedu.address.model.ReadOnlyModule;
import seedu.address.model.student.Student;

/**
 * An Immutable Module that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate student(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyModule} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyModule source) {
        persons.addAll(source.getStudentList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Module toModelType() throws IllegalValueException {
        Module module = new Module();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Student student = jsonAdaptedPerson.toModelType();
            if (module.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            module.addStudent(student);
        }
        return module;
    }

}
