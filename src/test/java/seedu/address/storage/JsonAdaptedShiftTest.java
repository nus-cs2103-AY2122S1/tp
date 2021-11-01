package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Shift;
import seedu.address.model.person.Slot;
import seedu.address.testutil.ShiftBuilder;

public class JsonAdaptedShiftTest {

    private static final String INVALID_DATE = "friMon";


    private static final Shift DEFAULT_SHIFT = new ShiftBuilder(1).build();
    private static final String VALID_SLOT = Slot.AFTERNOON.toString();


    @Test
    public void toModelType_validShiftDetails_returnShift() throws Exception {
        JsonAdaptedShift adaptedShift = new JsonAdaptedShift(DEFAULT_SHIFT);
        assertEquals(DEFAULT_SHIFT, adaptedShift.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedShift adaptedShift =
                new JsonAdaptedShift(INVALID_DATE, VALID_SLOT, new ArrayList<>(), true);
        assertThrows(IllegalValueException.class, adaptedShift::toModelType);
    }





}
