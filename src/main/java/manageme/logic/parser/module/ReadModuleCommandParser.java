package manageme.logic.parser.module;

import static manageme.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import manageme.commons.core.index.Index;
import manageme.logic.commands.module.ReadModuleCommand;
import manageme.logic.parser.Parser;
import manageme.logic.parser.ParserUtil;
import manageme.logic.parser.exceptions.ParseException;

public class ReadModuleCommandParser implements Parser<ReadModuleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReadModuleCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ReadModuleCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadModuleCommand.MESSAGE_USAGE), pe);
        }
    }
}
