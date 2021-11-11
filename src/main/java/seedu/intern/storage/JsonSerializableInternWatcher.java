package seedu.intern.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.intern.commons.exceptions.IllegalValueException;
import seedu.intern.model.InternWatcher;
import seedu.intern.model.ReadOnlyInternWatcher;
import seedu.intern.model.applicant.Applicant;

/**
 * An Immutable InternWatcher that is serializable to JSON format.
 */
@JsonRootName(value = "internwatcher")
class JsonSerializableInternWatcher {

    public static final String MESSAGE_DUPLICATE_APPLICANT = "Applicants list contains duplicate applicant(s).";

    private final List<JsonAdaptedApplicant> applicants = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInternWatcher} with the given applicants.
     */
    @JsonCreator
    public JsonSerializableInternWatcher(@JsonProperty("applicants") List<JsonAdaptedApplicant> applicants) {
        this.applicants.addAll(applicants);
    }

    /**
     * Converts a given {@code ReadOnlyInternWatcher} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInternWatcher}.
     */
    public JsonSerializableInternWatcher(ReadOnlyInternWatcher source) {
        applicants.addAll(source.getApplicantList().stream().map(JsonAdaptedApplicant::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this intern watcher into the model's {@code InternWatcher} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InternWatcher toModelType() throws IllegalValueException {
        InternWatcher internWatcher = new InternWatcher();
        for (JsonAdaptedApplicant jsonAdaptedApplicant : applicants) {
            Applicant applicant = jsonAdaptedApplicant.toModelType();
            if (internWatcher.hasApplicant(applicant)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPLICANT);
            }
            internWatcher.addApplicant(applicant);
        }
        return internWatcher;
    }

}
