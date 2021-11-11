package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.getTypicalApplications;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

public class InterviewDateAndTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewDateAndTime(null));
    }

    @Test
    public void getDate_returnsDate() {
        assertEquals(LocalDateTime.parse("2021-12-12 1600", DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")),
                new InterviewDateAndTime("2021-12-12 1600").getDate());
    }

    @Test
    public void isValidInterviewDateAndTime_returnsIsValid() {
        // correct date and time format
        assertTrue(InterviewDateAndTime.isValidInterviewDateAndTime("2021-12-12 1600"));

        // wrong date and time format
        assertFalse(InterviewDateAndTime.isValidInterviewDateAndTime("12-12-2021 4pm"));
    }

    @Test
    public void isValidDateAndTime_returnsIsValid() {
        // invalid date
        assertFalse(InterviewDateAndTime.isValidDateAndTime("2021-02-30 1600"));

        // feb 29 invalid on non-leap year
        assertFalse(InterviewDateAndTime.isValidDateAndTime("2021-02-29 1600"));

        // feb 29 valid on leap year
        assertTrue(InterviewDateAndTime.isValidDateAndTime("2020-02-29 1600"));

        // invalid time
        assertFalse(InterviewDateAndTime.isValidDateAndTime("2021-12-12 2500"));
    }

    @Test
    public void toString_returnsCorrectString() {
        InterviewDateAndTime i = new InterviewDateAndTime("2021-12-12 1600");
        assertEquals("[2021-12-12 1600]", i.toString());
    }

    @Test
    public void equals() {
        InterviewDateAndTime i = new InterviewDateAndTime("2021-12-12 1600");

        // same object -> return true
        assertTrue(i.equals(i));

        // different type -> return false
        assertFalse(i.equals(1));

        // null -> return false
        assertFalse(i.equals(null));

        // different date -> return false
        assertFalse(i.equals(new InterviewDateAndTime("2020-02-12 1600")));

        // different time -> return false
        assertFalse(i.equals(new InterviewDateAndTime("2020-12-12 1300")));

        // same value -> return true
        assertTrue(i.equals(new InterviewDateAndTime("2021-12-12 1600")));
    }

    @Test
    public void getComparator_success() {
        List<Application> applicationList = getTypicalApplications();
        applicationList.sort(InterviewDateAndTime.getComparator());
    }

}
