package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_APEX_LEGENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USER_NAME_DRACO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USER_NAME_OMEGA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.gamefriendlink.GameFriendLink;

/**
 * A utility class containing a list of {@code GameFriendLink} objects to be used in tests.
 */
public class TypicalGameFriendLinks {

    public static final GameFriendLink CSGO_AMY_DRACO_LINK = new GameFriendLinkBuilder().withGameId(VALID_GAME_ID_CSGO)
            .withFriendId(VALID_FRIEND_ID_AMY).withUserName(VALID_USER_NAME_DRACO).build();
    public static final GameFriendLink CSGO_AMY_OMEGA_LINK = new GameFriendLinkBuilder().withGameId(VALID_GAME_ID_CSGO)
            .withFriendId(VALID_FRIEND_ID_AMY).withUserName(VALID_USER_NAME_OMEGA).build();

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
    public static Set<GameFriendLink> getTypicalGameFriendLinksList() {
        Set<GameFriendLink> set = new HashSet<>();
        for (GameFriendLink game : getTypicalGameFriendLinks()) {
            set.add(game);
        }
        return set;
    }

    public static List<GameFriendLink> getTypicalGameFriendLinks() {
        return new ArrayList<>(Arrays.asList(CSGO_AMY_DRACO_LINK, CSGO_AMY_OMEGA_LINK, APEX_AMY_DRACO_LINK,
                APEX_AMY_OMEGA_LINK, CSGO_BOB_DRACO_LINK, CSGO_BOB_OMEGA_LINK, APEX_BOB_DRACO_LINK,
                APEX_BOB_OMEGA_LINK));
    }
}
