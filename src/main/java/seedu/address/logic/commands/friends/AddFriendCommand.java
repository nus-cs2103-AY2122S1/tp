package seedu.address.logic.commands.friends;

import seedu.address.logic.commands.*;
import seedu.address.logic.commands.exceptions.*;
import seedu.address.model.*;
import seedu.address.model.friend.*;

import static java.util.Objects.*;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddFriendCommand extends FriendCommand {
    public static final String COMMAND_WORD = "friend --id";
    public static final String MESSAGE_SUCCESS = "New friend added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This friend already exists in the gitGud friends list.";
    public static final FriendName DEFAULT_FRIEND_NAME = new FriendName("No name assigned");

    private final Friend toAdd;

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
