package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;

public class EditAppCommandParser implements Parser<EditAppCommand> {

    @Override
    public EditAppCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APP_INDEX, PREFIX_ADDRESS, PREFIX_DATE, PREFIX_TIME,
                        PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_APP_INDEX, PREFIX_ADDRESS, PREFIX_DATE, PREFIX_TIME,
                PREFIX_DESCRIPTION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppCommand.MESSAGE_USAGE));
        }

        Index appIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_APP_INDEX).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        LocalTime time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        String description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        return new EditAppCommand(appIndex, address, date, time, description);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}