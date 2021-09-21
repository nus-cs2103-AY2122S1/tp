package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventDateTimePredicate;
import seedu.address.model.event.EventTime;

public class FilterEventCommandParser implements Parser<FilterEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterEventCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FilterEventCommand parse(String userInput) throws ParseException {
        String[] dateTime = userInput.trim().split(" ");
        if (dateTime.length == 0 || dateTime.length > 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEventCommand.MESSAGE_USAGE));
        }
        if (dateTime.length == 1) { // date only
            if (!EventDate.isValidDate(dateTime[0])) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEventCommand.MESSAGE_USAGE));
            } else {
                return new FilterEventCommand(new EventDateTimePredicate(Arrays.asList(dateTime)));
            }
        } else {
            if (!EventDate.isValidDate(dateTime[0]) || !EventTime.isValidTime(dateTime[1])) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEventCommand.MESSAGE_USAGE));
            } else {
                return new FilterEventCommand(new EventDateTimePredicate(Arrays.asList(dateTime)));
            }
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
