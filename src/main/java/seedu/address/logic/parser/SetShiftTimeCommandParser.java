package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHIFT_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.SetShiftTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class SetShiftTimeCommandParser implements Parser<SetShiftTimeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddShiftCommand
     * and returns an SetShiftTimeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SetShiftTimeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_DASH_INDEX, PREFIX_DASH_NAME, PREFIX_DAY_SHIFT, PREFIX_SHIFT_TIME, PREFIX_DATE);

        Index index = null;
        Name name = null;
        String shiftDayAndSlot;
        LocalTime[] shiftTimes;


        LocalDate[] dates = DateTimeUtil.getDisplayedDateArray();


        //PREFIX_DAY_SHIFT must exist and exactly one from PREFIX_INDEX and PREFIX_NAME must exist.
        if (!arePrefixesPresent(argMultimap, PREFIX_DAY_SHIFT, PREFIX_SHIFT_TIME)
                || !argMultimap.getPreamble().isEmpty() || (!arePrefixesPresent(argMultimap, PREFIX_DASH_INDEX)
                && !arePrefixesPresent(argMultimap, PREFIX_DASH_NAME))
                || (arePrefixesPresent(argMultimap, PREFIX_DASH_INDEX)
                && arePrefixesPresent(argMultimap, PREFIX_DASH_NAME))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetShiftTimeCommand.MESSAGE_USAGE));
        }

        try {
            if (argMultimap.getValue(PREFIX_DASH_INDEX).isPresent()) {
                if (!ParserUtil.isValidInt(argMultimap.getValue(PREFIX_DASH_INDEX).get())) {
                    throw new ParseException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DASH_INDEX).get());
            }
            if (argMultimap.getValue(PREFIX_DASH_NAME).isPresent()) {
                name = ParserUtil.parseName(argMultimap.getValue(PREFIX_DASH_NAME).get());
            }
            if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
                dates = ParserUtil.extractTupleDates(argMultimap);
            }
            shiftDayAndSlot = ParserUtil.parseDayOfWeekAndSlot(argMultimap.getValue(PREFIX_DAY_SHIFT).get());
            shiftTimes = ParserUtil.parseShiftTime(argMultimap.getValue(PREFIX_SHIFT_TIME).get());

        } catch (ParseException pe) {
            if (pe.getMessage().equals(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX)) {
                throw pe;
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetShiftTimeCommand.MESSAGE_USAGE), pe);
        }
        return new SetShiftTimeCommand(index, name, shiftDayAndSlot, shiftTimes, dates[0], dates[1]);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
