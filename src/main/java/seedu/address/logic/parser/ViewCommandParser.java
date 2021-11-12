package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.INDEX_ARGS_COUNT_STUDENT;
import static seedu.address.logic.parser.ParserUtil.STUDENT_INDEX_ZERO_BASED;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParser implements Parser<ViewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        String[] preamble = ParserUtil.parsePreamble(args);
        if (preamble.length != INDEX_ARGS_COUNT_STUDENT) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseStudentIndex(preamble[STUDENT_INDEX_ZERO_BASED]);
        return new ViewCommand(index);
    }
}
