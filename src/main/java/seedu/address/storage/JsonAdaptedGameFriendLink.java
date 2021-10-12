package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.UserName;

public class JsonAdaptedGameFriendLink {
    private final String gameId;
    private final String friendId;
    private final String friendGameUserName;

    /**
     * Constructs a {@code JsonAdaptedGameFriendLink} with the given gameId, friendId and friendGameUserName.
     */
    @JsonCreator
    public JsonAdaptedGameFriendLink(
            @JsonProperty("gameId") String gameId,
            @JsonProperty("friendId") String friendId,
            @JsonProperty("friendGameUserName") String friendGameUserName) {
        this.gameId = gameId;
        this.friendId = friendId;
        this.friendGameUserName = friendGameUserName;
    }

    /**
     * Converts a given {@code GameFriendLink} into this class for Jackson use.
     */
    public JsonAdaptedGameFriendLink(GameFriendLink sourceInstance) {
        friendId = sourceInstance.getFriendId().value;
        gameId = sourceInstance.getGameId().value;
        friendGameUserName = sourceInstance.getUserName().value;
    }

    /**
     * Converts this Jackson-friendly adapted GameFriendLink object into the model's {@code GameFriendLink} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted GameFriendLink.
     */
    public GameFriendLink toModelType() throws IllegalValueException {
        if (!GameId.isValidGameId(gameId)) {
            throw new IllegalValueException(GameId.MESSAGE_CONSTRAINTS);
        }
        if (!FriendId.isValidFriendId(friendId)) {
            throw new IllegalValueException(FriendId.MESSAGE_INVALID_CHARACTERS);
        }
        if (!UserName.isValidUserName(friendGameUserName)) {
            throw new IllegalValueException(UserName.MESSAGE_CONSTRAINTS);
        }
        return new GameFriendLink(new GameId(gameId), new FriendId(friendId), new UserName(friendGameUserName));
    }
}
