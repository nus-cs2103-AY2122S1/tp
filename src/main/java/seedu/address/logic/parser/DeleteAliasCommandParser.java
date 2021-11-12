package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.Shortcut;

/**
 * Parses input arguments and creates a new DeleteAliasCommand object.
 */
public class DeleteAliasCommandParser implements Parser<DeleteAliasCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAliasCommand
     * and returns a DeleteAliasCommand object for execution.
     *
     * @param args the user input.
     * @return the DeleteAliasCommand object.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public DeleteAliasCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAliasCommand.MESSAGE_USAGE));
        }
        Shortcut shortcut = ParserUtil.parseShortcut(args);
        return new DeleteAliasCommand(shortcut);
    }
}
