package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.position.FindPositionCommand;
import seedu.address.model.position.FindPositionCommandPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindPositionCommandParser implements Parser<FindPositionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPositionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_POSITION_STATUS);


        FindPositionCommandPredicate findPositionCommandPredicate = new FindPositionCommandPredicate();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            findPositionCommandPredicate.setTitleKeywords(ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_TITLE)
                    .get()));
        }

        if (argMultimap.getValue(PREFIX_POSITION_STATUS).isPresent()) {
            findPositionCommandPredicate.setPositionStatusKeywords(
                    ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_POSITION_STATUS).get()));
        }


        if (!findPositionCommandPredicate.isAnyField()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPositionCommand.MESSAGE_USAGE));
        }

        return new FindPositionCommand(findPositionCommandPredicate);
    }

}
