package seedu.placebook.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.placebook.commons.exceptions.IllegalValueException;
import seedu.placebook.model.schedule.TimePeriod;

public class JsonAdaptedTimePeriod {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "TimePeriod's %s field is missing!";

    private final String startDateTime;
    private final String endDateTime;

    /**
     * Constructs a {@code JsonAdaptedTimePeriod} with the given start and end DateTime.
     */
    @JsonCreator
    public JsonAdaptedTimePeriod(@JsonProperty("startDateTime") String startDateTime,
             @JsonProperty("endDateTime") String endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Converts a given {@code TimePeriod} into this class for Jackson use.
     */
    public JsonAdaptedTimePeriod(TimePeriod source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.startDateTime = source.getStartDateTime().format(formatter);
        this.endDateTime = source.getEndDateTime().format(formatter);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code TimePeriod} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted TimePeriod.
     */
    public TimePeriod toModelType() throws IllegalValueException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        if (startDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        final LocalDateTime modelStartDateTime = LocalDateTime.parse(startDateTime, formatter);

        if (endDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        final LocalDateTime modelEndDateTime = LocalDateTime.parse(endDateTime, formatter);

        return new TimePeriod(modelStartDateTime, modelEndDateTime);
    }
}
