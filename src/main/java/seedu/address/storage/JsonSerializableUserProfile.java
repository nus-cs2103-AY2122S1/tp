package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;

@JsonRootName(value = "userprofile")
public class JsonSerializableUserProfile {

    private JsonAdaptedPerson person;

    @JsonCreator
    public JsonSerializableUserProfile(@JsonProperty("profile") JsonAdaptedPerson person) {
        this.person = person;
    }

    /**
     * Converts this user profile into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Person toModelType() throws IllegalValueException {
        return person.toModelType();
    }

}
