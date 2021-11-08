package seedu.address.logic.parser.member;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.member.MdelCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class MdelCommandParser implements Parser<MdelCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MdelCommand
     * and returns a MdelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MdelCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMBER_INDEX);
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_MEMBER_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MdelCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER_INDEX).get());
        return new MdelCommand(index);
    }

}
