package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.OutstandingFees;
import seedu.address.model.lesson.OutstandingFees.LastAddedDate;

/**
 * Jackson-friendly version of (@link OutstandingFees}
 */
public class JsonAdaptedFees {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Outstanding Fee's %s field is missing!";

    private final String value;
    private final String lastAdded;

    /**
     * Constructs a {@code JsonAdaptedFees} with the given Outstanding Fees details.
     */
    @JsonCreator
    public JsonAdaptedFees(@JsonProperty("value") String value,
                           @JsonProperty("lastAdded") String lastAdded) {
        this.value = value;
        this.lastAdded = lastAdded;
    }

    /**
     * Converts a given {@code OutstandingFees} into this class for Json use.
     */
    public JsonAdaptedFees(OutstandingFees source) {
        value = source.value;
        lastAdded = source.getLastAdded().lastAdded;

    }

    /**
     * Converts this Jackson-friendly adapted outstanding fee object into the model's {@code OutstandingFees} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adpated Outstanding Fees.
     */
    public OutstandingFees toModelType() throws IllegalValueException {
        if (value == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "value"));
        }
        if (!OutstandingFees.isValidOutstandingFee(value)) {
            throw new IllegalValueException(OutstandingFees.MESSAGE_CONSTRAINTS);
        }
        final String modelValue = new String(value);

        if (lastAdded == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LastAddedDate.class.getSimpleName()));
        }
        if (!LastAddedDate.isValidLastAddedDate(lastAdded)) {
            throw new IllegalValueException(LastAddedDate.MESSAGE_CONSTRAINTS);
        }
        final LastAddedDate modelLastAdded = new LastAddedDate(lastAdded);

        return new OutstandingFees(modelValue, modelLastAdded);
    }
}
