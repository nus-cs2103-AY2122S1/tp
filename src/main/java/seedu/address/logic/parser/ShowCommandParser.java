package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand
     * and returns an ShowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_ASSESSMENT);

        if (areRedundantPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_ASSESSMENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        return argMultimap.getValue(PREFIX_NAME).isPresent()
                ? new ShowCommand(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()))
                : argMultimap.getValue(PREFIX_ID).isPresent()
                ? new ShowCommand(ParserUtil.parseID(argMultimap.getValue(PREFIX_ID).get()))
                : new ShowCommand(ParserUtil.parseAssessment(argMultimap.getValue(PREFIX_ASSESSMENT).get()));
    }

    /**
     * Returns true if only one of the prefixes is present in the given {@code ArgumentMultimap}.
     */
    private static boolean areRedundantPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count() > 1;
    }

}
