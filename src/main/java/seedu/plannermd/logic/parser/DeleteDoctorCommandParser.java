package seedu.plannermd.logic.parser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.deletecommand.DeleteDoctorCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDoctorCommand object.
 */
public class DeleteDoctorCommandParser implements Parser<DeleteDoctorCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of a
     * DeleteDoctorCommand and returns a DeleteDoctorCommand object for execution.
     * @param args The user input.
     * @return The DeleteCommandObject that is to be executed.
     * @throws ParseException If the user input does not conform to the expected format
     */
    @Override
    public DeleteDoctorCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDoctorCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDoctorCommand.MESSAGE_USAGE), pe);
        }
    }

}
