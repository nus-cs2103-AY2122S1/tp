package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.interaction.Interaction;

/**
 * Jackson-friendly version of {@link Interaction}.
 */
class JsonAdaptedInteractions {

    public final String description;
    public final LocalDate date;
    /**
     * Constructs a {@code JsonAdaptedInteraction} with the given
     * {@code description} and {@code date}.
     */
    @JsonCreator
    public JsonAdaptedInteractions(@JsonProperty("description") String description,
                                    @JsonProperty("date") LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedInteractions(Interaction source) {
        this.description = source.description;
        this.date = source.date;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Interaction toModelType() {
        return new Interaction(description, date.toString());
    }
}
