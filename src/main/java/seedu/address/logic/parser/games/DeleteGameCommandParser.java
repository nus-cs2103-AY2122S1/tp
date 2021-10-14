package seedu.address.logic.parser.games;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_DELETE;

import java.util.NoSuchElementException;

import seedu.address.logic.commands.games.DeleteGameCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.game.GameId;

public class DeleteGameCommandParser implements Parser<DeleteGameCommand> {
    private GameId gameId;
    private ArgumentMultimap argMultimap;

    @Override
    public DeleteGameCommand parse(String args) throws ParseException {
        try {
            argMultimap = ArgumentTokenizer.tokenize(args, FLAG_DELETE);
            gameId = ParserUtil.parseGameId(argMultimap.getValue(FLAG_DELETE).get());
            return new DeleteGameCommand(gameId);
        } catch (ParseException | NoSuchElementException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteGameCommand.MESSAGE_USAGE), e);
        }
    }
}
