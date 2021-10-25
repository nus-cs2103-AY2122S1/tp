package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_ID;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EdelCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EdeleteCommand object
 */
public class EdelCommandParser implements Parser<EdelCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EdeleteCommand
     * and returns a EdeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EdelCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_ID);
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_EVENT_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EdelCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_ID).get());
        return new EdelCommand(index);
    }
}
