package seedu.address.logic.parser.games;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;

import seedu.address.logic.commands.games.AddGameCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameId;

public class AddGameCommandParser implements Parser<AddGameCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddGameCommand
     * and returns an AddGameCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddGameCommand parse(String args) throws ParseException {
        // assign gameId
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, FLAG_ADD);

        if (!ParserUtil.areFlagsPresent(argumentMultimap, FLAG_ADD)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGameCommand.MESSAGE_USAGE));
        }

        GameId gameId = ParserUtil.parseGameId(argumentMultimap.getValue(FLAG_ADD).get());
        return new AddGameCommand(new Game(gameId));
    }
}
