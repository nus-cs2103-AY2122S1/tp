package seedu.address.testutil;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USER_NAME_OMEGA;

import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.SkillValue;
import seedu.address.model.gamefriendlink.UserName;

/**
 * A utility class to help with building GameFriendLink objects.
 */
public class GameFriendLinkBuilder {

    public static final String DEFAULT_GAME_ID = VALID_GAME_ID_CSGO;
    public static final String DEFAULT_FRIEND_ID = VALID_FRIEND_ID_AMY;
    public static final String DEFAULT_USERNAME = VALID_USER_NAME_OMEGA;

    private GameId gameId;
    private FriendId friendId;
    private UserName userName;
    private SkillValue skillValue;

    /**
     * Creates a {@code GameFriendLinkBuilder} with the default details.
     */
    public GameFriendLinkBuilder() {
        gameId = new GameId(DEFAULT_GAME_ID);
        friendId = new FriendId(DEFAULT_FRIEND_ID);
        userName = new UserName(DEFAULT_USERNAME);
    }

    /**
     * Initializes the {@code GameFriendLinkBuilder} with the data of {@code gameFriendLinkToCopy}.
     */
    public GameFriendLinkBuilder(GameFriendLink gameFriendLinkToCopy) {
        gameId = gameFriendLinkToCopy.getGameId();
        friendId = gameFriendLinkToCopy.getFriendId();
        userName = gameFriendLinkToCopy.getUserName();
    }

    /**
     * Sets the {@code GameId} of the {@code GameFriendLink} that we are building.
     */
    public GameFriendLinkBuilder withGameId(String gameId) {
        this.gameId = new GameId(gameId);
        return this;
    }

    /**
     * Sets the {@code FriendId} of the {@code GameFriendLink} that we are building.
     */
    public GameFriendLinkBuilder withFriendId(String friendId) {
        this.friendId = new FriendId(friendId);
        return this;
    }

    /**
     * Sets the {@code UserName} of the {@code GameFriendLink} that we are building.
     */
    public GameFriendLinkBuilder withUserName(String userName) {
        this.userName = new UserName(userName);
        return this;
    }

    /**
     * Sets the {@code SkillLevel} of the {@code GameFriendLink} that we are building.
     */
    public GameFriendLinkBuilder withSkillValue(SkillValue skillValue) {
        requireNonNull(skillValue);
        this.skillValue = skillValue;
        return this;
    }

    /**
     * Constructs an instance of {@code GameFriendLink} based on the stored fields of the {@code GameFriendLinkBuilder}.
     * @return constructed GameFriendLink based on stored fields.
     */
    public GameFriendLink build() {
        if (skillValue == null) {
            return new GameFriendLink(gameId, friendId, userName);
        } else {
            return new GameFriendLink(gameId, friendId, userName, skillValue);
        }
    }
}
