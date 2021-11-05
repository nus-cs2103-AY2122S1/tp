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
        Assert.assertThrows(NullPointerException.class, () -> new TaskDate(null, false));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDescription = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TaskDate(invalidDescription, false));
    }

    @Test
    public void getDate() {
        TaskDate taskDateDate1 = new TaskDate("20 Oct 2021", false);
        Optional<LocalDate> date1 = Optional.of(LocalDate.parse(("20 Oct 2021"),
                DateTimeFormatter.ofPattern("dd MMM yyyy")));

        TaskDate taskDateDate2 = new TaskDate("20/10/2020", false);
        Optional<LocalDate> date2 = Optional.of(LocalDate.parse(("20/10/2020"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        assertEquals(taskDateDate1.getDate(), date1);
        assertEquals(taskDateDate2.getDate(), date2);
    }

    @Test
    public void getTime() {
        TaskDate taskDateTime1 = new TaskDate("1600", false);
        Optional<LocalTime> time1 = Optional.of(LocalTime.parse(("1600"),
                DateTimeFormatter.ofPattern("HHmm", Locale.US)));

        TaskDate taskDateTime2 = new TaskDate("07:00 AM", false);
        Optional<LocalTime> time2 = Optional.of(LocalTime.parse(("07:00 AM"),
                DateTimeFormatter.ofPattern("hh:mm a", Locale.US)));

        assertEquals(taskDateTime1.getTime(), time1);
        assertEquals(taskDateTime2.getTime(), time2);
    }

    @Test
    public void hasDate() {
        TaskDate taskDate1 = new TaskDate("20 Oct 2021", false);
        TaskDate taskDate2 = new TaskDate("1600", false);

        assertTrue(taskDate1.hasDate());
        assertTrue(taskDate2.hasDate());
    }

    @Test
    public void hasTime() {
        TaskDate taskDate1 = new TaskDate("20 Oct 2021", false);
        TaskDate taskDate2 = new TaskDate("1600", false);

        assertFalse(taskDate1.hasTime());
        assertTrue(taskDate2.hasTime());
    }

    @Test
    public void toDateString() {
        TaskDate taskDate1 = new TaskDate("20/10/2021", false);
        TaskDate taskDate2 = new TaskDate("2021-12-10", false);

        assertEquals(taskDate1.toDateString(), "20 Oct 2021");
        assertEquals(taskDate2.toDateString(), "10 Dec 2021");
    }

    @Test
    public void toTimeString() {
        TaskDate taskDate1 = new TaskDate("10:00 AM", false);
        TaskDate taskDate2 = new TaskDate("1600", false);

        assertEquals(taskDate1.toTimeString(), "10:00 AM");
        assertEquals(taskDate2.toTimeString(), "04:00 PM");
    }

    @Test
    public void equals() {
        TaskDate taskDate1 = new TaskDate("20/10/2021", false);
        TaskDate taskDate2 = new TaskDate("20/10/2021", false);
        TaskDate taskDate3 = new TaskDate("21/10/2021", false);
        TaskDate taskDate4 = new TaskDate("1600", false);
        TaskDate taskDate5 = new TaskDate("1600", false);
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
