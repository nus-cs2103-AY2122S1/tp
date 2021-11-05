package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.SkillValue;
import seedu.address.model.gamefriendlink.UserName;

public class JsonAdaptedGameFriendLink {
    private final String gameId;
    private final String friendId;
    private final String friendGameUserName;
    private final String skillValue;

    /**
     * Constructs a {@code JsonAdaptedGameFriendLink} with the given gameId, friendId and friendGameUserName.
     */
    @JsonCreator
    public JsonAdaptedGameFriendLink(
            @JsonProperty("gameId") String gameId,
            @JsonProperty("friendId") String friendId,
            @JsonProperty("friendGameUserName") String friendGameUserName,
            @JsonProperty("skillValue") String skillValue) {
        this.gameId = gameId;
        this.friendId = friendId;
        this.friendGameUserName = friendGameUserName;
        this.skillValue = skillValue;
    }

    /**
     * Converts a given {@code GameFriendLink} into this class for Jackson use.
     */
    public JsonAdaptedGameFriendLink(GameFriendLink sourceInstance) {
        friendId = sourceInstance.getFriendId().value;
        gameId = sourceInstance.getGameId().value;
        friendGameUserName = sourceInstance.getUserName().value;
        SkillValue skillValueToSave = sourceInstance.getSkillValue();
        if (skillValueToSave == null) {
            this.skillValue = null;
        } else {
            this.skillValue = skillValueToSave.toString();
        }
    }

    /**
     * Converts this Jackson-friendly adapted GameFriendLink object into the model's {@code GameFriendLink} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted GameFriendLink.
     */
    public GameFriendLink toModelType() throws IllegalValueException {
        if (!GameId.isValidGameId(gameId)) {
            throw new IllegalValueException(GameId.MESSAGE_INVALID_CHARACTERS);
        }
        if (!FriendId.isValidFriendId(friendId)) {
            throw new IllegalValueException(FriendId.MESSAGE_INVALID_CHARACTERS);
        }
        if (!UserName.isValidUserName(friendGameUserName)) {
            throw new IllegalValueException(UserName.MESSAGE_CONSTRAINTS);
        }
        if (skillValue != null
                && !SkillValue.isValidSkillValueString(skillValue)) {
            throw new IllegalValueException(SkillValue.MESSAGE_CONSTRAINTS);
        }

        // if no skill value assigned, set to 0 as default.
        SkillValue loadedSkillValue = skillValue == null ? new SkillValue(0) : ParserUtil.parseSkillValue(skillValue);

        return new GameFriendLink(new GameId(gameId), new FriendId(friendId), new UserName(friendGameUserName),
                loadedSkillValue);
    }
}
