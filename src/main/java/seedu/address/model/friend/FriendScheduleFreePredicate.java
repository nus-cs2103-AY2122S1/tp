package seedu.address.model.friend;

import java.time.DayOfWeek;
import java.util.function.Predicate;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.time.HourOfDay;
import seedu.address.model.time.exceptions.InvalidHourOfDayException;

public class FriendScheduleFreePredicate implements Predicate<Friend> {

    private final HourOfDay hour;
    private final DayOfWeek day;

    /**
     * Constructs a predicate instance which checks if Friend's schedule is available
     * for given hour and day of week.
     *
     * @param hour hour of day to check availability for.
     * @param day  day of week to check availability for.
     */
    public FriendScheduleFreePredicate(HourOfDay hour, DayOfWeek day) {
        this.hour = hour;
        this.day = day;
    }

    @Override
    public boolean test(Friend friend) {
        try {
            return friend.isFriendScheduleFree(hour, day);
        } catch (InvalidHourOfDayException | InvalidDayTimeException e) { // unreachable but necessary catch block
            // return false since if hour or day is invalid, there should be no matches
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FriendScheduleFreePredicate)) {
            return false;
        }

        FriendScheduleFreePredicate otherPredicate = (FriendScheduleFreePredicate) other;
        return otherPredicate.hour.equals(hour) && otherPredicate.day.equals(day);
    }
}
