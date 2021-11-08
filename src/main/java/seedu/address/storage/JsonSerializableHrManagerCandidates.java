package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.person.Person;

/**
 * An Immutable HrManager that is serializable to JSON format.
 */
@JsonRootName(value = "HrManagerCandidates")
public class JsonSerializableHrManagerCandidates {
    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHrManagerCandidates} with the given persons.
     */
    @JsonCreator
    public JsonSerializableHrManagerCandidates(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyHrManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHrManagerCandidates}.
     */
    public JsonSerializableHrManagerCandidates(ReadOnlyHrManager source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this serializable HrManagerCandidates into the model's {@code HrManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HrManager toModelType() throws IllegalValueException {
        HrManager hrManager = new HrManager();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (hrManager.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            hrManager.addPerson(person);
        }
        return hrManager;
    }
}
