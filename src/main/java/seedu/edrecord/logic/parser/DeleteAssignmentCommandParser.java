package seedu.edrecord.logic.parser;

import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.DeleteAssignmentCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteAssignmentCommand object
 */
public class DeleteAssignmentCommandParser implements Parser<DeleteAssignmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAssignmentCommand
     * and returns a DeleteAssignmentCommand object for execution.
     * @return the DeleteAssignmentCommand corresponding to the user input.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteAssignmentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteAssignmentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAssignmentCommand.MESSAGE_USAGE), pe);
        }
    }

}
