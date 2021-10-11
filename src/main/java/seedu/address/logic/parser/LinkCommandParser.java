package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME_OLD;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;

public class LinkCommandParser implements Parser<LinkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LinkCommand
     * and returns an LinkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_GAME_OLD);

        if (!arePrefixesPresent(argMultimap, FLAG_GAME_OLD)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE));
        }

        List<String> commandSegments = Arrays.asList(args.split(" "));

        FriendId friendId;

        try {
            // first element is whitespace, FRIEND_ID is at the second element
            friendId = ParserUtil.parseFriendId(commandSegments.get(1));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCommand.MESSAGE_USAGE), pe);
        }

        HashMap<String, String> games = ParserUtil.parseGamesAndUsernames(
                argMultimap.getAllValues(FLAG_GAME_OLD));

        return new LinkCommand(friendId, games);
    }

}
