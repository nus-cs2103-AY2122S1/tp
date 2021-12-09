package seedu.times.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.times.storage.JsonAdaptedTuitionClass.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.times.testutil.Assert.assertThrows;
import static seedu.times.testutil.TypicalTimestable.IB_PHYSICS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.times.commons.exceptions.IllegalValueException;
import seedu.times.model.tuitionclass.ClassName;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;

public class JsonAdaptedTuitionClassTest {
    private static final String INVALID_CLASS_NAME = " ";
    private static final String INVALID_CLASS_TIMING = "MON 11:00-10:00";
    private static final String INVALID_RATE = "Sixty";
    private static final String INVALID_LOCATION = " ";
    private static final List<String> INVALID_STUDENT_NAME_LIST = new ArrayList<>(Arrays.asList(" "));

    private static final String VALID_CLASS_NAME = IB_PHYSICS.getClassName().toString();
    private static final String VALID_CLASS_TIMING = IB_PHYSICS.getClassTiming().toString();
    private static final String VALID_RATE = IB_PHYSICS.getRate().toString();
    private static final String VALID_LOCATION = IB_PHYSICS.getLocation().toString();
    private static final List<String> VALID_EMPTY_STUDENT_NAME_LIST = new ArrayList<>();

    @Test
    public void toModelType_validTuitionClassDetails_returnsPerson() throws Exception {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(IB_PHYSICS);
        assertEquals(IB_PHYSICS, tuitionClass.toModelType());
    }

    @Test
    public void toModelType_emptyStudentList_success() throws Exception {
        assertDoesNotThrow(() -> new JsonAdaptedTuitionClass(VALID_CLASS_TIMING, VALID_CLASS_NAME, VALID_RATE,
                VALID_LOCATION, VALID_EMPTY_STUDENT_NAME_LIST));
    }

    @Test
    public void toModelType_invalidClassTiming_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(INVALID_CLASS_TIMING, VALID_CLASS_NAME,
                VALID_RATE, VALID_LOCATION, VALID_EMPTY_STUDENT_NAME_LIST);
        String expectedMessage = ClassTiming.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_nullClassTiming_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(null, VALID_CLASS_NAME,
                VALID_RATE, VALID_LOCATION, VALID_EMPTY_STUDENT_NAME_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassTiming.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_invalidClassName_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_CLASS_TIMING, INVALID_CLASS_NAME,
                VALID_RATE, VALID_LOCATION, VALID_EMPTY_STUDENT_NAME_LIST);
        String expectedMessage = ClassName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_nullClassName_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_CLASS_TIMING, null,
                VALID_RATE, VALID_LOCATION, VALID_EMPTY_STUDENT_NAME_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_invalidRate_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_CLASS_TIMING, VALID_CLASS_NAME,
                INVALID_RATE, VALID_LOCATION, VALID_EMPTY_STUDENT_NAME_LIST);
        String expectedMessage = Rate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_nullRate_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_CLASS_TIMING, VALID_CLASS_NAME,
                null, VALID_LOCATION, VALID_EMPTY_STUDENT_NAME_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Rate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_CLASS_TIMING, VALID_CLASS_NAME,
                VALID_RATE, INVALID_LOCATION, VALID_EMPTY_STUDENT_NAME_LIST);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_CLASS_TIMING, VALID_CLASS_NAME,
                VALID_RATE, null, VALID_EMPTY_STUDENT_NAME_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

}
