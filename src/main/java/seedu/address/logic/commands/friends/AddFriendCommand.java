package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;

public class AddFriendCommand extends Command {
    public static final String COMMAND_WORD = "--add";
    public static final String MESSAGE_SUCCESS_ADD_FRIEND = "New friend added - %1$s";
    public static final String MESSAGE_DUPLICATE_FRIEND_ID = "A friend with the same FRIEND_ID already exists in "
            + "the friends list.";
    public static final String MESSAGE_USAGE = "To add a friend: \n"
            + COMMAND_WORD
            + " FRIEND_ID [--name NAME]: "
            + "Adds a friend to the gitGud friends list. \n"
            + "Parameters: "
            + "FRIEND_ID ["
            + CliSyntax.FLAG_FRIEND_NAME
            + " NAME]\n"
            + "Example: "
            + COMMAND_WORD + " "
            + "myfeely923 "
            + FLAG_FRIEND_NAME
            + "Yu Zher ";
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
        return new CommandResult(String.format(MESSAGE_SUCCESS_ADD_FRIEND, toAdd), CommandType.FRIEND_ADD);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddFriendCommand // instanceof handles nulls
                && toAdd.equals(((AddFriendCommand) other).toAdd));
    }
}
