package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;

/**
 * Jackson-friendly version of {@link Client}.
 */
class JsonAdaptedClient {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Client's %s field is missing!";

    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String address;

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedClient(@JsonProperty("name") String name, @JsonProperty("phoneNumber") String phoneNumber,
                             @JsonProperty("email") String email, @JsonProperty("address") String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    /**
     * Converts a given {@code Client} into this class for Jackson use.
     */
    public JsonAdaptedClient(Client source) {
        name = source.getName().fullName;
        phoneNumber = source.getPhoneNumber().value;

        email = isNull(source.getEmail()) ? null : source.getEmail().value;
        address = isNull(source.getAddress()) ? null : source.getAddress().value;
    }

    private <T> boolean isNull(T obj) {
        return obj == null;
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Client} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Client toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phoneNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PhoneNumber.class.getSimpleName()));
        }
        if (!PhoneNumber.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalValueException(PhoneNumber.MESSAGE_CONSTRAINTS);
        }
        final PhoneNumber modelPhoneNumber = new PhoneNumber(phoneNumber);

        final Email modelEmail;
        if (email == null) {
            modelEmail = null;
        } else if (Email.isValidEmail(email)) {
            modelEmail = new Email(email);
        } else {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }

        final Address modelAddress;
        if (address == null) {
            modelAddress = null;
        } else if (Address.isValidAddress(address)) {
            modelAddress = new Address(address);
        } else {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }

        return new Client(modelName, modelPhoneNumber, modelEmail, modelAddress);
    }
}
