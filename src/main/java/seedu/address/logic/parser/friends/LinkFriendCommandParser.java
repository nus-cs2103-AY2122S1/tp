package seedu.address.logic.parser.friends;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_LINK;
import static seedu.address.logic.parser.CliSyntax.FLAG_USERNAME;
import static seedu.address.logic.parser.ParserUtil.areFlagsPresent;

import seedu.address.logic.commands.friends.LinkFriendCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.UserName;

public class LinkFriendCommandParser implements Parser<LinkFriendCommand> {
    private FriendId friendId;
    private GameId gameId;
    private UserName userName;

    /**
     * Parses the given {@code String} of arguments in the context of the LinkCommand
     * and returns an LinkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkFriendCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_LINK, FLAG_GAME, FLAG_USERNAME);

        // All fields must be present.
        if (!areFlagsPresent(argMultimap, FLAG_LINK, FLAG_GAME, FLAG_USERNAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkFriendCommand.MESSAGE_USAGE));
        }

        friendId = ParserUtil.parseFriendId(argMultimap.getValue(FLAG_LINK).get());
        gameId = ParserUtil.parseGameId(argMultimap.getValue(FLAG_GAME).get());
        userName = ParserUtil.parseUserName(argMultimap.getValue(FLAG_USERNAME).get());

        return new LinkFriendCommand(friendId, gameId, userName);
    }

}
