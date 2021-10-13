package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.FriendsList;
import seedu.address.model.GamesList;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.ReadOnlyGamesList;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.friend.gamefriendlink.GameFriendLink;
import seedu.address.model.friend.gamefriendlink.UserName;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Friend[] getSampleFriends() {
        return new Friend[] {
            createFriend("AlexY123", "Alex Yeoh",
                "Valorant", "Minecraft"),
            createFriend("BernieSanders", "Bernice Yu",
                "RocketLeague", "Doom"),
            createFriend("ChickenTender", "Charlotte Oliveiro",
                "Halo", "Valorant"),
            createFriend("Davidz", "David Li",
                "ApexLegends"),
            createFriend("II3", "Irfan Ibrahim",
                "CSGO"),
            createFriend("RoyJoy", "Roy Balakrishnan",
                "Valorant", "CSGO")
        };
    }

    public static Game[] getSampleGames() {
        return new Game[] {
            new Game(new GameId("CSGO")),
            new Game(new GameId("Valorant")),
            new Game(new GameId("LeagueofLegends"))
        };
    }

    public static ReadOnlyGamesList getSampleGamesList() {
        GamesList sampleAb = new GamesList();
        for (Game sampleGame : getSampleGames()) {
            sampleAb.addGame(sampleGame);
        }
        return sampleAb;
    }

    public static ReadOnlyFriendsList getSampleFriendsList() {
        FriendsList sampleAb = new FriendsList();
        for (Friend sampleFriend : getSampleFriends()) {
            sampleAb.addFriend(sampleFriend);
        }
        return sampleAb;
    }

    /**
     * Create a Friend object with an array of gameIds.
     */
    public static Friend createFriend(String friendIdString, String friendNameString, String ...gameIds) {
        Set<GameFriendLink> gameFriendLinks = new HashSet<>();
        FriendId friendId = new FriendId(friendIdString);
        FriendName friendName = new FriendName(friendNameString);
        for (String gameId : gameIds) {
            gameFriendLinks.add(new GameFriendLink(new GameId(gameId), friendId, new UserName("Username")));
        }
        return new Friend(friendId, friendName, gameFriendLinks);
    }

    public static Set<GameFriendLink> getGameSet(String... strings) {
        //        return Arrays.stream(strings)
        //                .map(Game)
        //                .collect(Collectors.toSet());
        return new HashSet<>();
    }

}
