package tutoraid.logic.parser;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.AddProgressCommand;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.student.Progress;


/**
 * Parses input arguments and creates a new AddProgressCommand object
 */
public class AddProgressCommandParser implements Parser<AddProgressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of AddProgressCommand
     * and returns an AddProgressCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProgressCommand parse(String args) throws ParseException {
        try {
            args = args.trim() + " ";
            String [] arguments = args.split(" ", 2);

            Index index = ParserUtil.parseIndex(arguments[0]);
            Progress progress = ParserUtil.parseProgress(arguments[1]);

            return new AddProgressCommand(index, progress);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddProgressCommand.MESSAGE_USAGE), pe);
        }
    }
}
