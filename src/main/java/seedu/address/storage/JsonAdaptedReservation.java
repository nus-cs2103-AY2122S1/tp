package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Phone;
import seedu.address.model.reservation.Reservation;

/**
 * Jackson-friendly version of {@link seedu.address.model.reservation.Reservation}
 */
public class JsonAdaptedReservation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reservation's %s field is missing";

    private final String phone;
    private final Integer numberOfPeople;
    private final String time;

    /**
     * Constructs {@code JsonAdaptedReservation with the given values}
     */
    @JsonCreator
    public JsonAdaptedReservation(
            @JsonProperty("phone") String phone, @JsonProperty("numberOfPeople") int numberOfPeople,
            @JsonProperty("time") String time
    ) {
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.time = time;
    }

    /**
     * Converts a given {@code Reservation} into this class for Jackson use
     */
    public JsonAdaptedReservation(Reservation source) {
        phone = source.getPhone().value;
        numberOfPeople = source.getNumberOfPeople();
        time = source.getDateTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted reservation object into the model's {@code Reservation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted reservation.
     */
    public Reservation toModelType() throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName())
            );
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (numberOfPeople == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "number of people")
            );
        }
        if (numberOfPeople <= 0) {
            throw new IllegalValueException("Number of people should be a non-zero unsigned integer");
        }

        if (time == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "date time")
            );
        }
        try {
            LocalDateTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException("Wrong date time format");
        }
        LocalDateTime modelTime = LocalDateTime.parse(time);

        return new Reservation(modelPhone, numberOfPeople, modelTime);
    }

}
