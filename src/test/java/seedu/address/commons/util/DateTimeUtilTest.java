package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTimeUtilTest {

    @Test
    public void matchDateTimeRegex() {
        // valid date time format
        assertTrue(DateTimeUtil.matchDateTimeRegex("2020-01-01 12:00"));

        // invalid date time format
        assertFalse(DateTimeUtil.matchDateTimeRegex(""));
        assertFalse(DateTimeUtil.matchDateTimeRegex("2021-02-01 0000"));
        assertFalse(DateTimeUtil.matchDateTimeRegex("2021-01-01T22:00"));
        assertFalse(DateTimeUtil.matchDateTimeRegex("2021/01/01 22:00"));
    }

    @Test
    public void isFuture() {
        // is in future
        assertTrue(DateTimeUtil.isFuture(LocalDateTime.now().plusMinutes(1)));
        assertTrue(DateTimeUtil.isFuture(LocalDateTime.now().plusDays(1)));

        // is not in future
        assertFalse(DateTimeUtil.isFuture(LocalDateTime.now()));
        assertFalse(DateTimeUtil.isFuture(LocalDateTime.now().minusMinutes(1)));
        assertFalse(DateTimeUtil.isFuture(LocalDateTime.now().minusDays(1)));
    }

    @Test
    public void isPast() {
        // is in past
        assertTrue(DateTimeUtil.isPast(LocalDateTime.now().minusMinutes(1)));
        assertTrue(DateTimeUtil.isPast(LocalDateTime.now().minusDays(1)));

        // is not in past
        assertFalse(DateTimeUtil.isPast(LocalDateTime.now().plusMinutes(1)));
        assertFalse(DateTimeUtil.isPast(LocalDateTime.now().plusDays(1)));
    }

    @Test
    public void isNextSevenDays() {
        // is in the next seven days
        assertTrue(DateTimeUtil.isNextSevenDays(LocalDateTime.now().plusMinutes(1)));
        assertTrue(DateTimeUtil.isNextSevenDays(LocalDateTime.now().plusDays(7)));

        // is not in the next seven days
        assertFalse(DateTimeUtil.isNextSevenDays(LocalDateTime.now().minusMinutes(1)));
        assertFalse(DateTimeUtil.isNextSevenDays(LocalDateTime.now().minusDays(1)));
        assertFalse(DateTimeUtil.isNextSevenDays(LocalDateTime.now().plusDays(7).plusMinutes(1)));
        assertFalse(DateTimeUtil.isNextSevenDays(LocalDateTime.now().plusDays(8)));
    }

    @Test
    public void isNextThirtyDays() {
        // is in the next thirty days
        assertTrue(DateTimeUtil.isNextThirtyDays(LocalDateTime.now().plusMinutes(1)));
        assertTrue(DateTimeUtil.isNextThirtyDays(LocalDateTime.now().plusDays(30)));

        // is not in the next thirty days
        assertFalse(DateTimeUtil.isNextThirtyDays(LocalDateTime.now().minusMinutes(1)));
        assertFalse(DateTimeUtil.isNextThirtyDays(LocalDateTime.now().minusDays(1)));
        assertFalse(DateTimeUtil.isNextThirtyDays(LocalDateTime.now().plusDays(30).plusMinutes(1)));
        assertFalse(DateTimeUtil.isNextThirtyDays(LocalDateTime.now().plusDays(31)));
    }

    @Test
    public void isLastSevenDays() {
        // is in the last seven days
        assertTrue(DateTimeUtil.isLastSevenDays(LocalDateTime.now().minusMinutes(1)));
        assertTrue(DateTimeUtil.isLastSevenDays(LocalDateTime.now().minusDays(1)));
        assertTrue(DateTimeUtil.isLastSevenDays(LocalDateTime.now().minusDays(7).plusMinutes(1)));

        // is not in the last seven days
        assertFalse(DateTimeUtil.isLastSevenDays(LocalDateTime.now().plusMinutes(1)));
        assertFalse(DateTimeUtil.isLastSevenDays(LocalDateTime.now().minusDays(7).minusMinutes(1)));
        assertFalse(DateTimeUtil.isLastSevenDays(LocalDateTime.now().minusDays(8)));
    }

    @Test
    public void isLastThirtyDays() {
        // is in the last thirty days
        assertTrue(DateTimeUtil.isLastThirtyDays(LocalDateTime.now().minusMinutes(1)));
        assertTrue(DateTimeUtil.isLastThirtyDays(LocalDateTime.now().minusDays(1)));
        assertTrue(DateTimeUtil.isLastThirtyDays(LocalDateTime.now().minusDays(30).plusMinutes(1)));

        // is not in the last thirty days
        assertFalse(DateTimeUtil.isLastThirtyDays(LocalDateTime.now().plusMinutes(1)));
        assertFalse(DateTimeUtil.isLastThirtyDays(LocalDateTime.now().minusDays(30).minusMinutes(1)));
        assertFalse(DateTimeUtil.isLastThirtyDays(LocalDateTime.now().minusDays(31)));
    }

}
