package seedu.address.model.gamefriendlink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USER_NAME_OMEGA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGameFriendLinks.APEX_AMY_DRACO_LINK;
import static seedu.address.testutil.TypicalGameFriendLinks.CSGO_AMY_DRACO_LINK;
import static seedu.address.testutil.TypicalGameFriendLinks.CSGO_AMY_DRACO_SKILL_SIX_LINK;
import static seedu.address.testutil.TypicalGameFriendLinks.CSGO_AMY_DRACO_SKILL_TWO_LINK;
import static seedu.address.testutil.TypicalGameFriendLinks.CSGO_AMY_OMEGA_LINK;
import static seedu.address.testutil.TypicalGameFriendLinks.CSGO_BOB_DRACO_LINK;

import java.util.BitSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;
import seedu.address.testutil.GameFriendLinkBuilder;

public class GameFriendLinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GameFriendLink(null,
                new FriendId(VALID_FRIEND_ID_AMY), new UserName(VALID_USER_NAME_OMEGA)));

        assertThrows(NullPointerException.class, () -> new GameFriendLink(new GameId(VALID_GAME_ID_CSGO),
                null, new UserName(VALID_USER_NAME_OMEGA)));

        assertThrows(NullPointerException.class, () -> new GameFriendLink(new GameId(VALID_GAME_ID_CSGO),
                new FriendId(VALID_FRIEND_ID_AMY), null));
    }

    @Test
    public void equals() {
        // same values -> equals
        GameFriendLink minecraftCopy = new GameFriendLinkBuilder(CSGO_AMY_DRACO_LINK).build();
        assertEquals(CSGO_AMY_DRACO_LINK, minecraftCopy);

        // same object -> equals
        assertEquals(CSGO_AMY_DRACO_LINK, CSGO_AMY_DRACO_LINK);

        // null -> notEquals
        assertNotEquals(null, CSGO_AMY_DRACO_LINK);

        // different type -> notEquals
        assertNotEquals(CSGO_AMY_DRACO_LINK, new BitSet());

        // different friendId -> notEquals
        assertNotEquals(CSGO_AMY_DRACO_LINK, CSGO_BOB_DRACO_LINK);

        // different gameId -> notEquals
        assertNotEquals(CSGO_AMY_DRACO_LINK, APEX_AMY_DRACO_LINK);

        // different username -> notEquals
        assertNotEquals(CSGO_AMY_DRACO_LINK, CSGO_AMY_OMEGA_LINK);

        // different skill value -> notEquals
        assertNotEquals(CSGO_AMY_DRACO_SKILL_SIX_LINK, CSGO_AMY_DRACO_SKILL_TWO_LINK);
    }
}
