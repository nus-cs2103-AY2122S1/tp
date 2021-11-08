package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EaddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Name;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.event.EventDate;

/**
 * Parses input arguments and creates a new EaddCommand object
 */
public class EaddCommandParser implements Parser<EaddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EaddCommand
     * and returns an EaddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EaddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_DATE, PREFIX_MEMBER_INDEX);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EaddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());
        EventDate date = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get());
        Set<Index> indexList = ParserUtil.parseIndices(argMultimap.getAllValues(PREFIX_MEMBER_INDEX));

        Event event = new Event(name, date);

        return new EaddCommand(event, indexList);
    }
}
