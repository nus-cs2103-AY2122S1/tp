package seedu.address.model.friend;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import seedu.address.model.friend.exceptions.GameLinkNotFoundException;
import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.SkillValue;
import seedu.address.model.time.HourOfDay;
import seedu.address.model.time.exceptions.InvalidHourOfDayException;

/**
 * Represents a Friend in the gitGud friend's list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Friend {
    // Identity fields
    // used to uniquely identify each Friend.
    private final FriendId friendId;
    private final FriendName friendName;
    private final Schedule schedule;

    // Data fields
    private final Map<GameId, GameFriendLink> gameFriendLinks = new HashMap<>();

    /**
     * Constructs a {@code Friend}.
     * Every field must be present and not null.
     *
     * @param friendId        a valid friend id.
     * @param friendName      a valid friend name.
     * @param gameFriendLinks a map of game-friend links of this friend.
     * @param schedule        Schedule of friend.
     */
    public Friend(FriendId friendId, FriendName friendName, Map<GameId, GameFriendLink> gameFriendLinks,
                  Schedule schedule) {
        requireAllNonNull(friendId, gameFriendLinks);
        this.friendId = friendId;
        this.friendName = friendName == null ? FriendName.DEFAULT_FRIEND_NAME : friendName;
        this.gameFriendLinks.putAll(gameFriendLinks);
        this.schedule = schedule;
    }

    /**
     * Overloaded constructor using only friendId and friendName.
     *
     * @param friendId   Unique id of friend.
     * @param friendName Name of friend.
     */
    public Friend(FriendId friendId, FriendName friendName) {
        requireAllNonNull(friendId);
        this.friendId = friendId;
        this.friendName = friendName == null ? FriendName.DEFAULT_FRIEND_NAME : friendName;
        this.schedule = new Schedule();
    }

    /**
     * Updates the skill value for the {@code GameFriendLink} with the given {@code gameId} and linked to
     * this friend.
     *
     * @param gameId     valid gameId which is already linked to this friend.
     * @param skillValue value to update friend's skill value to.
     * @throws GameLinkNotFoundException thrown when gameId provided is not linked to this friend.
     */
    public void updateGameFriendLinkSkillValue(GameId gameId, SkillValue skillValue) throws GameLinkNotFoundException {
        if (!gameFriendLinks.containsKey(gameId)) {
            throw new GameLinkNotFoundException();
        }

        gameFriendLinks.get(gameId).setSkillValue(skillValue);
    }

    /**
     * Links friend with the game in gameFriendLink
     *
     * @param gameFriendLink gameFriendLink which contains the game to link to, the username and also the skill value.
     */
    public void link(GameFriendLink gameFriendLink) {
        requireNonNull(gameFriendLink);
        GameId gameId = gameFriendLink.getGameId();
        this.gameFriendLinks.put(gameId, gameFriendLink);
    }

    /**
     * Removes the link between the friend and the game provided.
     *
     * @param game game to unlink from.
     */
    public void unlink(Game game) {
        GameId gameId = game.getGameId();
        this.gameFriendLinks.remove(gameId);
    }

    public FriendId getFriendId() {
        return friendId;
    }

    /**
     * Returns the number of games contained inside the friend's GameFriendLinks.
     *
     * @return Number of games.
     */
    public int getNumberOfGames() {
        return getGameFriendLinks().size();
    }

    public FriendName getFriendName() {
        return friendName;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Returns an immutable game-friend-link map, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Map<GameId, GameFriendLink> getGameFriendLinks() {
        return Collections.unmodifiableMap(gameFriendLinks);
    }

    /**
     * Returns true if the friend is currently associated with the game provided.
     *
     * @param game Game to check.
     * @return True if the friend is associated with the game.
     */
    public boolean hasGameAssociation(Game game) {
        return gameFriendLinks.containsKey(game.getGameId());
    }

    /**
     * Returns true if both friends have same friendId.
     *
     * @return boolean result of equals.
     */
    public boolean isSameFriendId(Friend friend) {
        return this.friendId.equals(friend.getFriendId());
    }

    /**
     * Checks whether a friend is available at the specified hour and day of the week.
     *
     * @param hour      The hour to check availability.
     * @param dayOfWeek The day of the week to check availability.
     * @return Boolean value representing the availability of the friend.
     * @throws InvalidHourOfDayException when an invalid hour is given.
     * @throws InvalidDayTimeException   when an invalid day is given.
     */
    public boolean isFriendScheduleFree(HourOfDay hour, DayOfWeek dayOfWeek)
            throws InvalidHourOfDayException, InvalidDayTimeException {
        return this.schedule.isTimeSlotAvailable(hour.getHour(), dayOfWeek.getValue());
    }

    /**
     * Returns true if both friends have the same friendId, name and games.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Friend)) {
            return false;
        }

        Friend otherFriend = (Friend) other;
        return otherFriend.getFriendId().equals(getFriendId())
                && otherFriend.getGameFriendLinks().equals(getGameFriendLinks())
                && otherFriend.getFriendName().equals(getFriendName())
                && otherFriend.getSchedule().equals(getSchedule());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Friend ID: ")
                .append(getFriendId())
                .append("; ")
                .append("Name: ")
                .append(getFriendName())
                .append("; ");

        Collection<GameFriendLink> gameFriendLinks = getGameFriendLinks().values();
        if (!gameFriendLinks.isEmpty()) {
            builder.append("Games: ");
            gameFriendLinks.forEach(builder::append);
            builder.append(" ");
        }

        builder.append(schedule);
        return builder.toString();
    }
}
