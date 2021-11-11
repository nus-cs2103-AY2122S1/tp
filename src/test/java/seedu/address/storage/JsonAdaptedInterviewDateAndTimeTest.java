package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.Assert;

public class JsonAdaptedInterviewDateAndTimeTest {
    @Test
    public void toModelType_invalidInterviewDateAndTime_throwsException() {
        JsonAdaptedInterviewDateAndTime invalidInterviewDateAndTime = new JsonAdaptedInterviewDateAndTime("");
        Assert.assertThrows(IllegalValueException.class, invalidInterviewDateAndTime::toModelType);
    }

    @Test
    public void getInterviewDateAndTime_success() {
        String validTime = "2021-12-21 0900";
        assertEquals(validTime, new JsonAdaptedInterviewDateAndTime(validTime).getInterviewDateAndTime());
    }
}
