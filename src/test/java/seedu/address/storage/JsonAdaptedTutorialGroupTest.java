package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTutorialGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_01;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.ClassCode;
import seedu.address.model.tutorialgroup.GroupNumber;
import seedu.address.model.tutorialgroup.GroupType;

class JsonAdaptedTutorialGroupTest {

    private static final String INVALID_GROUP_NUMBER = "a";
    private static final String INVALID_GROUP_TYPE = "OP3";
    private static final String INVALID_CLASSCODE = "G100";

    private static final String VALID_GROUP_NUMBER = TUT_01.getGroupNumber().toString();
    private static final String VALID_GROUP_TYPE = TUT_01.getGroupType().toString();
    private static final String VALID_CLASSCODE = TUT_01.getClassCode().toString();

    @Test
    public void toModelType_validTutorialGroupDetails_returnsStudent() throws Exception {
        JsonAdaptedTutorialGroup tutorialGroup = new JsonAdaptedTutorialGroup(TUT_01);
        assertEquals(TUT_01, tutorialGroup.toModelType());
    }

    @Test
    public void toModelType_invalidGroupNumber_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(INVALID_GROUP_NUMBER,
                        VALID_CLASSCODE, VALID_GROUP_TYPE);
        String expectedMessage = GroupNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_nullGroupNumber_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup = new JsonAdaptedTutorialGroup(null,
                VALID_CLASSCODE, VALID_GROUP_TYPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_invalidClassCode_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(VALID_GROUP_NUMBER, INVALID_CLASSCODE, VALID_GROUP_TYPE);
        String expectedMessage = ClassCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_nullClassCode_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup = new JsonAdaptedTutorialGroup(VALID_GROUP_NUMBER, null,
                VALID_GROUP_TYPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_invalidGroupType_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup =
                new JsonAdaptedTutorialGroup(VALID_GROUP_NUMBER, VALID_CLASSCODE, INVALID_GROUP_TYPE);
        String expectedMessage = GroupType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

    @Test
    public void toModelType_nullGroupType_throwsIllegalValueException() {
        JsonAdaptedTutorialGroup tutorialGroup = new JsonAdaptedTutorialGroup(VALID_GROUP_NUMBER,
                VALID_CLASSCODE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorialGroup::toModelType);
    }

}
