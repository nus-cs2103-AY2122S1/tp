package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_FLAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_FLAG;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ClearCommandParser implements Parser<ClearCommand> {
    private static final int PERSON_FLAG = 0;
    private static final int EVENT_FLAG = 1;
    private static final String VALIDATION_REGEX = "^all$|^[0-9]*-[0-9]*$";

    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns an ClearCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PERSON_FLAG, PREFIX_EVENT_FLAG);

        boolean isClearingPerson = arePrefixesPresent(argMultimap, PREFIX_PERSON_FLAG);
        boolean isClearingEvent = arePrefixesPresent(argMultimap, PREFIX_EVENT_FLAG);

        assert(!(isClearingEvent && isClearingPerson));

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        } else if (isClearingPerson && isClearingEvent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        } else if (!isClearingPerson && !isClearingEvent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }

        String clearRange;
        int flag;

        if (isClearingPerson) {
            flag = PERSON_FLAG;
            clearRange = argMultimap.getValue(PREFIX_PERSON_FLAG).get();
        } else {
            flag = EVENT_FLAG;
            clearRange = argMultimap.getValue(PREFIX_EVENT_FLAG).get();
        }

        if (!clearRange.matches(VALIDATION_REGEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }

        if (clearRange.equals("all")) {
            return new ClearCommand(flag, 0, null, null);
        } else {
            String[] range = clearRange.split("-");
            assert(range.length == 2);
            Index begin = ParserUtil.parseIndex(range[0]);
            Index end = ParserUtil.parseIndex(range[1]);
            return new ClearCommand(flag, 1, begin, end);
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

