package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRESS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.AddProgressCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Progress;

import java.util.stream.Stream;


/**
 * Parses input arguments and creates a new PaidCommand object
 */
public class AddProgressCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of PaidCommand
     * and returns a PaidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProgressCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PROGRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_PROGRESS)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProgressCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        Progress progress = ParserUtil.parseProgress(argMultimap.getValue(PREFIX_PROGRESS).get());

        return new AddProgressCommand(index, progress);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
