package seedu.plannermd.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.patient.Risk;
import seedu.plannermd.model.person.Person;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient extends JsonAdaptedPerson {

    private final String risk;

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("birthDate") String birthDate, @JsonProperty("remark") String remark,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("risk") String risk) {
        super(name, phone, email, address, birthDate, remark, tagged);
        this.risk = risk;
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        super(source);
        risk = source.getRisk().toString();
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's
     * {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted patient.
     */
    @Override
    public Patient toModelType() throws IllegalValueException {
        Person person = super.toModelType();

        if (risk == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Risk.class.getSimpleName()));
        }
        if (!Risk.isValidUnclassifiableRisk(risk)) {
            throw new IllegalValueException(Risk.MESSAGE_CONSTRAINTS);
        }
        final Risk modelRisk = Risk.getUnclassifiableRisk(risk);

        return new Patient(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(),
                person.getBirthDate(), person.getRemark(), person.getTags(), modelRisk);
    }

}
