package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteStudentGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteStudentCommand object
 */
public class DeleteStudentGroupCommandParser implements Parser<DeleteStudentGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteStudentGroupCommand
     * and returns a DeleteStudentGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteStudentGroupCommand parse(String args) throws ParseException {
        ArrayList<Index> index;
        try {
            index = ParserUtil.parseMultipleIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentGroupCommand.MESSAGE_USAGE), pe);
        }

        if (index.size() != 2) {
            throw new ParseException(
                    String.format(DeleteStudentGroupCommand.MESSAGE_WRONG_NUMBER_OF_INDEX, index.size()));
        }

        return new DeleteStudentGroupCommand(index.get(0), index.get(1));
    }

}
