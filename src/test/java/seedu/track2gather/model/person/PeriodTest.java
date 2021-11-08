package seedu.track2gather.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.track2gather.model.person.attributes.Period;

public class PeriodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Period(null, null));
    }

    @Test
    public void constructor_invalidPeriod_throwsIllegalArgumentException() {
        LocalDate invalidStartDate = LocalDate.of(2021, 01, 02);
        LocalDate invalidEndDate = LocalDate.of(2021, 01, 01);
        assertThrows(IllegalArgumentException.class, () -> new Period(invalidStartDate, invalidEndDate));
    }

    @Test
    public void isValidPeriod() {
        // null period
        assertThrows(NullPointerException.class, () -> Period.isValidPeriod(null, null));

        // invalid periods
        assertFalse(Period.isValidPeriod(
                LocalDate.of(2021, 01, 02),
                LocalDate.of(2021, 01, 01))); // startDate is after endDate
        assertFalse(Period.isValidPeriod(
                LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 01))); // startDate is same as endDate

        // valid periods
        assertTrue(Period.isValidPeriod(
                LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 02)));
        assertTrue(Period.isValidPeriod(
                LocalDate.of(2021, 01, 01),
                LocalDate.of(2021, 01, 02)));
    }
}
