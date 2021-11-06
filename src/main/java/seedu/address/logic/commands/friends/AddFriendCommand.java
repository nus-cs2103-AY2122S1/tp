package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CMD_FRIEND;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;

public class AddFriendCommand extends Command {
    public static final String COMMAND_WORD = "--add";

    public static final String MESSAGE_USAGE = "Format: "
            + CMD_FRIEND + " " + FLAG_ADD + "FRIEND_ID [" + FLAG_FRIEND_NAME + "NAME]\n"
            + "Example: "
            + CMD_FRIEND + " " + FLAG_ADD + "myfeely923 " + FLAG_FRIEND_NAME + "Yu Zher";
    public static final String MESSAGE_SUCCESS_ADD_FRIEND = "Added friend - FRIEND_ID: %1$s";
    public static final String MESSAGE_DUPLICATE_FRIEND_ID = "A friend with the same FRIEND_ID already exists in "
            + "the friends list.";

    private final Friend toAdd;

    /**
     * Constructor for AddFriendCommand that takes in the friend to add as the argument.
     *
     * @param friend The friend to be added.
     */
    public AddFriendCommand(Friend friend) {
        requireNonNull(friend);
        this.toAdd = friend;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFriendWithId(toAdd.getFriendId())) {
            throw new CommandException(MESSAGE_DUPLICATE_FRIEND_ID);
        }

        model.addFriend(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS_ADD_FRIEND,
                toAdd.getFriendId()), CommandType.FRIEND_ADD);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddFriendCommand // instanceof handles nulls
                && toAdd.equals(((AddFriendCommand) other).toAdd));
    }
}
