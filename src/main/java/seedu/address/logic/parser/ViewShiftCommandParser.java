package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;
import static seedu.address.logic.parser.ParserUtil.parseDayOfWeekAndSlot;
import static seedu.address.logic.parser.ParserUtil.parseDayOfWeekAndTime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.ViewShiftCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Period;


/**
 * Class representing the find schedule command parser.
 */
public class ViewShiftCommandParser implements Parser<ViewShiftCommand> {

    public static final String INVALID_VIEW_SHIFT_COMMAND =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewShiftCommand.HELP_MESSAGE);
    public static final ParseException INVALID_VIEW_SHIFT_COMMAND_EXCEPTION =
            new ParseException(INVALID_VIEW_SHIFT_COMMAND);
    private static final String INVALID_NUMBER_OF_DATES = "Wrong number of dates input. Expecting 0 or 1, "
            + "received %d date inputs.";


    private DayOfWeek currDayOfWeek = DayOfWeek.from(LocalDate.now());
    private LocalTime currTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
    public final ViewShiftCommand errorCommand = new ViewShiftCommand(currDayOfWeek,
            ViewShiftCommand.INVALID_SLOT_NUMBER_INDICATING_EMPTY_PREFIXES, currTime,
            new Period(LocalDate.now()));


    @Override
    public ViewShiftCommand parse(String args) throws ParseException {
        // If it is empty, return a viewShift with the current day and time
        if (args.trim().equals("")) {
            return errorCommand;
        }

        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DASH_DAY_SHIFT, PREFIX_DASH_TIME, PREFIX_DATE);
        checkPrefixes(argMultimap);

        int slotNum = ViewShiftCommand.INVALID_SLOT_NUMBER;
        DayOfWeek dayOfWeek = null; // should not be null when ViewShiftCommand object is created
        LocalTime time = null;
        LocalDate[] dates = DateTimeUtil.getDisplayedDateArray();
        try {
            // remove the prefix, then parse
            if (argMultimap.getValue(PREFIX_DASH_TIME).isPresent()) {
                String timeInput = argMultimap.getValue(PREFIX_DASH_TIME).get();
                String parsedArg = parseDayOfWeekAndTime(timeInput);
                String[] parsedArgArray = parsedArg.split("-");
                dayOfWeek = DayOfWeek.valueOf(parsedArgArray[0].toUpperCase());
                time = LocalTime.parse(parsedArgArray[1], DateTimeFormatter.ofPattern("HH:mm"));
                // slotNum will remain null
            }

            if (argMultimap.getValue(PREFIX_DASH_DAY_SHIFT).isPresent()) {
                String shiftInput = argMultimap.getValue(PREFIX_DASH_DAY_SHIFT).get();
                String parsedArg = parseDayOfWeekAndSlot(shiftInput); // returns [day]-[slot]
                String[] parsedArgArray = parsedArg.split("-");
                dayOfWeek = DayOfWeek.valueOf(parsedArgArray[0].toUpperCase());
                slotNum = Integer.parseInt(parsedArgArray[1]);
                // time remains as INVALID_SLOT_NUMBER
            }
            if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
                dates = ParserUtil.extractTupleDates(argMultimap);
            }
        } catch (ParseException pe) {
            throw INVALID_VIEW_SHIFT_COMMAND_EXCEPTION;
        }
        if (argMultimap.getAllValues(PREFIX_DATE).size() != 1
                && argMultimap.getAllValues(PREFIX_DATE).size() != 0) {
            throw new ParseException(String.format(INVALID_NUMBER_OF_DATES,
                    argMultimap.getAllValues(PREFIX_DATE).size()));
        }

        return new ViewShiftCommand(dayOfWeek, slotNum, time, new Period(dates[0], dates[1]));
    }

    private void checkPrefixes(ArgumentMultimap argMultimap) throws ParseException {
        // Exactly one of PREFIX_DASH_DAY_SHIFT or PREFIX_DASH_TIME must exist
        if (!arePrefixesPresent(argMultimap, PREFIX_DASH_DAY_SHIFT)
                && !arePrefixesPresent(argMultimap, PREFIX_DASH_TIME)) {
            throw INVALID_VIEW_SHIFT_COMMAND_EXCEPTION;
        }
        if (arePrefixesPresent(argMultimap, PREFIX_DASH_TIME)
                && arePrefixesPresent(argMultimap, PREFIX_DASH_DAY_SHIFT)) {
            throw INVALID_VIEW_SHIFT_COMMAND_EXCEPTION;
        }
    }
}
