package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.particpant.BirthDate;
import seedu.address.model.particpant.Note;
import seedu.address.model.particpant.Participant;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class JsonAdaptedParticipant {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Participant's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    //Add on for Managera Participants
    private final String birthDate;
    private final List<JsonAdaptedPerson> nextOfKins = new ArrayList<>();
    // TODO: JsonAdaptedNotes (To be implemented)

    /**
     * Constructs a {@code JsonAdaptedParticipant} with the given participant details.
     */
    @JsonCreator
    public JsonAdaptedParticipant(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("birthDate") String birthDate,
                             @JsonProperty("nextOfKins") List<JsonAdaptedPerson> nextOfKins) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.birthDate = birthDate;
        if (nextOfKins != null) {
            this.nextOfKins.addAll(nextOfKins);
        }
        // TODO: Notes to be implemented
    }

    /**
     * Converts a given {@code Participant} into this class for Json use.
     */
    public JsonAdaptedParticipant(Participant source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        birthDate = source.getBirthDate().toString();
        nextOfKins.addAll(source.getNextOfKins().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
        // TODO: Notes to be implemented
    }

    /**
     * Converts this Jackson-friendly adapted participant object into the model's {@code Participant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participant.
     */
    public Participant toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
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

        final Set<Tag> modelTags = new HashSet<>(personTags);

        BirthDate birthDate;
        if (!BirthDate.isValidBirthDate(this.birthDate)) {
            // throw new IllegalValueException(BirthDate.MESSAGE_DATE_CONSTRAINTS);
            // Can't do this due to BirthDate implementation
            birthDate = BirthDate.notSpecified();
        } else {
            birthDate = BirthDate.of(LocalDate.parse(this.birthDate));
        }

        // TODO: set Notes
        Set<Note> notes = new HashSet<>();

        final ArrayList<Person> nextOfKins = new ArrayList<>();
        for (JsonAdaptedPerson p : this.nextOfKins) {
            nextOfKins.add(p.toModelType());
        }

        return new Participant(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                birthDate, notes, nextOfKins);
    }
}
