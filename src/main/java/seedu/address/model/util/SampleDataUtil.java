package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.FriendsList;
import seedu.address.model.GamesList;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.ReadOnlyGamesList;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.friend.Schedule;
import seedu.address.model.friend.exceptions.InvalidDayTimeException;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.GameFriendLink;
import seedu.address.model.gamefriendlink.UserName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 * The gameFriendLinkSet for each friend must be empty to prevent mismatched game and friend data
 * in the event of storage load failure.
 */
public class SampleDataUtil {
    // include private constructor for utility class
    private SampleDataUtil() { }

    public static Friend[] getSampleFriends() {
        Game[] sampleGames = getSampleGames();
        Schedule[] sampleSchedules = getSampleSchedules();
        return new Friend[]{
                createSampleFriend("AlexY123", "Alex Yeoh", sampleSchedules[0],
                        sampleGames[1], sampleGames[3]),
                createSampleFriend("BernieSanders", "Bernice Yu", sampleSchedules[1],
                        sampleGames[5], sampleGames[4]),
                createSampleFriend("ChickenTender", "Charlotte Oliveiro", sampleSchedules[2],
                        sampleGames[6], sampleGames[1]),
                createSampleFriend("Davidz", "David Li", sampleSchedules[2],
                        sampleGames[7]),
                createSampleFriend("II3", "Irfan Ibrahim", sampleSchedules[1],
                        sampleGames[0]),
                createSampleFriend("RoyJoy", "Roy Balakrishnan", sampleSchedules[0],
                        sampleGames[1], sampleGames[0])
        };
    }

    public static Game[] getSampleGames() {
        return new Game[] {
            new Game(new GameId("CSGO")),
            new Game(new GameId("Valorant")),
            new Game(new GameId("LeagueofLegends")),
            new Game(new GameId("Minecraft")),
            new Game(new GameId("Doom")),
            new Game(new GameId("RocketLeague")),
            new Game(new GameId("Halo")),
            new Game(new GameId("ApexLegends"))
        };
    }

    public static Schedule[] getSampleSchedules() {
        try {
            Schedule scheduleOne = new Schedule();
            scheduleOne.setScheduleDay(0, "1800", "2200", true);

            Schedule scheduleTwo = new Schedule();
            scheduleTwo.setScheduleDay(2, "0200", "1600", true);
            scheduleTwo.setScheduleDay(3, "0300", "1600", true);
            scheduleTwo.setScheduleDay(4, "0400", "1600", true);

            Schedule scheduleThree = new Schedule();
            scheduleThree.setScheduleDay(5, "0800", "2200", true);
            scheduleThree.setScheduleDay(6, "0900", "2200", true);

            return new Schedule[] {
                    scheduleOne,
                    scheduleTwo,
                    scheduleThree
            };

        } catch (InvalidDayTimeException e) {
            return new Schedule[] {
                    new Schedule(),
                    new Schedule(),
                    new Schedule()
            };
        }

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
     *
     * Sample friends can only have GameFriendLinks to games which exist in the sample games list and must have unique
     * FriendIds.
     */
    public static Friend createSampleFriend(String friendIdString, String friendNameString, Schedule schedule, Game... games) {
        FriendId friendId = new FriendId(friendIdString);

        Set<GameFriendLink> gameFriendLinks = new HashSet<>();
        FriendName friendName = new FriendName(friendNameString);
        for (Game game : games) {
            // only link game and friend if game exists
            if (validateSampleGameExists(game)) {
                gameFriendLinks.add(new GameFriendLink(game.getGameId(), friendId, new UserName("Username")));
            }
        }
        return new Friend(friendId, friendName, gameFriendLinks, schedule);
    }

    private static boolean validateSampleGameExists(Game game) {
        return Arrays.asList(getSampleGames()).contains(game);
    }

    public static Set<GameFriendLink> getGameFriendLinkSet(GameFriendLink... gameFriendLinks) {
        return new HashSet<>(Arrays.asList(gameFriendLinks));
    }
}
