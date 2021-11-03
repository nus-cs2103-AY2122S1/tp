package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EunmarkCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EunmarkCommand object
 */
public class EunmarkCommandParser implements Parser<EunmarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EunmarkCommand
     * and returns a EunmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EunmarkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EVENT_INDEX, PREFIX_MEMBER_INDEX);
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_EVENT_INDEX, PREFIX_MEMBER_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EunmarkCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_INDEX).get());
        Set<Index> memberIndices = ParserUtil.parseIndices(argMultimap.getAllValues(PREFIX_MEMBER_INDEX));
        return new EunmarkCommand(index, memberIndices);
    }
}
