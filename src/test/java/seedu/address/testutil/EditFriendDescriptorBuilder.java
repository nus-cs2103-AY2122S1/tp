package seedu.address.testutil;

import seedu.address.logic.commands.friends.EditFriendCommand;
import seedu.address.logic.commands.friends.EditFriendCommand.EditFriendDescriptor;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendName;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditFriendDescriptorBuilder {

    private EditFriendDescriptor descriptor;

    public EditFriendDescriptorBuilder() {
        descriptor = new EditFriendCommand.EditFriendDescriptor();
    }

    public EditFriendDescriptorBuilder(EditFriendCommand.EditFriendDescriptor descriptor) {
        this.descriptor = new EditFriendCommand.EditFriendDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditFriendDescriptorBuilder(Friend friend) {
        descriptor = new EditFriendCommand.EditFriendDescriptor();
        descriptor.setFriendName(friend.getFriendName());
    }

    /**
     * Sets the {@code FriendId} of the {@code EditFriendDescriptor} that we are building.
     */
    public EditFriendDescriptorBuilder withFriendId(String friendId) {
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditFriendDescriptor} that we are building.
     */
    public EditFriendDescriptorBuilder withFriendName(String name) {
        descriptor.setFriendName(new FriendName(name));
        return this;
    }

    public EditFriendCommand.EditFriendDescriptor build() {
        return descriptor;
    }
}
