package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.person.PersonTestUtil.createPeriod;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.RecurrencePeriod;
import seedu.address.model.person.EmptyShift;
import seedu.address.model.person.Shift;
import seedu.address.model.person.Slot;
import seedu.address.testutil.ShiftBuilder;

public class JsonAdaptedShiftTest {

    private static final String INVALID_DATE = "friMon";
    private static final String INVALID_SLOT = "applebees";

    private static final Shift DEFAULT_SHIFT = new ShiftBuilder(1).build();
    private static final String VALID_SLOT = Slot.AFTERNOON.toString();
    private static final String VALID_DATE = "MONDAY";
    private static final JsonAdaptedRecurrencePeriod INVALID_PERIOD =
            new JsonAdaptedRecurrencePeriod(new RecurrencePeriod(createPeriod(1, 10), Slot.MORNING));


    @Test
    public void toModelType_validShiftDetails_returnShift() throws Exception {
        JsonAdaptedShift adaptedShift = new JsonAdaptedShift(DEFAULT_SHIFT);
        assertEquals(DEFAULT_SHIFT, adaptedShift.toModelType());
        Shift result = new EmptyShift(DayOfWeek.MONDAY, Slot.AFTERNOON);
        adaptedShift = new JsonAdaptedShift(VALID_DATE, VALID_SLOT, new ArrayList<>(), true);
        assertEquals(result, adaptedShift.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedShift adaptedShift =
                new JsonAdaptedShift(INVALID_DATE, VALID_SLOT, new ArrayList<>(), true);
        assertThrows(IllegalValueException.class, adaptedShift::toModelType);
    }

    @Test
    public void toModelType_invalidSlot_throwsIllegalValueException() {
        JsonAdaptedShift adaptedShift =
                new JsonAdaptedShift(VALID_DATE, INVALID_SLOT, new ArrayList<>(), true);
        assertThrows(IllegalValueException.class, adaptedShift::toModelType);
    }

    @Test
    public void toModelType_wrongTimeHistory_throwsIllegalValueException() {
        JsonAdaptedShift adaptedShift =
                new JsonAdaptedShift(VALID_DATE, VALID_SLOT, List.of(INVALID_PERIOD), false);
        assertThrows(IllegalValueException.class, adaptedShift::toModelType);
    }



}
