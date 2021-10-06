package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;

/**
 * Parses input arguments and creates a new AddEventCommand object
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns a AddEventCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME);
        if (!arePrefixesPresent(argMultiMap, PREFIX_NAME, PREFIX_DATE) || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }
        EventName eventName = ParserUtil.parseEventName(argMultiMap.getValue(PREFIX_NAME).get());
        EventDate eventDate = ParserUtil.parseEventDate(argMultiMap.getValue(PREFIX_DATE).get());
        EventTime eventTime = ParserUtil.parseEventTime(argMultiMap.getValue(PREFIX_TIME).orElse(""));
        Event event = new Event(eventName, eventDate, eventTime);
        return new AddEventCommand(event);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
