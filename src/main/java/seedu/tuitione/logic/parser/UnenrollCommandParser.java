package seedu.tuitione.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.tuitione.logic.commands.UnenrollCommand;
import seedu.tuitione.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UnenrollCommand object.
 */
public class UnenrollCommandParser implements Parser<UnenrollCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnenrollCommand
     * and returns a UnenrollCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public UnenrollCommand parse(String args) throws ParseException {
        requireNonNull(args);
        return ParserUtil.parseUnenrollArgs(args);
    }
}
