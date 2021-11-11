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
        final ClassCode modelClassCode = addClassCode();
        final GroupNumber modelGroupNumber = addGroupNumber();
        final GroupType modelGroupType = addGroupType();
        return new TutorialGroup(modelGroupNumber, modelClassCode, modelGroupType);
    }

    /**
     * Abstracted method to get GroupNumber.
     *
     * @return Valid GroupNumber.
     * @throws IllegalValueException
     */
    private GroupNumber addGroupNumber() throws IllegalValueException {
        if (groupNumber == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupNumber.class.getSimpleName())
            );
        }
        if (!GroupNumber.isValidGroupNumber(groupNumber)) {
            throw new IllegalValueException(GroupNumber.MESSAGE_CONSTRAINTS);
        }
        return new GroupNumber(groupNumber);
    }

    /**
     * Abstracted method to get ClassCode.
     *
     * @return Valid ClassCode.
     * @throws IllegalValueException
     */
    private ClassCode addClassCode() throws IllegalValueException {
        if (classCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClassCode.class.getSimpleName()));
        }
        if (!ClassCode.isValidClassCode(classCode)) {
            throw new IllegalValueException(ClassCode.MESSAGE_CONSTRAINTS);
        }
        return new ClassCode(classCode);
    }

    /**
     * Abstracted method to add GroupType.
     *
     * @return valid GroupType.
     * @throws IllegalValueException
     */
    private GroupType addGroupType() throws IllegalValueException {
        if (groupType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GroupType.class.getSimpleName()));
        }
        if (!GroupType.isValidGroupType(groupType)) {
            throw new IllegalValueException(GroupType.MESSAGE_CONSTRAINTS);
        }
        return new GroupType(groupType);
    }

}
