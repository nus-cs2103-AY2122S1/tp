package seedu.address.logic.parser.member;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.member.MdelCommand;
import seedu.address.logic.commands.member.MlistCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_ID);
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_EVENT_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MlistCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_ID).get());
        return new MlistCommand(index);
    }

}
