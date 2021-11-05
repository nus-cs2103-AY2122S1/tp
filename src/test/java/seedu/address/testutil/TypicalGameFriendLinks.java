package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_APEX_LEGENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USER_NAME_DRACO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USER_NAME_OMEGA;
import static seedu.address.testutil.TypicalFriends.ALICE_FRIEND_ID;
import static seedu.address.testutil.TypicalFriends.BENSON_FRIEND_ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.SkillValue;
import seedu.address.model.gamefriendlink.UserName;

/**
 * A utility class containing a list of {@code GameFriendLink} objects to be used in tests.
 */
public class TypicalGameFriendLinks {

    // for testing skill value persistence
    public static final GameFriendLink ALICE_CSGO_SKILL_VALUE_EIGHT_LINK = new GameFriendLink(
            TypicalGames.CSGO.getGameId(), new FriendId(ALICE_FRIEND_ID),
            new UserName(VALID_USER_NAME_OMEGA), new SkillValue(8));
    public static final GameFriendLink BOB_CSGO_SKILL_VALUE_ZERO_LINK = new GameFriendLink(
            TypicalGames.CSGO.getGameId(), new FriendId(BENSON_FRIEND_ID),
            new UserName(VALID_USER_NAME_OMEGA), new SkillValue(0));

    public static final GameFriendLink CSGO_AMY_DRACO_LINK = new GameFriendLinkBuilder().withGameId(VALID_GAME_ID_CSGO)
            .withFriendId(VALID_FRIEND_ID_AMY).withUserName(VALID_USER_NAME_DRACO).build();
    public static final GameFriendLink CSGO_AMY_OMEGA_LINK = new GameFriendLinkBuilder().withGameId(VALID_GAME_ID_CSGO)
            .withFriendId(VALID_FRIEND_ID_AMY).withUserName(VALID_USER_NAME_OMEGA).build();
    public static final GameFriendLink CSGO_AMY_DRACO_SKILL_SIX_LINK =
            new GameFriendLinkBuilder().withGameId(VALID_GAME_ID_CSGO)
            .withFriendId(VALID_FRIEND_ID_AMY).withUserName(VALID_USER_NAME_DRACO)
            .withSkillValue(new SkillValue(6)).build();
    public static final GameFriendLink CSGO_AMY_DRACO_SKILL_TWO_LINK =
            new GameFriendLinkBuilder().withGameId(VALID_GAME_ID_CSGO)
            .withFriendId(VALID_FRIEND_ID_AMY).withUserName(VALID_USER_NAME_DRACO)
            .withSkillValue(new SkillValue(2)).build();

    public static final GameFriendLink APEX_AMY_DRACO_LINK = new GameFriendLinkBuilder()
            .withGameId(VALID_GAME_ID_APEX_LEGENDS).withFriendId(VALID_FRIEND_ID_AMY)
            .withUserName(VALID_USER_NAME_DRACO).build();
    public static final GameFriendLink APEX_AMY_OMEGA_LINK = new GameFriendLinkBuilder()
            .withGameId(VALID_GAME_ID_APEX_LEGENDS).withFriendId(VALID_FRIEND_ID_AMY)
            .withUserName(VALID_USER_NAME_OMEGA).build();

    public static final GameFriendLink CSGO_BOB_DRACO_LINK = new GameFriendLinkBuilder().withGameId(VALID_GAME_ID_CSGO)
            .withFriendId(VALID_FRIEND_ID_BOB).withUserName(VALID_USER_NAME_DRACO).build();
    public static final GameFriendLink CSGO_BOB_OMEGA_LINK = new GameFriendLinkBuilder().withGameId(VALID_GAME_ID_CSGO)
            .withFriendId(VALID_FRIEND_ID_BOB).withUserName(VALID_USER_NAME_OMEGA).build();

    public static final GameFriendLink APEX_BOB_DRACO_LINK = new GameFriendLinkBuilder()
            .withGameId(VALID_GAME_ID_APEX_LEGENDS).withFriendId(VALID_FRIEND_ID_BOB)
            .withUserName(VALID_USER_NAME_DRACO).build();
    public static final GameFriendLink APEX_BOB_OMEGA_LINK = new GameFriendLinkBuilder()
            .withGameId(VALID_GAME_ID_APEX_LEGENDS).withFriendId(VALID_FRIEND_ID_BOB)
            .withUserName(VALID_USER_NAME_OMEGA).build();

    private TypicalGameFriendLinks() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static Map<GameId, GameFriendLink> getTypicalGameFriendLinksList() {
        Map<GameId, GameFriendLink> map = new HashMap<>();
        for (GameFriendLink gameFriendLink : getTypicalGameFriendLinks()) {
            map.put(gameFriendLink.getGameId(), gameFriendLink);
        }
        return map;
    }

    public static List<GameFriendLink> getTypicalGameFriendLinks() {
        return new ArrayList<>(Arrays.asList(CSGO_AMY_DRACO_LINK, CSGO_AMY_OMEGA_LINK, APEX_AMY_DRACO_LINK,
                APEX_AMY_OMEGA_LINK, CSGO_BOB_DRACO_LINK, CSGO_BOB_OMEGA_LINK, APEX_BOB_DRACO_LINK,
                APEX_BOB_OMEGA_LINK));
    }
}
