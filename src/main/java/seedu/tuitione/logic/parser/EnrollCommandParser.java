package seedu.tuitione.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.tuitione.logic.commands.EnrollCommand;
import seedu.tuitione.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code EnrollCommand} object.
 */
public class EnrollCommandParser implements Parser<EnrollCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EnrollCommand parse(String args) throws ParseException {
        requireNonNull(args);
        return ParserUtil.parseEnrollArgs(args);
    }
}
