package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.FriendsList;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.gamefriendlink.GameFriendLink;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 * The gameFriendLinkSet for each friend must be empty to prevent mismatched game and friend data
 * in the event of storage load failure.
 */
public class SampleDataUtil {
    public static Friend[] getSampleFriends() {
        return new Friend[] {
            new Friend(new FriendId("AlexY123"), new FriendName("Alex Yeoh"),
                    getGameFriendLinkSet()), new Friend(
                        new FriendId("BernieSanders"), new FriendName("Bernice Yu"),
                    getGameFriendLinkSet()), new Friend(
                        new FriendId("ChickenTender"), new FriendName("Charlotte Oliveiro"),
                    getGameFriendLinkSet()), new Friend(
                        new FriendId("Davidz"), new FriendName("David Li"),
                    getGameFriendLinkSet()), new Friend(
                        new FriendId("II3"), new FriendName("Irfan Ibrahim"),
                    getGameFriendLinkSet()), new Friend(
                        new FriendId("RoyJoy"), new FriendName("Roy Balakrishnan"),
                    getGameFriendLinkSet())
        };
    }

    public static ReadOnlyFriendsList getSampleFriendsList() {
        FriendsList sampleAb = new FriendsList();
        for (Friend sampleFriend : getSampleFriends()) {
            sampleAb.addFriend(sampleFriend);
        }
        return sampleAb;
    }

    /**
     * Returns a GameFriendLink set containing the list of strings given.
     */
    public static Set<GameFriendLink> getGameFriendLinkSet(GameFriendLink... gameFriendLinks) {
        return new HashSet<>(Arrays.asList(gameFriendLinks));
    }

}
