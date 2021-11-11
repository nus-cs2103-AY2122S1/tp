package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.getTypicalApplications;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void getDate_returnsDate() {
        assertEquals(LocalDate.parse("2021-12-12"), new Deadline("2021-12-12").getDate());
    }

    @Test
    public void isValidDeadline_returnsIsValid() {
        // correct deadline format and date
        assertTrue(Deadline.isValidDeadline("2021-12-12"));

        // correct deadline format but invalid date
        assertTrue(Deadline.isValidDeadline("2021-02-30"));

        // wrong deadline format
        assertFalse(Deadline.isValidDeadline("12-12-2021"));
    }

    @Test
    public void isValidDate_returnsIsValid() {
        // valid date
        assertTrue(Deadline.isValidDate("2021-12-12"));

        // invalid date
        assertFalse(Deadline.isValidDate("2021-02-30"));

        // feb 29 invalid on non-leap year
        assertFalse(Deadline.isValidDate("2021-02-29"));

        // feb 29 valid on leap year
        assertTrue(Deadline.isValidDate("2020-02-29"));
    }

    @Test
    public void equals() {
        Deadline d = new Deadline("2021-12-12");

        // same object -> return true
        assertTrue(d.equals(d));

        // null -> return false
        assertFalse(d.equals(null));

        // different value -> return false
        assertFalse(d.equals(new Deadline("2020-02-12")));

        // same value -> return true
        assertTrue(d.equals(new Deadline("2021-12-12")));
    }

    @Test
    public void getComparator_success() {
        List<Application> applicationList = getTypicalApplications();
        applicationList.sort(Deadline.getComparator());
    }

    @Test
    public void toString_success() {
        String deadlineString = "2021-12-29";
        String formattedDeadline = "Dec 29 2021";
        Deadline amazonDeadline = AMAZON.getDeadline();
        assertEquals(deadlineString, amazonDeadline.toString());
        assertEquals(deadlineString.hashCode(), amazonDeadline.hashCode());
        assertEquals(formattedDeadline, amazonDeadline.toFormattedString());
    }


}
