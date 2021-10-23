package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewTaskListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ViewTaskListCommand} object.
 */
public class ViewTaskListCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code ViewTaskListCommand} and returns a ViewTaskListCommand object
     * for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTaskListCommand parse(String args) throws ParseException {
        args = args.trim();
        try {
            if (args.equals("-A")) {
                System.out.println("HERE");
                return new ViewTaskListCommand();
            } else {
                Index index = ParserUtil.parseIndex(args);
                return new ViewTaskListCommand(index);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTaskListCommand.MESSAGE_USAGE), pe);
        }
    }
}
