package seedu.address.storage;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.SubGroup;
import seedu.address.model.group.SuperGroup;

public class JsonAdaptedSuperGroup {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's format is wrong!";

    private final String name;

    private ArrayList<String> subGroups;

    /**
     * Constructs a {@code JsonAdaptedSuperGroup} with the given group details.
     */
    @JsonCreator
    public JsonAdaptedSuperGroup(@JsonProperty("name") String name,
        @JsonProperty("subGroups") ArrayList<String> subGroups) {
        this.name = name;
        this.subGroups = subGroups;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedSuperGroup(SuperGroup source) {
        this.name = source.getName();
        this.subGroups = source.getSubGroups();
    }

    /**
     * Converts this Jackson-friendly adapted SuperGroup object into the model's {@code SuperGroup}
     * and {@code SuperGroup} objects.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public SuperGroup toModelType() throws IllegalValueException {
        SuperGroup group = new SuperGroup(name);
        for (String sg : this.subGroups) {
            group.addSubGroup(new SubGroup(sg, group.getName()));
        }
        return group;
    }
}
