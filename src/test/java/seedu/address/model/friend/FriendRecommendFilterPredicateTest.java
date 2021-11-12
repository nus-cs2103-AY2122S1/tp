package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.VALORANT;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.UserName;
import seedu.address.model.time.HourOfDay;
import seedu.address.testutil.FriendBuilder;

public class FriendRecommendFilterPredicateTest {

    @Test
    public void equals() {
        HourOfDay hourZero = new HourOfDay(0);
        HourOfDay hourTwentyThree = new HourOfDay(23);
        DayOfWeek dayOfWeekMonday = DayOfWeek.of(1);
        DayOfWeek dayOfWeekSunday = DayOfWeek.of(7);

        FriendRecommendFilterPredicate friendRecommendFilterPredicateOne =
            new FriendRecommendFilterPredicate(hourZero, dayOfWeekMonday, VALORANT);
        FriendRecommendFilterPredicate friendRecommendFilterPredicateSameFields =
            new FriendRecommendFilterPredicate(hourZero, dayOfWeekMonday, VALORANT);

        // same object -> equal
        assertEquals(friendRecommendFilterPredicateOne, friendRecommendFilterPredicateOne);

        // null -> not equal
        assertNotEquals(friendRecommendFilterPredicateOne, null);

        // different types -> not equal
        assertNotEquals(friendRecommendFilterPredicateOne, "String");

        // same fields -> equal
        assertEquals(friendRecommendFilterPredicateOne, friendRecommendFilterPredicateSameFields);

        // different hourOfDay -> not equal
        FriendRecommendFilterPredicate differentHourPredicate = new FriendRecommendFilterPredicate(
            hourTwentyThree, dayOfWeekMonday, VALORANT);
        assertNotEquals(friendRecommendFilterPredicateOne, differentHourPredicate);

        // different dayOfWeek -> not equal
        FriendRecommendFilterPredicate differentDayPredicate = new FriendRecommendFilterPredicate(
            hourZero, dayOfWeekSunday, VALORANT);
        assertNotEquals(friendRecommendFilterPredicateOne, differentDayPredicate);

        // different game -> not equal
        FriendRecommendFilterPredicate differentGamePredicate = new FriendRecommendFilterPredicate(
            hourZero, dayOfWeekMonday, CSGO);
        assertNotEquals(friendRecommendFilterPredicateOne, differentGamePredicate);
    }

    @Test
    public void test_freeDayAndTimeAndGameExists_returnsTrue() throws InvalidDayTimeException {
        Schedule tenToTwelveFree = new Schedule();
        tenToTwelveFree.setScheduleDay(1, "10", "12", true);
        Friend freeFriendDuringTenToTwelveMonday = new FriendBuilder().withFriendId("misc")
            .withGameFriendLinks(new GameFriendLink(CSGO.getGameId(), new FriendId("misc"),
                new UserName("Tenz"))).withSchedule(tenToTwelveFree).build();

        FriendRecommendFilterPredicate isFreeAtTenMonday = new FriendRecommendFilterPredicate(new HourOfDay(10),
            DayOfWeek.of(1), CSGO);
        assertTrue(isFreeAtTenMonday.test(freeFriendDuringTenToTwelveMonday));

        FriendRecommendFilterPredicate isFreeAtElevenMonday = new FriendRecommendFilterPredicate(new HourOfDay(11),
            DayOfWeek.of(1), CSGO);
        assertTrue(isFreeAtElevenMonday.test(freeFriendDuringTenToTwelveMonday));
    }

    @Test
    public void test_notFreeDateTime_returnsFalse() throws InvalidDayTimeException {
        Schedule tenToTwelveFree = new Schedule();
        tenToTwelveFree.setScheduleDay(1, "10", "12", true);
        Friend freeFriendDuringTenToTwelveMonday = new FriendBuilder().withFriendId("misc")
            .withGameFriendLinks(new GameFriendLink(CSGO.getGameId(), new FriendId("misc"),
                new UserName("Tenz"))).withSchedule(tenToTwelveFree).build();

        // negative test for invalid hour but valid day
        FriendRecommendFilterPredicate isFreeAtNineMonday = new FriendRecommendFilterPredicate(new HourOfDay(9),
            DayOfWeek.of(1), CSGO);
        assertFalse(isFreeAtNineMonday.test(freeFriendDuringTenToTwelveMonday));

        FriendRecommendFilterPredicate isFreeAtTwelveMonday = new FriendRecommendFilterPredicate(new HourOfDay(12),
            DayOfWeek.of(1), CSGO);
        assertFalse(isFreeAtTwelveMonday.test(freeFriendDuringTenToTwelveMonday));

        // negative test for invalid hour but valid day
        FriendRecommendFilterPredicate isFreeAtTenTuesDay = new FriendRecommendFilterPredicate(new HourOfDay(10),
            DayOfWeek.of(2), CSGO);
        assertFalse(isFreeAtNineMonday.test(freeFriendDuringTenToTwelveMonday));

        FriendRecommendFilterPredicate isFreeAtTenSunday = new FriendRecommendFilterPredicate(new HourOfDay(10),
            DayOfWeek.of(7), CSGO);
        assertFalse(isFreeAtTwelveMonday.test(freeFriendDuringTenToTwelveMonday));
    }

    @Test
    public void test_notValidGame_returnsFalse() throws InvalidDayTimeException {
        Schedule tenToTwelveFree = new Schedule();
        tenToTwelveFree.setScheduleDay(1, "10", "12", true);
        Friend freeFriendDuringTenToTwelveMonday = new FriendBuilder().withFriendId("misc")
            .withGameFriendLinks(new GameFriendLink(CSGO.getGameId(), new FriendId("misc"),
                new UserName("Tenz"))).withSchedule(tenToTwelveFree).build();

        // negative test invalid game - not in games list
        FriendRecommendFilterPredicate isFreeAtTenMondayWithInvalidGame =
            new FriendRecommendFilterPredicate(new HourOfDay(10),
                DayOfWeek.of(1), VALORANT);
        assertFalse(isFreeAtTenMondayWithInvalidGame.test(freeFriendDuringTenToTwelveMonday));

    }
}
