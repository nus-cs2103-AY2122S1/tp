package seedu.address.logic.parser.student;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.student.ShowMedicalHistoryCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ShowMedicalHistoryCommand} object
 */
public class ShowMedicalHistoryCommandParser implements Parser<ShowMedicalHistoryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ShowMedicalHistoryCommand
     * and returns a ShowMedicalHistoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowMedicalHistoryCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ShowMedicalHistoryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowMedicalHistoryCommand.MESSAGE_USAGE), pe);
        }
    }
}
