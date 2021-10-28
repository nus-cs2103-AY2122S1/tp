package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.organisation.Organisation;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;

public class JsonAdaptedOrganisation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Organisation's %s field is missing";
    private final String name;
    private final String email;
    private final List<JsonAdaptedPerson> personList = new ArrayList<>();
    /**
     * Constructs a {@code JsonAdaptedOrganisation} with the given organisation details.
     */
    @JsonCreator
    public JsonAdaptedOrganisation(@JsonProperty("name") String name, @JsonProperty("email") String email,
                                   @JsonProperty("personList") List<JsonAdaptedPerson> personList) {
        this.name = name;
        this.email = email;
        if (personList != null) {
            this.personList.addAll(personList);
        }
    }

    /**
     * Constructs a {@code JsonAdaptedOrganisation} with the given organisation details.
     */
    public JsonAdaptedOrganisation(Organisation source) {
        name = source.getName().fullName;
        email = source.getEmail().value;
        personList.addAll(source.getPersons().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts a given {@code Organisation} into this class for Jackson use.
     */
    public Organisation toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);
        Organisation organisation = new Organisation(modelName, modelEmail);
        for (JsonAdaptedPerson person : personList) {
            organisation.addPerson(person.toModelType());
        }
        return organisation;
    }
}
