package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("31 Feb 2021")); // no such date
        assertFalse(Date.isValidDate("12 Top 1234")); // contains invalid month

        // valid date
        assertTrue(Date.isValidDate("23 Feb 2021"));
        assertTrue(Date.isValidDate("12 Jan 2022"));
        assertTrue(Date.isValidDate("29 Feb 2020")); // leap year
    }

    @Test
    public void updateDate() {
        // At least 1 week has passed
        LocalDate oneWeekAgo = LocalDate.now().minusWeeks(1);
        Date dateOneWeekAgo = new Date(oneWeekAgo.format(Date.FORMATTER));
        assertEquals(LocalDate.now(), dateOneWeekAgo.updateDate().getLocalDate());

        /*
        Less than a week has passed but the date is over.
         */
        long daysBefore = 2;
        long daysInAWeek = 7;
        LocalDate lessThanOneWeekAgo = LocalDate.now().minusDays(daysBefore);
        Date dateLessThanOneWeekAgo = new Date(lessThanOneWeekAgo.format(Date.FORMATTER));
        assertEquals(LocalDate.now().plusDays(daysInAWeek - daysBefore),
            dateLessThanOneWeekAgo.updateDate().getLocalDate());

        // Current date (date is not over yet)
        Date today = new Date(LocalDate.now().format(Date.FORMATTER));
        assertEquals(LocalDate.now(), today.updateDate().getLocalDate());
    }

}

