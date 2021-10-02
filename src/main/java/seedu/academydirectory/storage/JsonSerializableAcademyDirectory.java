package seedu.academydirectory.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.academydirectory.commons.exceptions.IllegalValueException;
import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.ReadOnlyAcademyDirectory;
import seedu.academydirectory.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "academy directory")
class JsonSerializableAcademyDirectory {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAcademyDirectory} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAcademyDirectory(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAcademyDirectory} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAcademyDirectory}.
     */
    public JsonSerializableAcademyDirectory(ReadOnlyAcademyDirectory source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this academy directory into the model's {@code AcademyDirectory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AcademyDirectory toModelType() throws IllegalValueException {
        AcademyDirectory academyDirectory = new AcademyDirectory();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (academyDirectory.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            academyDirectory.addPerson(person);
        }
        return academyDirectory;
    }

}
