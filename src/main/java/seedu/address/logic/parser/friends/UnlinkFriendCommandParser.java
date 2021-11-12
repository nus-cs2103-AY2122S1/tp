package seedu.address.logic.parser.friends;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_UNLINK;
import static seedu.address.logic.parser.ParserUtil.areFlagsPresent;

import seedu.address.logic.commands.friends.UnlinkFriendCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;

public class UnlinkFriendCommandParser implements Parser<UnlinkFriendCommand> {
    private FriendId friendId;
    private GameId gameId;

    /**
     * Parses the given {@code String} of arguments in the context of the UnlinkFriendCommand
     * and returns an UnlinkFriendCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnlinkFriendCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_UNLINK, FLAG_GAME);

        // All fields must be present.
        if (!areFlagsPresent(argMultimap, FLAG_UNLINK, FLAG_GAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnlinkFriendCommand.MESSAGE_USAGE));
        }

        friendId = ParserUtil.parseFriendId(argMultimap.getValue(FLAG_UNLINK).get());
        gameId = ParserUtil.parseGameId(argMultimap.getValue(FLAG_GAME).get());

        return new UnlinkFriendCommand(friendId, gameId);
    }

}
