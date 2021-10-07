package seedu.address.logic.commands.friends;

import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_ID;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_NAME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class FriendCommand extends Command {
    private static final String COMMAND_WORD = "friend";
    public static final String MESSAGE_USAGE =
            "To add a friend: \n" + COMMAND_WORD
                    + " --id FRIEND_ID [--name \"NAME\"]" + ": Adds a friend to "
                    + "the " + "gitGud friends " + "list. "
                    + "Parameters: "
                    + FLAG_FRIEND_ID + " FRIEND_ID "
                    + PREFIX_FRIEND_NAME + " NAME"
                    + "Example: " + COMMAND_WORD + " "
                    + FLAG_FRIEND_ID + "myfeely923 "
                    + FLAG_FRIEND_NAME + "Yu Zher " + "\n"
                    + "To delete a friend: \n"
                    + COMMAND_WORD
                    + " --delete: Deletes the person identified by the friend id"
                    + ".\n"
                    + "Parameters: FRIEND_ID (must be an existing friend id)\n"
                    + "Example: " + COMMAND_WORD + " --delete Draco";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
