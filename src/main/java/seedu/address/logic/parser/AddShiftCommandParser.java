package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddShiftCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;


public class AddShiftCommandParser implements Parser<AddShiftCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddShiftCommand
     * and returns an AddShiftCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddShiftCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_DAY_SHIFT, PREFIX_NAME);

        Index index = null;
        Name name = null;
        String shiftDayAndSlot;

        //PREFIX_DAY_SHIFT must exist and exactly one from PREFIX_INDEX and PREFIX_NAME must exist.
        if (!arePrefixesPresent(argMultimap, PREFIX_DAY_SHIFT)
                || !argMultimap.getPreamble().isEmpty() || (!arePrefixesPresent(argMultimap, PREFIX_INDEX)
                && !arePrefixesPresent(argMultimap, PREFIX_NAME))
                || (arePrefixesPresent(argMultimap, PREFIX_INDEX)
                        && arePrefixesPresent(argMultimap, PREFIX_NAME))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE));
        }

        try {
            if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            }
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            }
            shiftDayAndSlot = ParserUtil.parseDayOfWeek(argMultimap.getValue(PREFIX_DAY_SHIFT).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE), pe);
        }
        return new AddShiftCommand(index, name, shiftDayAndSlot);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
