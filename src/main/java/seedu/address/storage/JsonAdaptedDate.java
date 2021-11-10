package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Jackson-friendly version of {@link LocalDate}.
 */
class JsonAdaptedDate {

    private final int year;
    private final Month month;
    private final int day;

    /**
     * Constructs a {@code JsonAdaptedDate} with the given date details.
     */
    @JsonCreator
    public JsonAdaptedDate(@JsonProperty("year") int year, @JsonProperty("month") Month month,
                             @JsonProperty("day") int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Converts a given {@code Date} into this class for Jackson use.
     */
    public JsonAdaptedDate(LocalDate source) {
        requireNonNull(source);
        year = source.getYear();
        month = source.getMonth();
        day = source.getDayOfMonth();
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's {@code LocalDate} object.
     */
    public LocalDate toModelType() throws IllegalValueException {
        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }

}
