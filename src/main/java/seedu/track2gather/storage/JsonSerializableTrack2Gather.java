package seedu.track2gather.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.track2gather.commons.exceptions.IllegalValueException;
import seedu.track2gather.model.ReadOnlyTrack2Gather;
import seedu.track2gather.model.Track2Gather;
import seedu.track2gather.model.person.Person;

/**
 * An Immutable Track2Gather that is serializable to JSON format.
 */
@JsonRootName(value = "track2gather")
class JsonSerializableTrack2Gather {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate case number(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrack2Gather} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTrack2Gather(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyTrack2Gather} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTrack2Gather}.
     */
    public JsonSerializableTrack2Gather(ReadOnlyTrack2Gather source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this contacts list into the model's {@code Track2Gather} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Track2Gather toModelType() throws IllegalValueException {
        Track2Gather track2Gather = new Track2Gather();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (track2Gather.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            track2Gather.addPerson(person);
        }
        return track2Gather;
    }

}
