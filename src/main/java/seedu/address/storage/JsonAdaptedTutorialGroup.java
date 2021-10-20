package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.ClassCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialgroup.GroupName;
import seedu.address.model.tutorialgroup.GroupType;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class JsonAdaptedTutorialGroup {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial Group's %s field is missing!";

    private final String groupName;
    private final String classCode;
    private final String groupType;

    /**
     * Constructs a {@code JsonAdaptedTutorialClass} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedTutorialGroup(@JsonProperty("groupName") String groupName,
                                    @JsonProperty("classCode") String classCode,
                                    @JsonProperty("groupType") String groupType) {

        this.groupName = groupName;
        this.classCode = classCode;
        this.groupType = groupType;
    }

    /**
     * Converts a given {@code TutorialGroup} into this class for Jackson use.
     */
    public JsonAdaptedTutorialGroup(TutorialGroup source) {
        groupName = source.getGroupName().toString();
        classCode = source.getClassCode().toString();
        groupType = source.getGroupType().toString();
    }

    /**
     * Converts this Jackson-friendly adapted tutorial group object into the model's {@code TutorialGroup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public TutorialGroup toModelType() throws IllegalValueException {

        if (classCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }
        if (!Tag.isValidTagName(classCode)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        final ClassCode modelClassCode = new ClassCode(classCode);

        if (groupName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupName.class.getSimpleName())
            );
        }

        final GroupName modelGroupName = new GroupName(groupName);

        if (groupType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }

        final GroupType modelGroupType = new GroupType(groupType);

        return new TutorialGroup(modelGroupName, modelClassCode, modelGroupType);
    }

}
