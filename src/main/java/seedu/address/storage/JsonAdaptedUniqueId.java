package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.id.UniqueId;

/**
 * Jackson-friendly version of {@link UniqueId}.
 */
public class JsonAdaptedUniqueId {

    private final String id;

    /**
     * Constructs a {@code JsonAdaptedUniqueId} with the given {@code id}.
     */
    @JsonCreator
    public JsonAdaptedUniqueId(String id) {
        this.id = id;
    }

    /**
     * Converts a given {@code UniqueId} into this class for Jackson use.
     */
    public JsonAdaptedUniqueId(UniqueId source) {
        id = source.getUuid().toString();
    }

    @JsonValue
    public String getId() {
        return id;
    }

    /**
     * Converts this Jackson-friendly adapted id object into the model's {@code UniqueId} object.
     */
    public UniqueId toModelType() {
        return UniqueId.generateId(id);
    }

}
