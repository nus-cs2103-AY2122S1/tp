package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditFriendDescriptor;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.gamefriendlink.GameFriendLink;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditFriendDescriptorBuilder {

    private EditFriendDescriptor descriptor;

    public EditFriendDescriptorBuilder() {
        descriptor = new EditCommand.EditFriendDescriptor();
    }

    public EditFriendDescriptorBuilder(EditCommand.EditFriendDescriptor descriptor) {
        this.descriptor = new EditCommand.EditFriendDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditFriendDescriptorBuilder(Friend friend) {
        descriptor = new EditCommand.EditFriendDescriptor();
        descriptor.setFriendId(friend.getFriendId());
        descriptor.setFriendName(friend.getName());
        descriptor.setGames(friend.getGameFriendLinks());
    }

    /**
     * Sets the {@code FriendId} of the {@code EditFriendDescriptor} that we are building.
     */
    public EditFriendDescriptorBuilder withFriendId(String friendId) {
        descriptor.setFriendId(new FriendId(friendId));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditFriendDescriptor} that we are building.
     */
    public EditFriendDescriptorBuilder withFriendName(String name) {
        descriptor.setFriendName(new FriendName(name));
        return this;
    }

    /**
     * Parses the {@code games} into a {@code Set<GameFriendLink>} and set it to the {@code EditFriendDescriptor}
     * that we are building.
     */
    public EditFriendDescriptorBuilder withGames(String... games) {
        //        Set<GameFriendLink> gameSet = Stream.of(games).map(Game::new).collect(Collectors.toSet());
        Set<GameFriendLink> gameSet = new HashSet<>();
        descriptor.setGames(gameSet);
        return this;
    }

    public EditCommand.EditFriendDescriptor build() {
        return descriptor;
    }
}
