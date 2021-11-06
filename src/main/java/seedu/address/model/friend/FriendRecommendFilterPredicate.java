package seedu.address.model.friend;

import java.time.DayOfWeek;
import java.util.function.Predicate;

import seedu.address.model.game.Game;
import seedu.address.model.time.HourOfDay;

/**
 * Represents a predicate which tests if friend's schedule is free and has a {@code GameFriendLink} with
 * the provided {@code Game}.
 */
public class FriendRecommendFilterPredicate implements Predicate<Friend> {

    private final HourOfDay hour;
    private final DayOfWeek day;
    private final Game game;

    /**
     * Constructs an instance of predicate which checks if friend's schedule is free
     * during the given {@code hour} and {@code day} and has a {@code GameFriendLink} with
     * the provided {@code game}.
     *
     * @param hour hour to check if friend is free.
     * @param day  day to check if friend is free.
     * @param game game to check if included in friend's {@code GameFriendLink}(s).
     */
    public FriendRecommendFilterPredicate(HourOfDay hour, DayOfWeek day, Game game) {
        this.hour = hour;
        this.day = day;
        this.game = game;
    }

    @Override
    public boolean test(Friend friend) {
        return new FriendGameFriendLinksContainsGamePredicate(game).test(friend)
                && new FriendScheduleFreePredicate(hour, day).test(friend);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof FriendRecommendFilterPredicate)) {
            return false;
        }

        FriendRecommendFilterPredicate otherPredicate = (FriendRecommendFilterPredicate) other;
        return otherPredicate.game.equals(game)
                && otherPredicate.day.equals(day)
                && otherPredicate.hour.equals(hour);
    }
}
