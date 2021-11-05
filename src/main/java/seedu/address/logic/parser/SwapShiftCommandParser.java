package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.SwapShiftCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new SwapShiftCommand object.
 */
public class SwapShiftCommandParser implements Parser<SwapShiftCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SwapShiftCommand
     * and returns a SwapShiftCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public SwapShiftCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DAY_SHIFT, PREFIX_DASH_NAME, PREFIX_DATE);
        LocalDate[] dates = DateTimeUtil.getDisplayedDateArray();

        // Checks if there are exactly 2 "- n" fields and exactly 2 "d/" fields
        if (argMultimap.getAllValues(PREFIX_DASH_NAME).size() != 2
                || argMultimap.getAllValues(PREFIX_DAY_SHIFT).size() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwapShiftCommand.MESSAGE_USAGE));
        }

        // Checks if the 2 names provided are unique
        if (argMultimap.getAllValues(PREFIX_DASH_NAME).get(0).equals(
                argMultimap.getAllValues(PREFIX_DASH_NAME).get(1))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwapShiftCommand.NON_UNIQUE_NAMES));
        }

        // Checks if the 2 shifts provided are unique
        if (argMultimap.getAllValues(PREFIX_DAY_SHIFT).get(0).equals(
                argMultimap.getAllValues(PREFIX_DAY_SHIFT).get(1))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwapShiftCommand.NON_UNIQUE_SHIFTS));
        }

        List<Name> nameList = new ArrayList<>();
        for (String name : argMultimap.getAllValues(PREFIX_DASH_NAME)) {
            nameList.add(ParserUtil.parseName(name));
        }

        List<String> shiftList = new ArrayList<>();
        for (String shift : argMultimap.getAllValues(PREFIX_DAY_SHIFT)) {
            shiftList.add(ParserUtil.parseDayOfWeekAndSlot(shift));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            dates = ParserUtil.extractTupleDates(argMultimap);
        }

        return new SwapShiftCommand(nameList, shiftList, dates[0], dates[1]);
    }
}
