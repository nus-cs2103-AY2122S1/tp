package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.FriendsList;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.friend.Friend;

/**
 * An Immutable FriendsList that is serializable to JSON format.
 */
@JsonRootName(value = "friends")
class JsonSerializableFriendsList {

    public static final String MESSAGE_DUPLICATE_FRIEND_ID = "Friends list contains duplicate friend(s) "
            + "with the same friendId.";

    private final List<JsonAdaptedFriend> friends = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFriendsList} with the given friends.
     */
    @JsonCreator
    public JsonSerializableFriendsList(@JsonProperty("friends") List<JsonAdaptedFriend> friends) {
        this.friends.addAll(friends);
    }

    /**
     * Converts a given {@code ReadOnlyFriendsList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFriendsList}.
     */
    public JsonSerializableFriendsList(ReadOnlyFriendsList source) {
        friends.addAll(source.getFriendsList().stream().map(JsonAdaptedFriend::new).collect(Collectors.toList()));
    }

    /**
     * Converts this friend list into the model's {@code FriendsList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FriendsList toModelType() throws IllegalValueException {
        FriendsList friendsList = new FriendsList();
        for (JsonAdaptedFriend jsonAdaptedFriend : friends) {
            Friend friend = jsonAdaptedFriend.toModelType();
            if (friendsList.hasFriendWithId(friend.getFriendId())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FRIEND_ID);
            }
            friendsList.addFriend(friend);
        }
        return friendsList;
    }

}
