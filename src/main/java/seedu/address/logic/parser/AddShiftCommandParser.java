package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE_SHIFT;

import java.time.DayOfWeek;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddShiftCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Slot;

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
                ArgumentTokenizer.tokenize(args, PREFIX_DAY_SHIFT, PREFIX_TYPE_SHIFT, PREFIX_SLOT_SHIFT);

        Index index;
        DayOfWeek dayOfWeek = ParserUtil.parseDayOfWeek(argMultimap.getValue(PREFIX_DAY_SHIFT).get());
        Slot slot = ParserUtil.parseSlot(argMultimap.getValue(PREFIX_SLOT_SHIFT).get());

        if (!arePrefixesPresent(argMultimap, PREFIX_DAY_SHIFT, PREFIX_SLOT_SHIFT)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShiftCommand.MESSAGE_USAGE), pe);
        }
        return new AddShiftCommand(index, dayOfWeek, slot);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
