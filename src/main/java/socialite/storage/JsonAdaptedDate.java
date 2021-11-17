package socialite.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import socialite.commons.exceptions.IllegalValueException;
import socialite.model.person.Date;

/**
 * Jackson-friendly version of {@link Date}.
 */
class JsonAdaptedDate {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final String name;
    private final LocalDate date;
    private final String recurrenceInterval;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedDate(@JsonProperty("name") String name, @JsonProperty("date") LocalDate date,
                           @JsonProperty("recurrenceInterval") String recurrenceInterval) {
        this.name = name;
        this.date = date;
        this.recurrenceInterval = recurrenceInterval;
    }

    /**
     * Converts a given {@code Date} into this class for Jackson use.
     */
    public JsonAdaptedDate(Date source) {
        name = source.getName();
        date = source.getDate();
        recurrenceInterval = source.getRecurrenceInterval().orElse(null);
    }

    /**
     * Converts this Jackson-friendly adapted date object into the model's {@code Date} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Date.
     */
    public Date toModelType() throws IllegalValueException {
        if (name == null || date == null) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        String constructedDate = name + ":" + DATE_FORMATTER.format(date)
                + Optional.ofNullable(recurrenceInterval).map(interval -> ":" + interval).orElse("");
        if (!Date.isValidDate(constructedDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(constructedDate);
    }

}
