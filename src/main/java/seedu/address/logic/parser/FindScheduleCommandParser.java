package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.ParserUtil.*;

import seedu.address.logic.commands.FindScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Class representing the find schedule command parser.
 */
public class FindScheduleCommandParser implements Parser<FindScheduleCommand> {

    private static final ParseException INVALID_FIND_SCHEDULE_COMMAND =
            new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindScheduleCommand.HELP_MESSAGE));

    @Override
    public FindScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DASH_DAY_SHIFT, PREFIX_DASH_TIME);
        checkPrefixes(argMultimap); // throws an error if prefixes are inputted wrong

        int slotNum = FindScheduleCommand.INVALID_SLOT_NUMBER;
        DayOfWeek dayOfWeek = null; // should not be null when FindScheduleCommand object is created
        LocalTime time = null;

        try {
            // remove the prefix, then parse
            if (argMultimap.getValue(PREFIX_DASH_TIME).isPresent()) {
                String trimmedArgs = args.replace(PREFIX_DASH_TIME.toString(), "").trim();
                String parsedArg = parseDayOfWeekAndTime(trimmedArgs);
                String[] parsedArgArray = parsedArg.split("-");
                dayOfWeek = DayOfWeek.valueOf(parsedArgArray[0].toUpperCase());
                time = LocalTime.parse(parsedArgArray[1], DateTimeFormatter.ofPattern("HH:mm"));
                // slotNum will remain null
            }

            if (argMultimap.getValue(PREFIX_DASH_DAY_SHIFT).isPresent()) {
                String trimmedArgs = args.replace(PREFIX_DASH_DAY_SHIFT.toString(), "").trim()  ;
                String parsedArg = parseDayOfWeekAndSlot(trimmedArgs); // returns [day]-[slot]
                String[] parsedArgArray = parsedArg.split("-");
                dayOfWeek = DayOfWeek.valueOf(parsedArgArray[0].toUpperCase());
                slotNum = Integer.parseInt(parsedArgArray[1]);
                // time remains as INVALID_SLOT_NUMBER
            }

        } catch (ParseException pe) {
            throw INVALID_FIND_SCHEDULE_COMMAND;
        }

        return new FindScheduleCommand(dayOfWeek, slotNum, time);
    }

    private void checkPrefixes(ArgumentMultimap argMultimap) throws ParseException {
        // Exactly one of PREFIX_DASH_DAY_SHIFT or PREFIX_DASH_TIME must exist
        if (!arePrefixesPresent(argMultimap, PREFIX_DASH_DAY_SHIFT)
                && !arePrefixesPresent(argMultimap, PREFIX_DASH_TIME)) {
            throw INVALID_FIND_SCHEDULE_COMMAND;
        }
        if (arePrefixesPresent(argMultimap, PREFIX_DASH_TIME)
                && arePrefixesPresent(argMultimap, PREFIX_DASH_DAY_SHIFT)) {
            throw INVALID_FIND_SCHEDULE_COMMAND;
        }
    }
}
