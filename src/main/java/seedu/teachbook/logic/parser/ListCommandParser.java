package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.teachbook.logic.commands.ListCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ListCommand} object.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ListCommand}
     * and returns a {@code ListCommand} object for execution.
     *
     * @param args input arguments to be parsed.
     * @return a {@code ListCommand} object as a result of the parsing of the input arguments.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.equals("all")) {
            return new ListCommand(true, false);
        }
        if (trimmedArgs.equals("absent")) {
            return new ListCommand(false, true);
        }
        if (trimmedArgs.isEmpty()) {
            return new ListCommand();
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

}
