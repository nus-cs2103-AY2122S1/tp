package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EaddCommand;
import seedu.address.logic.commands.EaddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.member.*;
import seedu.address.model.position.Position;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;


/**
 * Parses input arguments and creates a new EaddCommand object
 */
public class EaddCommandParser implements Parser<EaddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EaddCommand
     * and returns an EaddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EaddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_DATE, PREFIX_MEMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EaddCommand.MESSAGE_USAGE));
        }

        EventName name = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());
        EventDate date = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get());
        Set<Index> indexList = ParserUtil.parseIndices(argMultimap.getAllValues(PREFIX_MEMBER));

        Event event = new Event(name, date);

        return new EaddCommand(event, indexList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
