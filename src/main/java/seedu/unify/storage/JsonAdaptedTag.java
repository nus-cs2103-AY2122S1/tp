package seedu.unify.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.unify.commons.exceptions.IllegalValueException;
import seedu.unify.model.task.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    private final String tagTaskName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagTaskName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagTaskName) {
        this.tagTaskName = tagTaskName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        tagTaskName = source.tagTaskName;
    }

    @JsonValue
    public String getTagTaskName() {
        return tagTaskName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagTaskName(tagTaskName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(tagTaskName);
    }

}
