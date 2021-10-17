package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tuitione.logic.commands.FilterCommand;
import seedu.tuitione.logic.parser.exceptions.ParseException;
import seedu.tuitione.model.student.Grade;

/**
 * Parses input arguments and creates a new {@code FilterCommand} object.
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (!Grade.isValidGrade(trimmedArgs)) {
            throw new ParseException(Grade.GRADE_MESSAGE_CONSTRAINTS);
        }
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return new FilterCommand(new Grade(trimmedArgs));
    }
}
