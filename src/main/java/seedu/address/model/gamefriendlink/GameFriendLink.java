package seedu.address.model.gamefriendlink;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;

/**
 * Represents an association between a {@code Friend} and a {@code Game} that are present in the gitGud database.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class GameFriendLink {

    private final GameId gameId;
    private final FriendId friendId;
    private final UserName userName;
    private SkillValue skillValue;

    /**
     * Constructs a {@code GameFriendLink} with SkillValue set to value 0.
     * Every field except skillValue must be present and not null.
     *
     * @param gameId   the valid game id of an existing {@code Game}.
     * @param friendId the valid friend id of an existing {@code Friend}.
     * @param userName a valid username.
     */
    public GameFriendLink(GameId gameId, FriendId friendId, UserName userName) {
        requireAllNonNull(gameId, friendId, userName);
        this.gameId = gameId;
        this.friendId = friendId;
        this.userName = userName;
        this.skillValue = new SkillValue(0);
    }

    /**
     * Constructs a {@code GameFriendLink}.
     * Every field except skillValue must be present and not null.
     *
     * @param gameId     the valid game id of an existing {@code Game}.
     * @param friendId   the valid friend id of an existing {@code Friend}.
     * @param userName   a valid username.
     * @param skillValue a valid skill value or null.
     */
    public GameFriendLink(GameId gameId, FriendId friendId, UserName userName, SkillValue skillValue) {
        requireAllNonNull(gameId, friendId, userName, skillValue);
        this.gameId = gameId;
        this.friendId = friendId;
        this.userName = userName;
        this.skillValue = skillValue;
    }

    /**
     * Returns the {@Code GameId} of the {@Code GameFriendLink}.
     *
     * @return {@Code GameId} of the {@Code GameFriendLink}.
     */
    public GameId getGameId() {
        return gameId;
    }

    /**
     * Returns the {@Code GameId} of the {@Code GameFriendLink}.
     *
     * @return {@Code GameId} of the {@Code GameFriendLink}.
     */
    public FriendId getFriendId() {
        return friendId;
    }

    /**
     * Returns the {@Code UserName} of the {@Code GameFriendLink}.
     *
     * @return {@Code UserName} of the {@Code GameFriendLink}.
     */
    public UserName getUserName() {
        return userName;
    }

    /**
     * Returns the {@Code SkillValue} of the {@Code GameFriendLink}.
     *
     * @return {@Code SkillValue} of the {@Code GameFriendLink}.
     */
    public SkillValue getSkillValue() {
        return skillValue;
    }

    /**
     * Sets a new skill value to the {@Code GameFriendLink}.
     *
     * @param skillValue The new skill value to assign to the GameFriendLink.
     */
    public void setSkillValue(SkillValue skillValue) {
        this.skillValue = skillValue;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GameFriendLink)) {
            return false;
        }

        GameFriendLink otherLink = (GameFriendLink) other;
        return otherLink.getGameId().equals(getGameId())
                && otherLink.getFriendId().equals(getFriendId())
                && otherLink.getUserName().equals(getUserName())
                && otherLink.getSkillValue().equals(getSkillValue());
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Game ID: ")
                .append(getGameId())
                .append(" Username: ")
                .append(getUserName())
                .append(" Skill: ")
                .append(getSkillValue());

        return builder.toString();
    }
}
