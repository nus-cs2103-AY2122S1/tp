package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Internship;
import seedu.address.model.ReadOnlyInternship;
import seedu.address.model.application.Application;

/**
 * An Immutable Internship that is serializable to JSON format.
 */
@JsonRootName(value = "internship")
class JsonSerializableInternship {

    public static final String MESSAGE_DUPLICATE_APPLICATION = "Applications list contains duplicate application(s).";

    private final List<JsonAdaptedApplication> applications = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInternship} with the given applications.
     */
    @JsonCreator
    public JsonSerializableInternship(@JsonProperty("applications") List<JsonAdaptedApplication> applications) {
        this.applications.addAll(applications);
    }

    /**
     * Converts a given {@code ReadOnlyInternship} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInternship}.
     */
    public JsonSerializableInternship(ReadOnlyInternship source) {
        applications.addAll(source.getApplicationList().stream().map(JsonAdaptedApplication::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Internship into the model's {@code Internship} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Internship toModelType() throws IllegalValueException {
        Internship internship = new Internship();
        for (JsonAdaptedApplication jsonAdaptedApplication : applications) {
            Application application = jsonAdaptedApplication.toModelType();
            if (internship.hasApplication(application)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICATION);
            }
            internship.addApplication(application);
        }
        return internship;
    }

}
