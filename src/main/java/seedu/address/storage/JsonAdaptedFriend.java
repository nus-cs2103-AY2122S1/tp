package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.gamefriendlink.GameFriendLink;

/**
 * Jackson-friendly version of {@link Friend}.
 */
class JsonAdaptedFriend {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Friend's %s field is missing!";

    private final String friendId;
    private final String friendName;
    private final List<JsonAdaptedGameFriendLink> gameFriendLinks = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedFriend(@JsonProperty("friendId") String friendId,
                             @JsonProperty("friendName") String friendName,
                             @JsonProperty("gameFriendLink") List<JsonAdaptedGameFriendLink> gameFriendLinks) {
        this.friendId = friendId;
        this.friendName = friendName;
        if (gameFriendLinks != null) {
            this.gameFriendLinks.addAll(gameFriendLinks);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedFriend(Friend sourceInstance) {
        friendId = sourceInstance.getFriendId().value;
        friendName = sourceInstance.getName().fullName;
        gameFriendLinks.addAll(sourceInstance.getGameFriendLinks().stream()
                .map(JsonAdaptedGameFriendLink::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Friend toModelType() throws IllegalValueException {
        final List<GameFriendLink> gameFriendLinksList = new ArrayList<>();
        for (JsonAdaptedGameFriendLink gameFriendLink : gameFriendLinks) {
            gameFriendLinksList.add(gameFriendLink.toModelType());
        }

        if (friendId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    FriendId.class.getSimpleName()));
        }
        if (!FriendId.isValidFriendId(friendId)) {
            throw new IllegalValueException(FriendId.MESSAGE_INVALID_CHARACTERS);
        }
        if (friendName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    FriendName.class.getSimpleName()));
        }
        if (!FriendName.isValidName(friendName)) {
            throw new IllegalValueException(FriendName.MESSAGE_CONSTRAINTS);
        }

        final FriendId modelFriendId = new FriendId(friendId);
        final FriendName modelFriendName = new FriendName(friendName);
        final Set<GameFriendLink> gameFriendLinkSet = new HashSet<>(gameFriendLinksList);

        return new Friend(modelFriendId, modelFriendName, gameFriendLinkSet);
    }
}
