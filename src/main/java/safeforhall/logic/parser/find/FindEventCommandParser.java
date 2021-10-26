package safeforhall.logic.parser.find;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import safeforhall.logic.commands.find.FindEventCommand;
import safeforhall.logic.parser.ArgumentMultimap;
import safeforhall.logic.parser.ArgumentTokenizer;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.logic.parser.Parser;
import safeforhall.logic.parser.ParserUtil;
import safeforhall.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindEventCommand object
 */
public class FindEventCommandParser implements Parser<FindEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEventCommand
     * and returns a FindEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DATE,
                        CliSyntax.PREFIX_VENUE, CliSyntax.PREFIX_CAPACITY);

        FindEventCommand.FindCompositePredicate findCompositePredicate =
                new FindEventCommand.FindCompositePredicate();

        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            findCompositePredicate.setEventName(ParserUtil.parseEventName(argMultimap.getValue(CliSyntax.PREFIX_NAME)
                    .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DATE).isPresent()) {
            findCompositePredicate.setEventDate(ParserUtil.parseEventDate(argMultimap.getValue(CliSyntax.PREFIX_DATE)
                    .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_VENUE).isPresent()) {
            findCompositePredicate.setVenue(ParserUtil.parseVenue(argMultimap.getValue(CliSyntax.PREFIX_VENUE)
                    .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_CAPACITY).isPresent()) {
            findCompositePredicate.setCapacity(ParserUtil.parseCapacity(argMultimap.getValue(CliSyntax.PREFIX_CAPACITY)
                    .get()));
        }

        if (!findCompositePredicate.isAnyFieldFiltered()) {
            throw new ParseException(FindEventCommand.MESSAGE_NOT_FILTERED);
        }

        return new FindEventCommand(findCompositePredicate);
    }

}
