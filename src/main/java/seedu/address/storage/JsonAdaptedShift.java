package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.employee.Shift;

/**
 * Jackson-friendly version of {@link Shift}.
 */
class JsonAdaptedShift {

    private final String shift;

    /**
     * Constructs a {@code JsonAdaptedShift} with the given {@code shift}.
     */
    @JsonCreator
    public JsonAdaptedShift(String shift) {
        this.shift = shift;
    }

    /**
     * Converts a given {@code shift} into this class for Jackson use.
     */
    public JsonAdaptedShift(Shift source) {
        shift = source.shiftString;
    }

    @JsonValue
    public String getShift() {
        return shift;
    }

    /**
     * Converts this Jackson-friendly adapted shift object into the model's {@code shift} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted shift.
     */
    public Shift toModelType() throws IllegalValueException {
        if (!Shift.isValidShift(shift)) {
            throw new IllegalValueException(Shift.MESSAGE_CONSTRAINTS);
        }
        return new Shift(shift);
    }

}
