package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CalledCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the input argument and creates a new CalledCommand object
 */
public class CalledCommandParser implements Parser<CalledCommand> {

    /**
     * Parses the given {@code String} of argument and returns a CalledCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CalledCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;

        index = ParserUtil.parseIndex(argMultimap.getPreamble(), CalledCommand.MESSAGE_USAGE);

        return new CalledCommand(index);
    }
}
