package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedFriend.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;

// TODO: Update tests for FriendId and Games based on UG specs
public class JsonAdaptedFriendTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_GAME = "R@staurantC!ty";

    private static final String VALID_FRIEND_ID = BENSON.getFriendId().toString();
    private static final String VALID_NAME_BENSON = BENSON.getName().toString();
    private static final List<JsonAdaptedGameFriendLink> VALID_GAME_FRIEND_LINK = BENSON.getGameFriendLinks().stream()
            .map(JsonAdaptedGameFriendLink::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsFriend() throws Exception {
        JsonAdaptedFriend person = new JsonAdaptedFriend(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedFriend person =
                new JsonAdaptedFriend(VALID_FRIEND_ID, INVALID_NAME, VALID_GAME_FRIEND_LINK);
        String expectedMessage = FriendName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_friendIdNull_throwsIllegalValueException() throws Exception {
        JsonAdaptedFriend person = new JsonAdaptedFriend(null, BENSON.getName().toString(),
                VALID_GAME_FRIEND_LINK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FriendId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFriend person = new JsonAdaptedFriend(VALID_FRIEND_ID, null, VALID_GAME_FRIEND_LINK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FriendName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    // TODO: Check if there can be an invalid gameFriendLink.

    //    @Test
    //    public void toModelType_invalidGameFriendLink_throwsIllegalValueException() {
    //        List<JsonAdaptedGameFriendLink> invalidGameFriendLinks = new ArrayList<>(VALID_GAME_FRIEND_LINK);
    //        invalidGameFriendLinks.add(new JsonAdaptedGameFriendLink(INVALID_GAME));
    //        JsonAdaptedFriend friend =
    //                new JsonAdaptedFriend(VALID_FRIEND_ID, VALID_NAME, invalidGameFriendLinks);
    //        assertThrows(IllegalValueException.class, friend::toModelType);
    //    }

}
