package socialite.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid dates
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("2020-02-01")); // no name
        assertFalse(Date.isValidDate(":2020-02-01")); // no name
        assertFalse(Date.isValidDate("date name")); // no date
        assertFalse(Date.isValidDate("date name:")); // no date
        assertFalse(Date.isValidDate("date:2021-02-02:hourly")); // invalid recurrence interval
        assertFalse(Date.isValidDate("date:2021-02-29")); // non-existent date
        assertFalse(Date.isValidDate("date:2020-03-32")); // non-existent date
        // name over 50 characters long
        assertFalse(Date.isValidDate("date ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ:2021-02-02:yearly"));

        // valid dates
        assertTrue(Date.isValidDate("date:2020-02-02"));
        // name with space character ("date 3")
        assertTrue(Date.isValidDate("date 3:2020-02-03"));

        // recurring dates
        assertTrue(Date.isValidDate("date:2021-02-02:yearly"));
        assertTrue(Date.isValidDate("date 3:2021-02-03:yearly"));
        assertTrue(Date.isValidDate("date:2021-02-02:monthly"));
        assertTrue(Date.isValidDate("date 3:2021-02-03:monthly"));
    }

    @Test
    public void getRecurrenceInterval() {
        // empty recurrence interval
        assertTrue(new Date("date:2020-02-02").getRecurrenceInterval().isEmpty());

        // yearly recurrence interval
        Date recurringDateYearly = new Date("date:2021-02-02:yearly");
        assertTrue(recurringDateYearly.getRecurrenceInterval().isPresent());
        assertEquals("yearly", recurringDateYearly.getRecurrenceInterval().get());

        // monthly recurrence interval
        Date recurringDateMonthly = new Date("date:2021-02-02:monthly");
        assertTrue(recurringDateMonthly.getRecurrenceInterval().isPresent());
        assertEquals("monthly", recurringDateMonthly.getRecurrenceInterval().get());
    }

    @Test
    public void getNextOccurrence_nonRecurring() {
        Date nonRecurring = new Date("date:2020-02-02");

        // next occurrence with reference date after a non-recurring date should be empty
        LocalDate referenceDate1 = LocalDate.of(2021, 4, 4);
        assertTrue(nonRecurring.getNextOccurrence(referenceDate1).isEmpty());

        // next occurrence with reference date before a non-recurring date should be the non-recurring date
        LocalDate referenceDate2 = LocalDate.of(2020, 1, 1);
        assertTrue(nonRecurring.getNextOccurrence(referenceDate2).isPresent());
        assertEquals(LocalDate.of(2020, 2, 2), nonRecurring.getNextOccurrence(referenceDate2).get());

        // next occurrence with reference date on a non-recurring date should be the non-recurring date
        LocalDate referenceDate3 = LocalDate.of(2020, 2, 2);
        assertTrue(nonRecurring.getNextOccurrence(referenceDate3).isPresent());
        assertEquals(LocalDate.of(2020, 2, 2), nonRecurring.getNextOccurrence(referenceDate3).get());
    }

    @Test
    public void getNextOccurrence_recurringMonthly() {
        Date recurring = new Date("date:2020-02-02:monthly");

        // next occurrence with reference date after a recurring date should not be empty
        LocalDate referenceDate1 = LocalDate.of(2021, 4, 4);
        assertTrue(recurring.getNextOccurrence(referenceDate1).isPresent());
        assertEquals(LocalDate.of(2021, 5, 2), recurring.getNextOccurrence(referenceDate1).get());

        // next occurrence with reference date before a recurring date should the recurring date
        LocalDate referenceDate2 = LocalDate.of(2020, 1, 1);
        assertTrue(recurring.getNextOccurrence(referenceDate2).isPresent());
        assertEquals(LocalDate.of(2020, 2, 2), recurring.getNextOccurrence(referenceDate2).get());

        // next occurrence with reference date on a recurring date should be the recurring date
        LocalDate referenceDate3 = LocalDate.of(2020, 2, 2);
        assertTrue(recurring.getNextOccurrence(referenceDate3).isPresent());
        assertEquals(LocalDate.of(2020, 2, 2), recurring.getNextOccurrence(referenceDate3).get());
    }

    @Test
    public void getNextOccurrence_recurringYearly() {
        Date recurring = new Date("date:2020-02-02:yearly");

        // next occurrence with reference date after a recurring date should not be empty
        LocalDate referenceDate1 = LocalDate.of(2021, 4, 4);
        assertTrue(recurring.getNextOccurrence(referenceDate1).isPresent());
        assertEquals(LocalDate.of(2022, 2, 2), recurring.getNextOccurrence(referenceDate1).get());

        // next occurrence with reference date before a recurring date should the recurring date
        LocalDate referenceDate2 = LocalDate.of(2020, 1, 1);
        assertTrue(recurring.getNextOccurrence(referenceDate2).isPresent());
        assertEquals(LocalDate.of(2020, 2, 2), recurring.getNextOccurrence(referenceDate2).get());

        // next occurrence with reference date on a recurring date should be the recurring date
        LocalDate referenceDate3 = LocalDate.of(2020, 2, 2);
        assertTrue(recurring.getNextOccurrence(referenceDate3).isPresent());
        assertEquals(LocalDate.of(2020, 2, 2), recurring.getNextOccurrence(referenceDate3).get());
    }

    @Test
    public void getUpcomingDays() {
        Date date = new Date("date:2020-02-02:yearly");

        assertEquals(0, date.getUpcomingDays(LocalDate.of(2020, 2, 2)));
        assertEquals(276, date.getUpcomingDays(LocalDate.of(2020, 5, 2)));
    }

    @Test
    public void toString_success() {
        assertEquals("date: 2020-02-02", new Date("date:2020-02-02").toString());
        assertEquals("date: 2020-02-02 (monthly)", new Date("date:2020-02-02:monthly").toString());
        assertEquals("date: 2020-02-02 (yearly)", new Date("date:2020-02-02:yearly").toString());
    }

    @Test
    public void equals_success() {
        Date date = new Date("date:2020-02-02:yearly");

        assertEquals(date, new Date("date:2020-02-02:yearly"));
        assertNotEquals(date, new Date("date:2020-02-02"));
    }

    @Test
    public void hashCode_success() {
        Date date = new Date("date:2020-02-02:yearly");

        assertEquals(date.hashCode(), new Date("date:2020-02-02:yearly").hashCode());
        assertNotEquals(date.hashCode(), new Date("date:2020-02-02").hashCode());
    }

    @Test
    public void check_equal_dates() {
        Date d1 = new Date("date:2020-02-02");
        Date d2 = new Date("date:2020-02-02");
        assertTrue(d1.equals(d2));
    }

    @Test
    public void check_date_string() {
        Date d1 = new Date("date:2020-01-01");
        assertEquals("date: 2020-01-01", d1.toString());
    }
}
