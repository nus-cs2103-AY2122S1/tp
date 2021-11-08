package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new MarkAssignmentCommand object
 */
public class MarkAssignmentCommandParser implements Parser<MarkAssignmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkAssignmentCommand
     * and returns an MarkAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAssignmentCommand parse(String args) throws ParseException {
        try {
            Index assignmentIndex = ParserUtil.parseIndex(args);
            return new MarkAssignmentCommand(assignmentIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAssignmentCommand.MESSAGE_USAGE), pe);
        }
    }
}
