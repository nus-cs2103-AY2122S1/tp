package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.teachbook.logic.commands.DeleteClassCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.classobject.ClassNameDescriptor;

/**
 * Parses input arguments and creates a new DeleteClassCommand object.
 */
public class DeleteClassCommandParser implements Parser<DeleteClassCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteClassCommand and returns a
     * DeleteClassCommand object for execution.
     *
     * @param args input arguments to be parsed.
     * @return {@code DeleteClassCommand} as a result of the parsing of the input arguments.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DeleteClassCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE));
        }

        ClassNameDescriptor className = ParserUtil.parseClassNameForLocatingClass(trimmedArgs);

        return new DeleteClassCommand(className);
    }
}
