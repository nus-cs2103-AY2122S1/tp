package socialite.model.person;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import socialite.testutil.Assert;

public class DatesTest {
    @Test
    public void isValidDates() {
        // null date
        Assert.assertThrows(NullPointerException.class, () -> Dates.isValidDatesSequence(null));

        // invalid dates
        assertFalse(Dates.isValidDatesSequence("")); // empty string
        assertFalse(Dates.isValidDatesSequence(" ")); // spaces only
        assertFalse(Dates.isValidDatesSequence("2020-02-01")); // no name
        assertFalse(Dates.isValidDatesSequence(":2020-02-01")); // no name
        assertFalse(Dates.isValidDatesSequence("date name")); // no date
        assertFalse(Dates.isValidDatesSequence("date name:")); // no date

        // valid date sequences
        assertTrue(Dates.isValidDatesSequence("date:2020-02-02"));
        assertTrue(Dates.isValidDatesSequence("date1:2020-02-03 date2:2020-02-03"));
        // name with space character ("date 3")
        assertTrue(Dates.isValidDatesSequence("date1:2020-02-03 date2:2020-02-03 date 3:2020-02-03"));
    }
}
