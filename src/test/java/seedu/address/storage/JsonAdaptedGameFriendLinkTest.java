package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.ALICE;
import static seedu.address.testutil.TypicalFriends.BENSON;
import static seedu.address.testutil.TypicalGames.GENSHIN_IMPACT;
import static seedu.address.testutil.TypicalGames.VALORANT;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.SkillValue;
import seedu.address.model.gamefriendlink.UserName;

public class JsonAdaptedGameFriendLinkTest {
    private static final String VALID_FRIEND_USERNAME_RACHEL = "R@chel";
    private static final String VALID_FRIEND_USERNAME_JAMIE = "JAMIEFLAMIE";
    private static final String INVALID_FRIEND_USERNAME = "R@staurantC! \n next line";
    private static final String VALID_FRIEND_ID_BENSON = BENSON.getFriendId().toString();
    private static final String VALID_FRIEND_ID_ALICE = ALICE.getFriendId().toString();
    private static final String INVALID_FRIEND_ID = "id with spaces";
    private static final String VALID_GAME_ID_VALORANT = VALORANT.getGameId().value;
    private static final String VALID_GAME_ID_GENSHIN = GENSHIN_IMPACT.getGameId().value;
    private static final String INVALID_GAME_ID = "GAMEWITH***SPECIALCH@R";
    private static final List<JsonAdaptedGameFriendLink> VALID_GAME_FRIEND_LINK = BENSON.getGameFriendLinks()
            .values().stream()
            .map(JsonAdaptedGameFriendLink::new)
            .collect(Collectors.toList());
    private static final String VALID_SKILL_VALUE_STRING_ONE = "1";
    private static final String VALID_SKILL_VALUE_STRING_TEN = "10";
    private static final String VALID_SKILL_VALUE_STRING_ZERO = "0";
    private static final String INVALID_SKILL_VALUE_STRING_OUT_LOWER_BOUNDS = "-1";

    @Test
    public void sourceConstructorWorks_validGameFriendLinkSourceInstance_createsCorrectJsonAdaptedGameFriendLink()
            throws Exception {
        GameFriendLink gameFriendLinkSource = new GameFriendLink(new GameId(VALID_GAME_ID_VALORANT),
                new FriendId(VALID_FRIEND_ID_BENSON), new UserName(VALID_FRIEND_USERNAME_RACHEL));
        JsonAdaptedGameFriendLink gameFriendLink = new JsonAdaptedGameFriendLink(gameFriendLinkSource);
        assertEquals(new GameFriendLink(new GameId(VALID_GAME_ID_VALORANT), new FriendId(VALID_FRIEND_ID_BENSON),
                new UserName(VALID_FRIEND_USERNAME_RACHEL)), gameFriendLink.toModelType());
    }

    @Test
    public void toModelType_validGameFriendLinkDetails_returnsCorrectGameFriendLink() throws Exception {
        JsonAdaptedGameFriendLink gameFriendLink = new JsonAdaptedGameFriendLink(VALID_GAME_ID_VALORANT,
                VALID_FRIEND_ID_BENSON, VALID_FRIEND_USERNAME_RACHEL, VALID_SKILL_VALUE_STRING_ZERO);
        assertEquals(new GameFriendLink(new GameId(VALID_GAME_ID_VALORANT), new FriendId(VALID_FRIEND_ID_BENSON),
                new UserName(VALID_FRIEND_USERNAME_RACHEL), new SkillValue(0)), gameFriendLink.toModelType());

        JsonAdaptedGameFriendLink gameFriendLinkTwo = new JsonAdaptedGameFriendLink(VALID_GAME_ID_GENSHIN,
                VALID_FRIEND_ID_ALICE, VALID_FRIEND_USERNAME_JAMIE, VALID_SKILL_VALUE_STRING_TEN);
        assertNotEquals(new GameFriendLink(new GameId(VALID_GAME_ID_VALORANT), new FriendId(VALID_FRIEND_ID_BENSON),
                new UserName(VALID_FRIEND_USERNAME_RACHEL)), gameFriendLinkTwo.toModelType());
        assertEquals(new GameFriendLink(new GameId(VALID_GAME_ID_GENSHIN), new FriendId(VALID_FRIEND_ID_ALICE),
                new UserName(VALID_FRIEND_USERNAME_JAMIE), new SkillValue(10)), gameFriendLinkTwo.toModelType());
    }

    @Test
    public void toModelType_invalidFriendId_throwsIllegalValueException() {
        JsonAdaptedGameFriendLink gameFriendLink = new JsonAdaptedGameFriendLink(VALID_GAME_ID_VALORANT,
                INVALID_FRIEND_ID, VALID_FRIEND_USERNAME_RACHEL, VALID_SKILL_VALUE_STRING_ONE);
        String expectedMessage = FriendId.MESSAGE_INVALID_CHARACTERS;
        assertThrows(IllegalValueException.class, expectedMessage, gameFriendLink::toModelType);
    }

    @Test
    public void toModelType_invalidFriendUsername_throwsIllegalValueException() {
        JsonAdaptedGameFriendLink gameFriendLink = new JsonAdaptedGameFriendLink(VALID_GAME_ID_VALORANT,
                VALID_FRIEND_ID_BENSON, INVALID_FRIEND_USERNAME, VALID_SKILL_VALUE_STRING_ZERO);
        String expectedMessage = UserName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, gameFriendLink::toModelType);
    }

    @Test
    public void toModelType_invalidGameId_throwsIllegalValueException() {
        JsonAdaptedGameFriendLink gameFriendLink = new JsonAdaptedGameFriendLink(INVALID_GAME_ID,
                VALID_FRIEND_ID_BENSON, INVALID_FRIEND_USERNAME, VALID_SKILL_VALUE_STRING_ONE);
        String expectedMessage = GameId.MESSAGE_INVALID_CHARACTERS;
        assertThrows(IllegalValueException.class, expectedMessage, gameFriendLink::toModelType);
    }

    @Test
    public void toModelType_invalidSkillValue_throwsIllegalValueException() {

    }
}
