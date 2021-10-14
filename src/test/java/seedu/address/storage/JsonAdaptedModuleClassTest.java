package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModuleClasses.CS2100_LAB1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedModuleClassTest {

    public static final String INVALID_MODULE_CODE = "CS 50";
    public static final String INVALID_DAY = "8";
    public static final String INVALID_TIME = "15:61";

    public static final String VALID_MODULE_CODE = CS2100_LAB1.getModuleCode().toString();
    public static final String VALID_DAY = CS2100_LAB1.getDay().getDayAsIntString();
    public static final String VALID_TIME = CS2100_LAB1.getTime().toString();
    public static final String VALID_REMARK = CS2100_LAB1.getRemark().toString();

    @Test
    public void toModelType_validModuleClassDetails_returnsModuleClass() throws Exception {
        JsonAdaptedModuleClass moduleClass = new JsonAdaptedModuleClass(CS2100_LAB1);
        assertEquals(CS2100_LAB1, moduleClass.toModelType());
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegaArgumentException() {
        JsonAdaptedModuleClass moduleClass = new JsonAdaptedModuleClass(INVALID_MODULE_CODE,
                VALID_DAY, VALID_TIME, VALID_REMARK);
        assertThrows(IllegalArgumentException.class, moduleClass::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsIllegaArgumentException() {
        JsonAdaptedModuleClass moduleClass = new JsonAdaptedModuleClass(VALID_MODULE_CODE,
                INVALID_DAY, VALID_TIME, VALID_REMARK);
        assertThrows(IllegalArgumentException.class, moduleClass::toModelType);
    }

    @Test void toModelType_nullDay_throwsIllegalValueException() {
        JsonAdaptedModuleClass moduleClass = new JsonAdaptedModuleClass("CS2040S", null,
                VALID_TIME, VALID_REMARK);
        assertThrows(IllegalValueException.class, moduleClass::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegaArgumentException() {
        JsonAdaptedModuleClass moduleClass = new JsonAdaptedModuleClass(VALID_MODULE_CODE,
                VALID_DAY, INVALID_TIME, VALID_REMARK);
        assertThrows(IllegalArgumentException.class, moduleClass::toModelType);
    }

    @Test void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedModuleClass moduleClass = new JsonAdaptedModuleClass("CS2040S", VALID_DAY,
                null, VALID_REMARK);
        assertThrows(IllegalValueException.class, moduleClass::toModelType);
    }


}
