package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.LastUpdatedDate;

/**
 * Jackson-friendly version of (@link OutstandingFees}
 */
public class JsonAdaptedLastUpdated {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Last Updated Date is missing!";
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedFees} with the given Outstanding Fees details.
     */
    @JsonCreator
    public JsonAdaptedLastUpdated(@JsonProperty("date") String lastUpdated) {
        this.date = lastUpdated;
    }

    /**
     * Converts a given {@code OutstandingFees} into this class for Json use.
     */
    public JsonAdaptedLastUpdated(LastUpdatedDate source) {
        date = source.getLastUpdatedDate().value;
    }

    /**
     * Converts this Jackson-friendly adapted outstanding fee object into the model's {@code OutstandingFees} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adpated Outstanding Fees.
     */
    public LastUpdatedDate toModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LastUpdatedDate.class.getSimpleName()));
        }
        if (!LastUpdatedDate.isValidLastUpdatedDate(date)) {
            throw new IllegalValueException(LastUpdatedDate.MESSAGE_CONSTRAINTS);
        }

        return new LastUpdatedDate(date);
    }
}
