package nustracker.logic.parser;

import static nustracker.commons.core.Messages.MESSAGE_COMMAND_EXTRANEOUS_SLASHES;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;

import nustracker.logic.commands.DeleteCommand;
import nustracker.logic.commands.DeleteEventCommand;
import nustracker.logic.commands.DeleteStudentCommand;
import nustracker.logic.parser.exceptions.ExtraSlashException;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.event.EventName;
import nustracker.model.student.StudentId;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap;

        try {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_STUDENTID, PREFIX_EVENT);
        } catch (ExtraSlashException e) {
            throw new ParseException(String.format(MESSAGE_COMMAND_EXTRANEOUS_SLASHES,
                    DeleteCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_STUDENTID).isPresent() && argMultimap.getValue(PREFIX_EVENT).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.ONE_PREFIX_ONLY));
        }

        if (argMultimap.getValue(PREFIX_STUDENTID).isPresent()) {
            StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get());
            return new DeleteStudentCommand(studentId);
        } else if (argMultimap.getValue(PREFIX_EVENT).isPresent()) {
            EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT).get());
            return new DeleteEventCommand(eventName);
        } else {
            // Execution should not reach here.
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }

}
