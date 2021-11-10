package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Countdown;
import seedu.address.model.ReadOnlyCountdown;

/**
 * An Immutable Countdown that is serializable to JSON format.
 */
@JsonRootName(value = "countdown")
class JsonSerializableCountdown {

    private final JsonAdaptedDate date;

    /**
     * Constructs a {@code JsonSerializableCountdown} with the given date.
     */
    @JsonCreator
    public JsonSerializableCountdown(@JsonProperty("date") JsonAdaptedDate date) {
        this.date = date;
    }

    /**
     * Converts a given {@code ReadOnlyCountdown} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCountdown}.
     */
    public JsonSerializableCountdown(ReadOnlyCountdown source) {
        LocalDate date = source.getDate();
        this.date = new JsonAdaptedDate(date == null ? LocalDate.now() : date);
    }

    /**
     * Converts this countdown into the model's {@code Countdown} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Countdown toModelType() throws IllegalValueException {
        Countdown countdown = new Countdown();
        countdown.setDate(date.toModelType());
        return countdown;
    }

}
