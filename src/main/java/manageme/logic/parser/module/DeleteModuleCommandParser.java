package manageme.logic.parser.module;

import static manageme.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import manageme.commons.core.index.Index;
import manageme.logic.commands.module.DeleteModuleCommand;
import manageme.logic.parser.Parser;
import manageme.logic.parser.ParserUtil;
import manageme.logic.parser.exceptions.ParseException;

public class DeleteModuleCommandParser implements Parser<DeleteModuleCommand> {
    @Override
    public DeleteModuleCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteModuleCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE), pe);
        }
    }
}
