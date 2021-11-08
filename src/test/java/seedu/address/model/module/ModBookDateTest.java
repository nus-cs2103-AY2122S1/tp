package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ModBookDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModBookDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ModBookDate(invalidDate));
    }

    @Test
    void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> ModBookDate.isValidDate(null));

        // blank date
        assertFalse(ModBookDate.isValidDate(""));
        assertFalse(ModBookDate.isValidDate(" "));

        // invalid date formats
        assertFalse(ModBookDate.isValidDate("September 2021")); // no specification of date within month
        assertFalse(ModBookDate.isValidDate("32/07/2021")); // non-existent date
        assertFalse(ModBookDate.isValidDate("2021/07/2012")); // wrong order
        assertFalse(ModBookDate.isValidDate("19th Sep")); // no year
        assertFalse(ModBookDate.isValidDate("12/13/2021")); // Month out of range
        assertFalse(ModBookDate.isValidDate("6pm 12/12/2021")); // including time of day
        assertFalse(ModBookDate.isValidDate("09|09|2021"));
        assertFalse(ModBookDate.isValidDate("09,09,2021"));
        assertFalse(ModBookDate.isValidDate("29/02/2021"));
        assertFalse(ModBookDate.isValidDate("31/06/2021"));

        // valid dates
        assertTrue(ModBookDate.isValidDate("19 Sep 2021")); // use of month abbreviations
        assertTrue(ModBookDate.isValidDate("19-02-2012")); // use of hyphens
        assertTrue(ModBookDate.isValidDate("09.09.2021"));
        assertTrue(ModBookDate.isValidDate("09 July 2021"));
        assertTrue(ModBookDate.isValidDate("09 Jul 2021"));
        assertTrue(ModBookDate.isValidDate("9 July 2021"));
        assertTrue(ModBookDate.isValidDate("9 Jul 2021"));
        assertTrue(ModBookDate.isValidDate("09092021"));
        assertTrue(ModBookDate.isValidDate("22/12/2022"));
        assertTrue(ModBookDate.isValidDate("29/05/2024"));
    }

    @Test
    void isToday() {
        assertTrue(new ModBookDate(LocalDate.now().format(ModBookDate.PRINT_FORMATTER)).isToday());
        assertFalse(new ModBookDate(LocalDate.now().plusDays(1).format(ModBookDate.PRINT_FORMATTER)).isToday());
        assertFalse(new ModBookDate(LocalDate.now().minusDays(1).format(ModBookDate.PRINT_FORMATTER)).isToday());
    }

    @Test
    void isFuture() {
        assertTrue(new ModBookDate(LocalDate.now().plusDays(1).format(ModBookDate.PRINT_FORMATTER)).isFuture());
        assertFalse(new ModBookDate(LocalDate.now().format(ModBookDate.PRINT_FORMATTER)).isFuture());
        assertFalse(new ModBookDate(LocalDate.now().minusDays(1).format(ModBookDate.PRINT_FORMATTER)).isFuture());
    }
}
