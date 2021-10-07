package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendName;

public class AddFriendCommand extends FriendCommand {
    public static final String COMMAND_WORD = "friend --id";
    public static final String MESSAGE_SUCCESS = "New friend added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This friend already exists in the gitGud friends list.";
    public static final FriendName DEFAULT_FRIEND_NAME = new FriendName("No name assigned");

    private final Friend toAdd;

    /**
     * Constructor for AddFriendCommand that takes in the friend to add as the argument.
     * @param friend The friend to be added.
     */
    public AddFriendCommand(Friend friend) {
        requireNonNull(friend);
        this.toAdd = friend;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFriend(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addFriend(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddFriendCommand // instanceof handles nulls
                && toAdd.equals(((AddFriendCommand) other).toAdd));
    }
}
