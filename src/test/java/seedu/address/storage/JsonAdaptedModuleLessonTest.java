package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModuleLessons.CS2100_LAB1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedModuleLessonTest {

    private static final String INVALID_DAY = "8";
    private static final String INVALID_TIME = "15:61";
    private static final String INVALID_MODULE_CODE = "CS 50";

    private static final String VALID_MODULE_CODE = "CS2040S T16";
    private static final String VALID_DAY = CS2100_LAB1.getDay().getDayAsIntString();
    private static final String VALID_TIME = CS2100_LAB1.getTime().toString();
    private static final String VALID_REMARK = CS2100_LAB1.getRemark().toString();

    @Test
    public void toModelType_validModuleClassDetails_returnsModuleClass() throws IllegalValueException {
        JsonAdaptedModuleLesson moduleClass = new JsonAdaptedModuleLesson(CS2100_LAB1);
        assertEquals(CS2100_LAB1, moduleClass.toModelType());
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegaArgumentException() {
        JsonAdaptedModuleLesson moduleClass = new JsonAdaptedModuleLesson(INVALID_MODULE_CODE,
                VALID_DAY, VALID_TIME, VALID_REMARK);
        assertThrows(IllegalValueException.class, moduleClass::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsIllegaArgumentException() {
        JsonAdaptedModuleLesson moduleClass = new JsonAdaptedModuleLesson(VALID_MODULE_CODE,
                INVALID_DAY, VALID_TIME, VALID_REMARK);
        assertThrows(IllegalArgumentException.class, moduleClass::toModelType);
    }

    @Test void toModelType_nullDay_throwsIllegalValueException() {
        JsonAdaptedModuleLesson moduleClass = new JsonAdaptedModuleLesson(VALID_MODULE_CODE, null,
                VALID_TIME, VALID_REMARK);
        assertThrows(IllegalValueException.class, moduleClass::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegaArgumentException() {
        JsonAdaptedModuleLesson moduleClass = new JsonAdaptedModuleLesson(VALID_MODULE_CODE,
                VALID_DAY, INVALID_TIME, VALID_REMARK);
        assertThrows(IllegalArgumentException.class, moduleClass::toModelType);
    }

    @Test void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedModuleLesson moduleClass = new JsonAdaptedModuleLesson(VALID_MODULE_CODE, VALID_DAY,
                null, VALID_REMARK);
        assertThrows(IllegalValueException.class, moduleClass::toModelType);
    }


}
