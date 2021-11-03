package safeforhall.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class LastDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastDate(null));
    }

    @Test
    public void isValidDate() {
        // null dates
        assertThrows(NullPointerException.class, () -> new LastDate(null));

        // invalid dates
        //assertFalse(LastDate.isValidDate(""));
        assertFalse(LastDate.isValidDate("9-9-2021"));
        assertFalse(LastDate.isValidDate("19-9-2021"));

        // valid dates
        assertTrue(LastDate.isValidDate("21-10-2021"));
        assertTrue(LastDate.isValidDate("10.10.2021"));
        assertTrue(LastDate.isValidDate("10/10/2021"));
    }

    @Test
    public void isFutureDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        assertFalse(LastDate.isFutureDate(LastDate.DEFAULT_DATE)); // default lastDate
        assertFalse(LastDate.isFutureDate(LocalDate.now().format(formatter))); // current date
        assertFalse(LastDate.isFutureDate(LocalDate.now().minusDays(1).format(formatter))); // a day before current date
        assertFalse(LastDate.isFutureDate("10-10-2020")); // current date

        assertTrue(LastDate.isFutureDate(LocalDate.now().plusDays(1).format(formatter))); // a day after current date
        assertTrue(LastDate.isFutureDate("10-10-2022")); // a day after current date
    }

    @Test
    public void returnsDateWhenEmpty_success() {
        LastDate date = new LastDate(LastDate.DEFAULT_DATE);
        assertDoesNotThrow(() -> date.getDeadline());
    }

    @Test
    public void checkCompareTo() {
        LastDate ld1 = new LastDate("10-02-2020");
        LastDate ld2 = new LastDate("11-02-2020");
        LastDate ld3 = new LastDate("10-02-2020");

        assertEquals(ld1.compareTo(ld2), -1);
        assertEquals(ld2.compareTo(ld1), 1);
        assertEquals(ld3.compareTo(ld1), 0);
    }
}
