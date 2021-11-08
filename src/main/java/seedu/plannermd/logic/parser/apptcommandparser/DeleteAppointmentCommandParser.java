package seedu.plannermd.logic.parser.apptcommandparser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.deletecommand.DeleteAppointmentCommand;
import seedu.plannermd.logic.parser.Parser;
import seedu.plannermd.logic.parser.ParserUtil;
import seedu.plannermd.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteAppointmentCommand object.
 */
public class DeleteAppointmentCommandParser implements Parser<DeleteAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of a
     * DeleteAppointmentCommand and returns a DeleteAppointmentCommand object for execution.
     * @param args The user input.
     * @return The DeleteCommandObject that is to be executed.
     * @throws ParseException If the user input does not conform to the expected format
     */
    @Override
    public DeleteAppointmentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteAppointmentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }
}

