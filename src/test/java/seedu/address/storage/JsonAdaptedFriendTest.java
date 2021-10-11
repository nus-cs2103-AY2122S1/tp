package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedFriend.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.friend.FriendName;

// TODO: Update tests for FriendId and Games based on UG specs
public class JsonAdaptedFriendTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_GAME = "R@staurantC!ty";

    private static final String VALID_FRIEND_ID = BENSON.getFriendId().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final List<JsonAdaptedGame> VALID_GAMES = BENSON.getGames().stream()
            .map(gameFriendLink -> gameFriendLink.getGameId().value)
            .map(JsonAdaptedGame::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedFriend person = new JsonAdaptedFriend(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedFriend person =
                new JsonAdaptedFriend(VALID_FRIEND_ID, INVALID_NAME, VALID_GAMES);
        String expectedMessage = FriendName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFriend person = new JsonAdaptedFriend(VALID_FRIEND_ID, null, VALID_GAMES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FriendName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGames_throwsIllegalValueException() {
        List<JsonAdaptedGame> invalidGames = new ArrayList<>(VALID_GAMES);
        invalidGames.add(new JsonAdaptedGame(INVALID_GAME));
        JsonAdaptedFriend person =
                new JsonAdaptedFriend(VALID_FRIEND_ID, VALID_NAME, invalidGames);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
