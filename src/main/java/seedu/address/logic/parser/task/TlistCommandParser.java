package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERDUE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TlistCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class TlistCommandParser implements Parser<TlistCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TlistCommand
     * and returns a TlistCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TlistCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_MEMBER_INDEX, PREFIX_DONE, PREFIX_OVERDUE);
        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_MEMBER_INDEX)
                || ParserUtil.arePrefixesPresent(argMultimap, PREFIX_DONE, PREFIX_OVERDUE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TlistCommand.MESSAGE_USAGE));
        }

        Index memberId = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER_INDEX).get());
        if (argMultimap.getValue(PREFIX_DONE).isPresent()) {
            if (argMultimap.getValue(PREFIX_DONE).get().equals("y")
                    || argMultimap.getValue(PREFIX_DONE).get().equals("n")) {
                return new TlistCommand(memberId, argMultimap.getValue(PREFIX_DONE).get());
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TlistCommand.MESSAGE_USAGE));
        } else if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_OVERDUE)) {
            return new TlistCommand(memberId, "overdue");
        } else {
            return new TlistCommand(memberId);
        }
    }
}
