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
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Friend[] getSampleFriends() {
        return new Friend[] {
            new Friend(new FriendId("AlexY123"), new FriendName("Alex Yeoh"),
                getGameSet("Valorant", "Minecraft")),
            new Friend(new FriendId("BernieSanders"), new FriendName("Bernice Yu"),
                getGameSet("Rocket League", "Doom")),
            new Friend(new FriendId("ChickenTender"), new FriendName("Charlotte Oliveiro"),
                getGameSet("Halo", "Valorant")),
            new Friend(new FriendId("Davidz"), new FriendName("David Li"),
                getGameSet("Apex Legends")),
            new Friend(new FriendId("II3"), new FriendName("Irfan Ibrahim"),
                getGameSet("CSGO")),
            new Friend(new FriendId("RoyJoy"), new FriendName("Roy Balakrishnan"),
                getGameSet("Valorant", "CSGO"))
        };
    }

    public static Game[] getSampleGames() {
        return new Game[] {
                new Game(new GameId("CSGO")),
                new Game(new GameId("Valorant")),
                new Game(new GameId("LeagueofLegends")),
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
     * Returns a tag set containing the list of strings given.
     */
    public static Set<GameFriendLink> getGameSet(String... strings) {
        //        return Arrays.stream(strings)
        //                .map(Game)
        //                .collect(Collectors.toSet());
        return new HashSet<>();
    }

}
