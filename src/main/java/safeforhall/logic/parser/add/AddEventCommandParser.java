package safeforhall.logic.parser.add;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static safeforhall.logic.parser.CliSyntax.PREFIX_DATE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_NAME;
import static safeforhall.logic.parser.CliSyntax.PREFIX_RESIDENTS;
import static safeforhall.logic.parser.CliSyntax.PREFIX_TIME;
import static safeforhall.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.stream.Stream;

import safeforhall.logic.commands.add.AddEventCommand;
import safeforhall.logic.parser.ArgumentMultimap;
import safeforhall.logic.parser.ArgumentTokenizer;
import safeforhall.logic.parser.Parser;
import safeforhall.logic.parser.ParserUtil;
import safeforhall.logic.parser.Prefix;
import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.ResidentList;
import safeforhall.model.event.Venue;

/**
 * Parses input arguments and creates a new AddEventCommand object
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns an AddEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME,
                        PREFIX_VENUE, PREFIX_CAPACITY, PREFIX_RESIDENTS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE,
                PREFIX_TIME, PREFIX_VENUE, PREFIX_CAPACITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());
        EventDate eventDate = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get());
        EventTime eventTime = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_TIME).get());
        Venue venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get());
        Capacity capacity = ParserUtil.parseCapacity(argMultimap.getValue(PREFIX_CAPACITY).get());
        ResidentList residents = ParserUtil.parseResidents(argMultimap.getValue(PREFIX_RESIDENTS)
                .orElse(ResidentList.DEFAULT_LIST));

        Event event = new Event(eventName, eventDate, eventTime, venue, capacity, residents);
        return new AddEventCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
