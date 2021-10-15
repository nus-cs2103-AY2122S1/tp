package seedu.address.storage;

import static seedu.address.model.person.Period.isValidPeriodString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Period;

/**
 * Json friendly version of {@link Period}
 */
public class JsonAdaptedPeriod {
    private final String period;

    /**
     * Constructs a {@code JsonAdaptedPeriod} with the given string source.
     */
    @JsonCreator
    public JsonAdaptedPeriod(String period) {
        this.period = period;
    }

    /**
     * Converts a given {@code Period} into this class for Jackson use.
     */
    public JsonAdaptedPeriod(Period period) {
        this.period = period.toString();
    }

    @JsonValue
    public String getPeriodName() {
        return period;
    }

    /**
     * Converts this Jackson-friendly adapted Period object into the model's {@code Period} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Period toModelType() throws IllegalValueException {
        if (!isValidPeriodString(period)) {
            throw new IllegalArgumentException(Period.MESSAGE_CONSTRAINTS);
        }
        return Period.transformStringToPeriod(period);
    }



}
