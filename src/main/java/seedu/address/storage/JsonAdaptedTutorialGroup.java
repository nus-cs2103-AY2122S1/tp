package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.ClassCode;
import seedu.address.model.tutorialgroup.GroupNumber;
import seedu.address.model.tutorialgroup.GroupType;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class JsonAdaptedTutorialGroup {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial Group's %s field is missing!";

    private final String groupNumber;
    private final String classCode;
    private final String groupType;

    /**
     * Constructs a {@code JsonAdaptedTutorialGroup} with the given tutorial group details.
     */
    @JsonCreator
    public JsonAdaptedTutorialGroup(@JsonProperty("groupNumber") String groupNumber,
                                    @JsonProperty("classCode") String classCode,
                                    @JsonProperty("groupType") String groupType) {

        this.groupNumber = groupNumber;
        this.classCode = classCode;
        this.groupType = groupType;
    }

    /**
     * Converts a given {@code TutorialGroup} into this class for Jackson use.
     */
    public JsonAdaptedTutorialGroup(TutorialGroup source) {
        groupNumber = source.getGroupNumber().toString();
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
        if (!ClassCode.isValidClassCode(classCode)) {
            throw new IllegalValueException(ClassCode.MESSAGE_CONSTRAINTS);
        }
        final ClassCode modelClassCode = new ClassCode(classCode);

        if (groupNumber == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupNumber.class.getSimpleName())
            );
        }
        if (!GroupNumber.isValidGroupNumber(groupNumber)) {
            throw new IllegalValueException(GroupNumber.MESSAGE_CONSTRAINTS);
        }

        final GroupNumber modelGroupNumber = new GroupNumber(groupNumber);

        if (groupType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }
        if (!GroupType.isValidGroupType(groupType)) {
            throw new IllegalValueException(GroupType.MESSAGE_CONSTRAINTS);
        }

        final GroupType modelGroupType = new GroupType(groupType);

        return new TutorialGroup(modelGroupNumber, modelClassCode, modelGroupType);
    }

}
