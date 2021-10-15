package seedu.address.logic.parser.games;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_GET;

import seedu.address.logic.commands.games.GetGameCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.game.GameId;

/**
 * Parses input arguments and creates a new GetGameCommand object
 */
public class GetGameCommandParser implements Parser<GetGameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetGameCommand
     * and returns a GetGameCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetGameCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_GET);

        if (!ParserUtil.areFlagsPresent(argMultimap, FLAG_GET)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetGameCommand.MESSAGE_USAGE));
        }

        String keyword = argMultimap.getValue(FLAG_GET).get().trim();
        if (!GameId.isValidGameId(keyword)) {
            throw new ParseException(GameId.MESSAGE_INVALID_CHARACTERS_IN_GAME_ID);
        }

        return new GetGameCommand(new GameId(keyword));
    }
}
