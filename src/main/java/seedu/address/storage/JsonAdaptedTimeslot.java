package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Timeslot;

/**
 * Jackson-friendly version of {@link Timeslot}.
 */
public class JsonAdaptedTimeslot {

    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedTimeslot} with the given timeslot details.
     */
    @JsonCreator
    public JsonAdaptedTimeslot(@JsonProperty("startTime") String startTime, @JsonProperty("endTime") String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Timeslot} into this class for Jackson use.
     */
    public JsonAdaptedTimeslot(Timeslot source) {
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted timeslot object into the model's {@code Timeslot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted timeslot.
     */
    public Timeslot toModelType() throws IllegalValueException {
        if (startTime == null || endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Timeslot.class.getSimpleName()));
        }

        if (!Timeslot.isValidTimeslot(startTime, endTime)) {
            throw new IllegalValueException(Timeslot.MESSAGE_CONSTRAINTS);
        }
        return new Timeslot(startTime, endTime);
    }
}
