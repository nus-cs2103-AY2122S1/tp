package seedu.modulink.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.modulink.commons.exceptions.IllegalValueException;
import seedu.modulink.model.tag.Mod;

/**
 * Jackson-friendly version of {@link Mod}.
 */
class JsonAdaptedTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Mod source) {
        tagName = source.oriInput;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Mod toModelType() throws IllegalValueException {
        if (!Mod.isValidTagName(tagName)) {
            throw new IllegalValueException(Mod.MESSAGE_CONSTRAINTS);
        }
        return new Mod(tagName);
    }

}
