package seedu.notor.storage;

import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.notor.commons.exceptions.IllegalValueException;
import seedu.notor.model.common.Name;
import seedu.notor.model.group.SubGroup;

public class JsonAdaptedSubGroup {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's format is wrong!";

    private final String name;

    private String parent;

    /**
     * Constructs a {@code JsonAdaptedSuperGroup} with the given subGroup details.
     */
    @JsonCreator
    public JsonAdaptedSubGroup(@JsonProperty("name") String name,
            @JsonProperty("Group") String parent) {
        this.name = name;
        this.parent = parent;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedSubGroup(SubGroup source) {
        this.name = source.getName();
        this.parent = source.getParent();
    }

    /**
     * Converts this Jackson-friendly adapted SuperGroup object into the model's {@code SuperGroup}
     * and {@code SuperGroup} objects.
     * TODO: take in actual tags instead of just using empty HashSet.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public SubGroup toModelType() throws IllegalValueException {
        return new SubGroup(new Name(name), new HashSet<>(), parent);
    }
}
