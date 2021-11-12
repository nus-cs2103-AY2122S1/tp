package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_FRIEND_FLAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.friends.AddFriendCommand;
import seedu.address.logic.commands.friends.AddFriendGameSkillCommand;
import seedu.address.logic.commands.friends.DeleteFriendCommand;
import seedu.address.logic.commands.friends.EditFriendCommand;
import seedu.address.logic.commands.friends.GetFriendCommand;
import seedu.address.logic.commands.friends.LinkFriendCommand;
import seedu.address.logic.commands.friends.ListFriendCommand;
import seedu.address.logic.commands.friends.ScheduleFriendCommand;
import seedu.address.logic.commands.friends.UnlinkFriendCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input for friends.
 */
public class FriendCommandParser implements Parser<Command> {
    public static final String COMMAND_WORD = "friend";

    /**
     * Parses the given {@code String} of arguments in the context of the FriendCommand
     * and returns a FriendCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public Command parse(String arguments) throws ParseException {
        String[] userInput = arguments.strip().split(" ");

        switch (userInput[0]) {
        case AddFriendCommand.COMMAND_WORD:
            return new AddFriendCommandParser().parse(arguments);

        case AddFriendGameSkillCommand.COMMAND_WORD:
            return new AddFriendGameSkillCommandParser().parse(arguments);

        case EditFriendCommand.COMMAND_WORD:
            return new EditFriendCommandParser().parse(arguments);

        case DeleteFriendCommand.COMMAND_WORD:
            return new DeleteFriendCommandParser().parse(arguments);

        case ScheduleFriendCommand.COMMAND_WORD:
            return new ScheduleFriendCommandParser().parse(arguments);

        case GetFriendCommand.COMMAND_WORD:
            return new GetFriendCommandParser().parse(arguments);

        case ListFriendCommand.COMMAND_WORD:
            return new ListFriendCommandParser().parse(arguments);

        case LinkFriendCommand.COMMAND_WORD:
            return new LinkFriendCommandParser().parse(arguments);

        case UnlinkFriendCommand.COMMAND_WORD:
            return new UnlinkFriendCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_FRIEND_FLAG);
        }
    }

}
