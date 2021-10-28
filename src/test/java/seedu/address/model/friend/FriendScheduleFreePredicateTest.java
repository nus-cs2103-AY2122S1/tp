package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.time.HourOfDay;
import seedu.address.testutil.FriendBuilder;

public class FriendScheduleFreePredicateTest {

    @Test
    public void equals() {
        HourOfDay hourZero = new HourOfDay(0);
        HourOfDay hourTwentyThree = new HourOfDay(23);
        DayOfWeek dayOfWeekMonday = DayOfWeek.of(1);
        DayOfWeek dayOfWeekSunday = DayOfWeek.of(7);


        FriendScheduleFreePredicate friendScheduleFreePredicateOne =
            new FriendScheduleFreePredicate(hourZero, dayOfWeekMonday);
        FriendScheduleFreePredicate friendScheduleFreePredicateSameFields =
            new FriendScheduleFreePredicate(hourZero, dayOfWeekMonday);

        // same object -> equal
        assertEquals(friendScheduleFreePredicateOne, friendScheduleFreePredicateOne);

        // same fields -> equal
        assertEquals(friendScheduleFreePredicateOne, friendScheduleFreePredicateSameFields);

        // different types -> not equal
        assertNotEquals(1, friendScheduleFreePredicateOne);

        // null -> not equal
        assertNotEquals(null, friendScheduleFreePredicateOne);

        // different hourOfDay -> not equal
        FriendScheduleFreePredicate differentHourPredicate = new FriendScheduleFreePredicate(
            hourTwentyThree, dayOfWeekMonday);
        assertNotEquals(friendScheduleFreePredicateOne, differentHourPredicate);

        // different dayOfWeek -> not equal
        FriendScheduleFreePredicate differentDayPredicate = new FriendScheduleFreePredicate(
            hourZero, dayOfWeekSunday);
        assertNotEquals(friendScheduleFreePredicateOne, differentDayPredicate);
    }

    @Test
    public void test_freeDayAndTime_returnsTrue() throws InvalidDayTimeException {
        Schedule tenToTwelveFree = new Schedule();
        tenToTwelveFree.setScheduleDay(1, "10", "12", true);
        Friend freeFriendDuringTenToTwelveMonday = new FriendBuilder().withFriendId("misc")
            .withSchedule(tenToTwelveFree).build();

        FriendScheduleFreePredicate isFreeAtTenMonday = new FriendScheduleFreePredicate(new HourOfDay(10),
            DayOfWeek.of(1));
        assertTrue(isFreeAtTenMonday.test(freeFriendDuringTenToTwelveMonday));

        FriendScheduleFreePredicate isFreeAtElevenMonday = new FriendScheduleFreePredicate(new HourOfDay(11),
            DayOfWeek.of(1));
        assertTrue(isFreeAtElevenMonday.test(freeFriendDuringTenToTwelveMonday));
    }

    @Test
    public void test_notFreeDateTime_returnsFalse() throws InvalidDayTimeException {
        Schedule tenToTwelveFree = new Schedule();
        tenToTwelveFree.setScheduleDay(1, "10", "12", true);
        Friend freeFriendDuringTenToTwelveMonday = new FriendBuilder().withFriendId("misc")
            .withSchedule(tenToTwelveFree).build();

        // negative test for invalid hour but valid day
        FriendScheduleFreePredicate isFreeAtNineMonday = new FriendScheduleFreePredicate(new HourOfDay(9),
            DayOfWeek.of(1));
        assertFalse(isFreeAtNineMonday.test(freeFriendDuringTenToTwelveMonday));

        FriendScheduleFreePredicate isFreeAtTwelveMonday = new FriendScheduleFreePredicate(new HourOfDay(12),
            DayOfWeek.of(1));
        assertFalse(isFreeAtTwelveMonday.test(freeFriendDuringTenToTwelveMonday));

        // negative test for invalid hour but valid day
        FriendScheduleFreePredicate isFreeAtTenTuesDay = new FriendScheduleFreePredicate(new HourOfDay(10),
            DayOfWeek.of(2));
        assertFalse(isFreeAtNineMonday.test(freeFriendDuringTenToTwelveMonday));

        FriendScheduleFreePredicate isFreeAtTenSunday = new FriendScheduleFreePredicate(new HourOfDay(10),
            DayOfWeek.of(7));
        assertFalse(isFreeAtTwelveMonday.test(freeFriendDuringTenToTwelveMonday));
    }
}
