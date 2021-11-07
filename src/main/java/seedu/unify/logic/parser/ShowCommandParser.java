package seedu.unify.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.stream.Stream;

import seedu.unify.logic.commands.FindCommand;
import seedu.unify.logic.commands.ShowCommand;
import seedu.unify.logic.parser.exceptions.ParseException;
import seedu.unify.model.task.Date;


/**
 * Parses input arguments and creates a new FindCommand object
 */

public class ShowCommandParser implements Parser<ShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.

     * @throws seedu.unify.logic.parser.exceptions.ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);
        String trimmedArgs;
        if (arePrefixesPresent(argMultimap, PREFIX_DATE)) {
            Date supplied = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
            return new ShowCommand(supplied);
        } else {
            trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
            return new ShowCommand(Integer.parseInt(trimmedArgs));
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
