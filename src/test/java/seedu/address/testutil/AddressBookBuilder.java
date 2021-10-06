package seedu.address.testutil;

import seedu.address.model.FriendsList;
import seedu.address.model.friend.Friend;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private FriendsList friendsList;

    public AddressBookBuilder() {
        friendsList = new FriendsList();
    }

    public AddressBookBuilder(FriendsList friendsList) {
        this.friendsList = friendsList;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Friend friend) {
        friendsList.addFriend(friend);
        return this;
    }

    public FriendsList build() {
        return friendsList;
    }
}
