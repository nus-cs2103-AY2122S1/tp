package seedu.plannermd.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.person.Person;

/**
 * Jackson-friendly version of {@link Doctor}.
 */
class JsonAdaptedDoctor extends JsonAdaptedPerson {


    /**
     * Constructs a {@code JsonAdaptedDoctor} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedDoctor(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("birthDate") String birthDate, @JsonProperty("remark") String remark,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(name, phone, email, address, birthDate, remark, tagged);
    }

    /**
     * Converts a given {@code Doctor} into this class for Jackson use.
     */
    public JsonAdaptedDoctor(Doctor source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted doctor object into the model's
     * {@code Doctor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted doctor.
     */
    @Override
    public Doctor toModelType() throws IllegalValueException {
        Person person = super.toModelType();

        return new Doctor(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(),
                person.getBirthDate(), person.getRemark(), person.getTags());
    }

}
