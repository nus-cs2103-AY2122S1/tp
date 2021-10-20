package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TodayAttendance;
import seedu.address.model.person.TotalAttendance;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String availability;
    private final Boolean todayAttendance;
    private final Integer totalAttendance;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("availability") String availability, @JsonProperty("todayAttendance") Boolean todayAttendance,
                             @JsonProperty("totalAttendance") Integer totalAttendance) {
        this.name = name;
        this.phone = phone;
        this.availability = availability;
        this.todayAttendance = todayAttendance;
        this.totalAttendance = totalAttendance;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        availability = source.getAvailability().values;
        todayAttendance = source.getTodayAttendance().getAttendance();
        totalAttendance = source.getTotalAttendance().getAttendance();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
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

        if (availability == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Availability.class.getSimpleName()));
        }
        final Availability modelAvailability = new Availability(availability);

        if (todayAttendance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TodayAttendance.class.getSimpleName()));
        }
        final TodayAttendance modelTodayAttendance = new TodayAttendance(todayAttendance);

        if (totalAttendance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TotalAttendance.class.getSimpleName()));
        }

        final TotalAttendance modelTotalAttendance = new TotalAttendance(totalAttendance);

        return new Person(modelName, modelPhone, modelAvailability, modelTodayAttendance, modelTotalAttendance);
    }
}
