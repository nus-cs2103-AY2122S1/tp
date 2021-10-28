package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteOrgCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteOrgCommand object
 */
public class DeleteOrgCommandParser implements Parser<DeleteOrgCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteOrgCommand
     * and returns a DeleteOrgCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteOrgCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteOrgCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrgCommand.MESSAGE_USAGE), pe);
        }
    }

}
