package seedu.address.logic.parser;


import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.Shortcut;

/**
 * Parses input arguments and creates a new DeleteAliasCommand object.
 */
public class DeleteAliasCommandParser implements Parser<DeleteAliasCommand> {

    @Override
    public DeleteAliasCommand parse(String args) throws ParseException {
        Shortcut shortcut = ParserUtil.parseShortcut(args);
        return new DeleteAliasCommand(shortcut);
    }
}
