package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.nextOfKin.NextOfKin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class JsonAdaptedNextOfKin {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Next of Kin's %s field is missing!";

    private final String name;
    private final String phone;
    private final JsonAdaptedTag tag;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedNextOfKin(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("tag") JsonAdaptedTag tag) {
        this.name = name;
        this.phone = phone;
        this.tag = tag;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedNextOfKin(NextOfKin source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        tag = new JsonAdaptedTag(source.getTag());
    }

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
