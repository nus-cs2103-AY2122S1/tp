package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TlistCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;

public class TlistCommandParser implements Parser<TlistCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TlistCommand
     * and returns a TlistCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TlistCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMBER_ID);
        if (!arePrefixesPresent(argMultimap, PREFIX_MEMBER_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TlistCommand.MESSAGE_USAGE));
        }

        Index memberID = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER_ID).get());
        return new TlistCommand(memberID);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
