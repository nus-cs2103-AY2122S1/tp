package seedu.address.logic.parser.member;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ABSENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.member.MlistCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class MlistCommandParser implements Parser<MlistCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ElistmCommand
     * and returns a ElistmCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MlistCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            return new MlistCommand();
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_INDEX,
                PREFIX_ATTEND, PREFIX_ABSENT);
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_EVENT_INDEX)
            || ParserUtil.arePrefixesPresent(argMultimap, PREFIX_ATTEND, PREFIX_ABSENT)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MlistCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_INDEX).get());

        if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_ATTEND)) {
            return new MlistCommand(index, true);
        } else if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_ABSENT)) {
            return new MlistCommand(index, false);
        } else {
            return new MlistCommand(index);
        }
    }
}
