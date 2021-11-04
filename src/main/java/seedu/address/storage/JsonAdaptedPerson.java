package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pin;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing.";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedPerson.class);
    private static final String INVALID_BIRTHDAY_MESSAGE = "%s's birthday %s is invalid. "
            + "Will start with empty birthday.";
    private static final String INVALID_PIN_MESSAGE = "%s's pin status %s is invalid. "
            + "Will start with not pinned by default.";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String birthday;
    private final String pin;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("birthday") String birthday, @JsonProperty("pin") String pin) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.birthday = birthday;
        this.pin = pin;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        birthday = source.getBirthday().map(Birthday::toString).orElse(null);
        pin = source.getPin().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            Tag currentTag = tag.toModelType();
            personTags.add(currentTag);
        }

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

        final Pin modelPin;

        if (pin == null) {
            logger.info(String.format(INVALID_PIN_MESSAGE, name, "null"));
            modelPin = new Pin(false);
            //throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Pin.class.getSimpleName()));
        } else if (!Pin.isValidPinStatus(pin)) {
            logger.info(String.format(INVALID_PIN_MESSAGE, name, pin));
            modelPin = new Pin(false);
            //throw new IllegalValueException(String.format(Pin.MESSAGE_CONSTRAINTS));
        } else {
            modelPin = new Pin(pin);
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (birthday == null) {
            return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags, null, modelPin);
        }

        final Birthday modelBirthday;
        if (!Birthday.isValidFormat(birthday)) {
            logger.info(String.format(INVALID_BIRTHDAY_MESSAGE, name, birthday));
            modelBirthday = null;
        } else if (!Birthday.isValidDate(birthday)) {
            logger.info(String.format(INVALID_BIRTHDAY_MESSAGE, name, birthday));
            modelBirthday = null;
        } else {
            modelBirthday = new Birthday(birthday);
        }

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelBirthday, modelPin);
    }

}
