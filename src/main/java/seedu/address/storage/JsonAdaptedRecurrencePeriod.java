package seedu.address.storage;

import static seedu.address.commons.util.DateTimeUtil.TIME_FORMATTER;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.RecurrencePeriod;

/**
 * Class representing the {@code RecurrencePeriod} object adapted for Json.
 */
public class JsonAdaptedRecurrencePeriod {
    private final JsonAdaptedPeriod period;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedRecurrencePeriod} with the given RecurrencePeriod details.
     */
    @JsonCreator
    public JsonAdaptedRecurrencePeriod(@JsonProperty("period") JsonAdaptedPeriod period,
                                       @JsonProperty("startTime") String startTime,
                                       @JsonProperty("endTime") String endTime) {
        this.period = period;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a {@code JsonAdaptedRecurrencePeriod} with the given RecurrencePeriod.
     *
     */
    public JsonAdaptedRecurrencePeriod(RecurrencePeriod period) {
        this.period = new JsonAdaptedPeriod(period.getPeriod());
        this.startTime = period.getStartTime().toString();
        this.endTime = period.getEndTime().toString();
    }

    /**
     * Creates {@code RecurrencePeriod} that this is representing and
     * returns it.
     */
    public RecurrencePeriod toModelType() throws IllegalValueException {
        try {
            LocalTime modelStartTime = LocalTime.parse(startTime, TIME_FORMATTER);
            LocalTime modelEndTime = LocalTime.parse(endTime, TIME_FORMATTER);
            return new RecurrencePeriod(period.toModelType(), modelStartTime, modelEndTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

    }


}
