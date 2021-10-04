package seedu.fast.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.model.Fast;
import seedu.fast.model.ReadOnlyFast;
import seedu.fast.model.person.Person;

/**
 * An Immutable FAST address book that is serializable to JSON format.
 */
@JsonRootName(value = "fast")
class JsonSerializableFast {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFast} with the given persons.
     */
    @JsonCreator
    public JsonSerializableFast(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyFast} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFast}.
     */
    public JsonSerializableFast(ReadOnlyFast source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this FAST into the model's {@code Fast} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Fast toModelType() throws IllegalValueException {
        Fast fast = new Fast();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (fast.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            fast.addPerson(person);
        }
        return fast;
    }

}
