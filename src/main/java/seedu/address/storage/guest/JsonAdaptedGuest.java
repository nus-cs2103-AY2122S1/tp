package seedu.address.storage.guest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.chargeable.Chargeable;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.RoomNumber;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAdaptedChargeable;
import seedu.address.storage.JsonAdaptedTag;

/**
 * Jackson-friendly version of {@link Guest}.
 */
class JsonAdaptedGuest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Guest's %s field is missing!";

    private final String name;
    private final String email;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String roomNumber;
    private final String passportNumber;
    private final List<JsonAdaptedChargeable> chargeablesUsed = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGuest} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedGuest(@JsonProperty("name") String name,
                            @JsonProperty("email") String email,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("roomNumber") String roomNumber,
                            @JsonProperty("passportNumber") String passportNumber,
                            @JsonProperty("chargeablesUsed") List<JsonAdaptedChargeable> chargeablesUsed) {
        this.name = name;
        this.email = email;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.roomNumber = roomNumber;
        this.passportNumber = passportNumber;
        if (chargeablesUsed != null) {
            this.chargeablesUsed.addAll(chargeablesUsed);
        }
    }

    /**
     * Converts a given {@code Guest} into this class for Jackson use.
     */
    public JsonAdaptedGuest(Guest source) {
        name = source.getName().fullName;
        email = source.getEmail().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        roomNumber = source.getRoomNumber().value;
        passportNumber = source.getPassportNumber().value;
        chargeablesUsed.addAll(source.getChargeableUsed().stream()
                .map(JsonAdaptedChargeable::new)
                .collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<JsonAdaptedTag> getTags() {
        return tagged;
    }

    public List<JsonAdaptedChargeable> getChargeablesUsed() {
        return chargeablesUsed;
    }
    
    private static boolean isStringUpperCase(String str) {
        char[] charArray = str.toCharArray();
        
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) {
                if (!Character.isUpperCase(charArray[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    // @Override
    public Guest toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : getTags()) {
            personTags.add(tag.toModelType());
        }

        final List<Chargeable> modelChargeableUsed = new ArrayList<>();
        for (JsonAdaptedChargeable chargeable : getChargeablesUsed()) {
            modelChargeableUsed.add(chargeable.toModelType());
        }

        if (getName() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(getName())) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(getName());

        if (getEmail() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(getEmail())) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(getEmail());

        if (roomNumber == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName()));
        }
        if (!RoomNumber.isValidRoomNumber(roomNumber)) {
            throw new IllegalValueException(RoomNumber.MESSAGE_CONSTRAINTS);
        }
        final RoomNumber modelRoomNumber = new RoomNumber(roomNumber);

        if (passportNumber == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, PassportNumber.class.getSimpleName()));
        }
        if (!PassportNumber.isValidPassportNumber(passportNumber) || !isStringUpperCase(passportNumber)) {
            throw new IllegalValueException(PassportNumber.MESSAGE_CONSTRAINTS);
        }
        
        final PassportNumber modelPassportNumber = new PassportNumber(passportNumber);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Guest(modelName, modelEmail, modelTags, modelRoomNumber, modelPassportNumber, modelChargeableUsed);
    }

}
