package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.position.Title;

public class JsonAdaptedTitle {
    private final String title;

    /**
     * Constructs a {@code JsonAdaptedTitle} with the given {@code title}.
     */
    @JsonCreator
    public JsonAdaptedTitle(String title) {
        this.title = title;
    }

    /**
     * Converts a given {@code JsonAdaptedTitle} into this class for Jackson use.
     */
    public JsonAdaptedTitle(Title source) {
        title = source.fullTitle;
    }

    @JsonValue
    public String getTitle() {
        return title;
    }

    /**
     * Converts this Jackson-friendly adapted title object into the model's {@code Title} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted title.
     */
    public Title toModelType() throws IllegalValueException {
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(title);
    }
}
