package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;

/**
 * Adds a friend to gitGud.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a friend to the gitGud friends list. "
            + "Parameters: "
            + PREFIX_FRIEND_ID + "FRIEND_ID "
            + PREFIX_FRIEND_NAME + "NAME "
            + "[" + PREFIX_GAME + "GAME]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FRIEND_ID + "myfeely923 "
            + PREFIX_FRIEND_NAME + "Yu Zher "
            + PREFIX_GAME + "CSGO "
            + PREFIX_GAME + "Valorant";

    public static final String MESSAGE_SUCCESS = "New friend added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This friend already exists in the gitGud friends list.";

    private final Friend toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Friend}
     */
    public AddCommand(Friend friend) {
        requireNonNull(friend);
        toAdd = friend;
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
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
