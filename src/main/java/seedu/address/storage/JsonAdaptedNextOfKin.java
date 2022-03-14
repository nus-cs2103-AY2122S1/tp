package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Phone;
import seedu.address.model.tag.Tag;

public class JsonAdaptedNextOfKin {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Next of Kin's %s field is missing!";

    private final String name;
    private final String phone;
    private final JsonAdaptedTag tag;

    /**
     * Constructs a {@code JsonAdaptedNextOfKin} with the given NextOfKin details.
     */
    @JsonCreator
    public JsonAdaptedNextOfKin(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                @JsonProperty("tag") JsonAdaptedTag tag) {
        this.name = name;
        this.phone = phone;
        this.tag = tag;
    }

    /**
     * Converts a given {@code NextOfKin} into this class for Jackson use.
     */
    public JsonAdaptedNextOfKin(NextOfKin source) {
        name = source.getFullName();
        phone = source.getPhoneValue();
        tag = new JsonAdaptedTag(source.getTag());
    }

    /**
     * Convert this Json representation of NextOfKin to NextOfKin object.
     * @return  NextOfKin object of this JsonAdaptedNextOfKin
     * @throws IllegalValueException if the given attributes in json fields is illegal
     */
    public NextOfKin toModelType() throws IllegalValueException {
        final Tag tag = this.tag.toModelType();

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

        return new NextOfKin(modelName, modelPhone, tag);
    }
}
