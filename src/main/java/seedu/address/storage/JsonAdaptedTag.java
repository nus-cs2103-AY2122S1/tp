package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.LessonCode;

/**
 * Jackson-friendly version of {@link LessonCode}.
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
     * Converts a given {@code LessonCode} into this class for Jackson use.
     */
    public JsonAdaptedTag(LessonCode source) {
        tagName = source.lessonCode;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code LessonCode} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public LessonCode toModelType() throws IllegalValueException {
        if (!LessonCode.isValidLessonCode(tagName)) {
            throw new IllegalValueException(LessonCode.MESSAGE_CONSTRAINTS);
        }
        return new LessonCode(tagName);
    }

}
