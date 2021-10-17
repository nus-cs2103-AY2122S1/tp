package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteAliasCommand object.
 */
public class DeleteAliasCommandParser implements Parser<DeleteAliasCommand> {

    @Override
    public DeleteAliasCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAliasCommand.MESSAGE_USAGE));
        }
        return new DeleteAliasCommand(trimmedArgs);
    }
}
