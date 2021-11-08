package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

public class DateTimeUtilTest {

    @Test
    public void generateCurrentTimestamp_success() {
        assertEquals(System.currentTimeMillis(), Long.parseLong(DateTimeUtil.generateTimestamp()));
    }


    @Test
    public void check_dateContainsTimestamp_success() {
        Date date = new GregorianCalendar(2021, Calendar.DECEMBER, 1).getTime();
        assertTrue(DateTimeUtil.isDateContainsTimestamp(date, 1638316800000L));
    }

}
