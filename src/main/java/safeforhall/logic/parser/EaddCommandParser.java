package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CliSyntax.PREFIX_NAME;
import static safeforhall.logic.parser.CliSyntax.PREFIX_DATE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_VENUE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_CAPACITY;

import java.util.stream.Stream;

import safeforhall.logic.commands.EaddCommand;
import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.event.EventName;
import safeforhall.model.event.Event;
import safeforhall.model.event.Venue;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.EventDate;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE,
                        PREFIX_VENUE, PREFIX_CAPACITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE,
                PREFIX_VENUE, PREFIX_CAPACITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EaddCommand.MESSAGE_USAGE));
        }

        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());
        EventDate eventDate = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get());
        Venue venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get());
        Capacity capacity = ParserUtil.parseCapacity(argMultimap.getValue(PREFIX_CAPACITY).get());

        Event event = new Event(eventName, eventDate, venue, capacity);
        return new EaddCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
