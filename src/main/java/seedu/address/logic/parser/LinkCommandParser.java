package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_SPACE;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME_SPACE;
import static seedu.address.logic.parser.CliSyntax.FLAG_USERNAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.gamefriendlink.UserName;
import seedu.address.model.game.GameId;

public class LinkCommandParser implements Parser<LinkCommand> {
    private FriendId friendId;
    private GameId gameId;
    private UserName userName;

    /**
     * Parses the given {@code String} of arguments in the context of the LinkCommand
     * and returns an LinkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_GAME_SPACE, FLAG_FRIEND_SPACE, FLAG_USERNAME);

        // All fields must be present.
        if (!arePrefixesPresent(argMultimap, FLAG_GAME_SPACE, FLAG_FRIEND_SPACE, FLAG_USERNAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE));
        }

        friendId = ParserUtil.parseFriendId(argMultimap.getValue(FLAG_FRIEND_SPACE).get());
        gameId = ParserUtil.parseGameId(argMultimap.getValue(FLAG_GAME_SPACE).get());
        userName = ParserUtil.parseUserName(argMultimap.getValue(FLAG_USERNAME).get());

        return new LinkCommand(friendId, gameId, userName);
    }

}
