package safeforhall.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import safeforhall.commons.exceptions.IllegalValueException;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String room;
    private final String phone;
    private final String email;
    private final String vaccStatus;
    private final String faculty;
    private final String lastFetDate;
    private final String lastCollectionDate;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("room") String room,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("vaccStatus") String vaccStatus,
                             @JsonProperty("faculty") String faculty,
                             @JsonProperty("lastFetDate") String lastFetDate,
                             @JsonProperty("lastCollectionDate") String lastCollectionDate) {
        this.name = name;
        this.room = room;
        this.phone = phone;
        this.email = email;
        this.vaccStatus = vaccStatus;
        this.faculty = faculty;
        this.lastFetDate = lastFetDate;
        this.lastCollectionDate = lastCollectionDate;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        room = source.getRoom().room;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        vaccStatus = source.getVaccStatus().vaccStatus;
        faculty = source.getFaculty().faculty;
        lastFetDate = source.getLastFetDate().date;
        lastCollectionDate = source.getLastCollectionDate().date;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        // Name
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        // Room
        if (room == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Room.class.getSimpleName()));
        }
        if (!Room.isValidRoom(room)) {
            throw new IllegalValueException(Room.MESSAGE_CONSTRAINTS);
        }
        final Room modelRoom = new Room(room);

        // Phone
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        // Email
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        // VaccStatus
        if (vaccStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    VaccStatus.class.getSimpleName()));
        }
        if (!VaccStatus.isValidVaccStatus(vaccStatus)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final VaccStatus modelVaccStatus = new VaccStatus(vaccStatus);

        // Faculty
        if (!Faculty.isValidFaculty(faculty)) {
            throw new IllegalValueException(Faculty.MESSAGE_CONSTRAINTS);
        }
        final Faculty modelFaculty = new Faculty(faculty);

        // LastFetDate
        if (!LastDate.isValidDate(lastFetDate)) {
            throw new IllegalValueException(LastDate.MESSAGE_CONSTRAINTS);
        }
        final LastDate modelFetDate = new LastDate(lastFetDate);

        // LastCollectionDate
        if (!LastDate.isValidDate(lastCollectionDate)) {
            throw new IllegalValueException(LastDate.MESSAGE_CONSTRAINTS);
        }
        final LastDate modelCollectionDate = new LastDate(lastCollectionDate);

        return new Person(modelName, modelRoom, modelPhone, modelEmail,
                modelVaccStatus, modelFaculty, modelFetDate, modelCollectionDate);
    }

}
