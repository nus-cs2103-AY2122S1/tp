package seedu.edrecord.storage.group;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.model.group.Group;

/**
 * Jackson-friendly version of {@link Group}.
 */
public class JsonAdaptedGroup {

    private final String code;

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given {@code code}.
     */
    @JsonCreator
    public JsonAdaptedGroup(String code) {
        this.code = code;
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        code = source.code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType() throws IllegalValueException {
        if (!Group.isValidGroup(code)) {
            throw new IllegalValueException(Group.MESSAGE_CONSTRAINTS);
        }
        return new Group(code);
    }
}
