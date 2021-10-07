package seedu.address.logic.parser.friends;
import seedu.address.logic.commands.friends.FriendCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class FriendCommandParser implements Parser<FriendCommand> {
    private static final String DELETE_FLAG = "--delete";

    /**
     * Parses the given {@code String} of arguments in the context of the FriendCommand
     * and returns a FriendCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FriendCommand parse(String args) throws ParseException {
        boolean isDelete = args.contains(DELETE_FLAG);

        if (isDelete) {
            return new DeleteFriendCommandParser().parse(args);
        } else {
            return new AddFriendCommandParser().parse(args);
        }
    }


}
