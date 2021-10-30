package tutoraid.logic.parser;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.ViewLessonCommand;
import tutoraid.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewLessonCommand object
 */
public class ViewLessonCommandParser implements Parser<ViewLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewLessonCommand
     * and returns a ViewLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewLessonCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewLessonCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ViewLessonCommand.MESSAGE_USAGE), pe);
        }
    }

}
