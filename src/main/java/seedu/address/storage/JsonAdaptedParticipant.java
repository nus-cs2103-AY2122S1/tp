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
import seedu.address.model.participant.Address;
import seedu.address.model.participant.BirthDate;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Note;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;
import seedu.address.model.tag.Tag;

public class JsonAdaptedParticipant {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Participant's %s field is missing!";

    private final String id;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    //Add on for Managera Participants
    private final String birthDate;
    private final List<JsonAdaptedNote> notes = new ArrayList<>();
    private final List<JsonAdaptedNextOfKin> nextOfKins = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedParticipant} with the given participant details.
     */
    @JsonCreator
    public JsonAdaptedParticipant(@JsonProperty("id") String id, @JsonProperty("name") String name,
                                  @JsonProperty("phone") String phone,
                                  @JsonProperty("email") String email, @JsonProperty("address") String address,
                                  @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                  @JsonProperty("birthDate") String birthDate,
                                  @JsonProperty("notes") List<JsonAdaptedNote> notes,
                                  @JsonProperty("nextOfKin") List<JsonAdaptedNextOfKin> nextOfKins) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.birthDate = birthDate;
        if (notes != null) {
            this.notes.addAll(notes);
        }
        if (nextOfKins != null) {
            this.nextOfKins.addAll(nextOfKins);
        }
    }

    public String getId() {
        return id;
    }

    /**
     * Converts a given {@code Participant} into this class for Json use.
     */
    public JsonAdaptedParticipant(Participant source) {
        id = source.getParticipantIdValue();
        name = source.getFullName();
        phone = source.getPhoneValue();
        email = source.getEmailValue();
        address = source.getAddressValue();
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
        birthDate = source.getBirthDateString();
        notes.addAll(source.getNotes().stream().map(JsonAdaptedNote::new).collect(Collectors.toList()));
        nextOfKins.addAll(source.getNextOfKins().stream().map(JsonAdaptedNextOfKin::new).collect(Collectors.toList()));
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

        BirthDate modelBirthDate;
        if (!BirthDate.isValidBirthDate(this.birthDate)) {
            // throw new IllegalValueException(BirthDate.MESSAGE_DATE_CONSTRAINTS);
            // Can't do this due to BirthDate implementation
            modelBirthDate = BirthDate.notSpecified();
        } else {
            modelBirthDate = BirthDate.of(LocalDate.parse(this.birthDate));
        }

        final List<Note> personNotes = new ArrayList<>();
        for (JsonAdaptedNote note : notes) {
            personNotes.add(note.toModelType());
        }

        Set<Note> notes = new HashSet<>(personNotes);

        ArrayList<NextOfKin> modelNextOfKins = new ArrayList<>();
        for (JsonAdaptedNextOfKin nok : nextOfKins) {
            modelNextOfKins.add(nok.toModelType());
        }

        return new Participant(modelName, modelPhone, modelEmail, modelAddress, modelTags,
            modelBirthDate, notes, modelNextOfKins);
    }
}
