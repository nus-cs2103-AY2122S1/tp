package dash.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import dash.testutil.Assert;

public class TaskDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDescription = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TaskDate(invalidDescription));
    }

    @Test
    public void isValidTaskDate() {
        // null date
        Assert.assertThrows(NullPointerException.class, () -> TaskDate.isValidTaskDate(null));

        // invalid dates
        assertFalse(TaskDate.isValidTaskDate("")); // empty string
        assertFalse(TaskDate.isValidTaskDate(" ")); // spaces only
        assertFalse(TaskDate.isValidTaskDate("21 10 2021")); // wrong date format only
        assertFalse(TaskDate.isValidTaskDate("21/20/2021")); // impossible dates
        assertFalse(TaskDate.isValidTaskDate("7 00 PM")); // wrong time format only
        assertFalse(TaskDate.isValidTaskDate("20:59 PM")); // impossible times
        assertFalse(TaskDate.isValidTaskDate("21/10/2021 07:00 PM")); // wrong date time separation
        assertFalse(TaskDate.isValidTaskDate("not date or time")); // not date or time
        assertFalse(TaskDate.isValidTaskDate("21/10/2021, 07:00")); // valid date, wrong time format
        assertFalse(TaskDate.isValidTaskDate("21 10 2021, 07:00 PM")); // valid time, wrong date format

        // valid dates
        assertTrue(TaskDate.isValidTaskDate("11/10/2020")); // valid date format only
        assertTrue(TaskDate.isValidTaskDate("10:59 AM")); // valid time format only
        assertTrue(TaskDate.isValidTaskDate("10/10/2021, 2359")); // valid date and time
    }

    @Test
    public void getDate() {
        TaskDate taskDateDate1 = new TaskDate("20 Oct 2021");
        Optional<LocalDate> date1 = Optional.of(LocalDate.parse(("20 Oct 2021"),
                DateTimeFormatter.ofPattern("dd MMM yyyy")));

        TaskDate taskDateDate2 = new TaskDate("20/10/2020");
        Optional<LocalDate> date2 = Optional.of(LocalDate.parse(("20/10/2020"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        assertEquals(taskDateDate1.getDate(), date1);
        assertEquals(taskDateDate2.getDate(), date2);
    }

    @Test
    public void getTime() {
        TaskDate taskDateTime1 = new TaskDate("1600");
        Optional<LocalTime> time1 = Optional.of(LocalTime.parse(("1600"),
                DateTimeFormatter.ofPattern("HHmm", Locale.US)));

        TaskDate taskDateTime2 = new TaskDate("07:00 AM");
        Optional<LocalTime> time2 = Optional.of(LocalTime.parse(("07:00 AM"),
                DateTimeFormatter.ofPattern("hh:mm a", Locale.US)));

        assertEquals(taskDateTime1.getTime(), time1);
        assertEquals(taskDateTime2.getTime(), time2);
    }

    @Test
    public void hasDate() {
        TaskDate taskDate1 = new TaskDate("20 Oct 2021");
        TaskDate taskDate2 = new TaskDate("1600");

        assertTrue(taskDate1.hasDate());
        assertTrue(taskDate2.hasDate());
    }

    @Test
    public void hasTime() {
        TaskDate taskDate1 = new TaskDate("20 Oct 2021");
        TaskDate taskDate2 = new TaskDate("1600");

        assertFalse(taskDate1.hasTime());
        assertTrue(taskDate2.hasTime());
    }

    @Test
    public void isThisDate() {
        String validDateFormat1 = "10/10/2021";
        String validDateFormat2 = "10-10-2021";
        String validDateFormat3 = "2021/10/10";
        String validDateFormat4 = "2021-10-10";
        String validDateFormat5 = "10 Oct 2021";
        String invalidDateFormat = "10/Oct/2021";
        String invalidDate = "10/20/2020";

        assertTrue(TaskDate.isThisDate(validDateFormat1));
        assertTrue(TaskDate.isThisDate(validDateFormat2));
        assertTrue(TaskDate.isThisDate(validDateFormat3));
        assertTrue(TaskDate.isThisDate(validDateFormat4));
        assertTrue(TaskDate.isThisDate(validDateFormat5));
        assertFalse(TaskDate.isThisDate(invalidDateFormat));
        assertFalse(TaskDate.isThisDate(invalidDate));
    }

    @Test
    public void isThisTime() {
        String validTimeFormat1 = "2330";
        String validTimeFormat2 = "10:00 AM";
        String invalidTimeFormat = "7:00 PM";
        String invalidTime = "20:10 PM";

        assertTrue(TaskDate.isThisTime(validTimeFormat1));
        assertTrue(TaskDate.isThisTime(validTimeFormat2));
        assertFalse(TaskDate.isThisTime(invalidTimeFormat));
        assertFalse(TaskDate.isThisTime(invalidTime));
    }

    @Test
    public void toDateString() {
        TaskDate taskDate1 = new TaskDate("20/10/2021");
        TaskDate taskDate2 = new TaskDate("2021-12-10");

        assertEquals(taskDate1.toDateString(), "20 Oct 2021");
        assertEquals(taskDate2.toDateString(), "10 Dec 2021");
    }

    @Test
    public void toTimeString() {
        TaskDate taskDate1 = new TaskDate("10:00 AM");
        TaskDate taskDate2 = new TaskDate("1600");

        assertEquals(taskDate1.toTimeString(), "10:00 AM");
        assertEquals(taskDate2.toTimeString(), "04:00 PM");
    }

    @Test
    public void equals() {
        TaskDate taskDate1 = new TaskDate("20/10/2021");
        TaskDate taskDate2 = new TaskDate("20/10/2021");
        TaskDate taskDate3 = new TaskDate("21/10/2021");
        TaskDate taskDate4 = new TaskDate("1600");
        TaskDate taskDate5 = new TaskDate("1600");
        TaskDate emptyTaskDate1 = new TaskDate();
        TaskDate emptyTaskDate2 = new TaskDate();


        assertTrue(taskDate1.equals(taskDate1));
        assertTrue(taskDate1.equals(taskDate2));
        assertTrue(taskDate4.equals(taskDate5));
        assertTrue(emptyTaskDate1.equals(emptyTaskDate1));
        assertTrue(emptyTaskDate1.equals(emptyTaskDate2));
        assertFalse(taskDate1.equals(taskDate3));
        assertFalse(taskDate1.equals(taskDate4));
        assertFalse(emptyTaskDate1.equals(taskDate1));
    }
}
