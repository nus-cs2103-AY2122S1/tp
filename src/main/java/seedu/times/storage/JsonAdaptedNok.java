package seedu.times.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.times.commons.exceptions.IllegalValueException;
import seedu.times.model.person.Address;
import seedu.times.model.person.Email;
import seedu.times.model.person.Name;
import seedu.times.model.person.Nok;
import seedu.times.model.person.Phone;

/**
 * Jackson-friendly version of {@link Nok}.
 */
class JsonAdaptedNok {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Nok's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;

    /**
     * Constructs a {@code JsonAdaptedNok} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedNok(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                          @JsonProperty("email") String email, @JsonProperty("address") String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Converts a given {@code Nok} into this class for Jackson use.
     */
    public JsonAdaptedNok(Nok source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Nok} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Nok toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        return new Nok(modelName, modelPhone, modelEmail, modelAddress);
    }
}
