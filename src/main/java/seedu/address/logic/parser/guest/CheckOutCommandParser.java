package seedu.address.logic.parser.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;

import seedu.address.logic.commands.guest.CheckOutCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class CheckOutCommandParser implements Parser<CheckOutCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckOutCommand
     * and returns an CheckOutCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckOutCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PASSPORT_NUMBER);

        if (!argMultimap.getValue(PREFIX_PASSPORT_NUMBER).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckOutCommand.MESSAGE_USAGE));
        }

        return new CheckOutCommand(ParserUtil.parsePassportNumber(argMultimap.getValue(PREFIX_PASSPORT_NUMBER).get()));
    }

}
