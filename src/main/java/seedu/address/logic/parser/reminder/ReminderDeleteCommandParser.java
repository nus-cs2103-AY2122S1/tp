package seedu.address.logic.parser.reminder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IndexExceedsCapacityException;
import seedu.address.logic.commands.reminder.ReminderDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ReminderDeleteCommand object
 */
public class ReminderDeleteCommandParser implements Parser<ReminderDeleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ReminderDeleteCommand
     * and returns a ReminderDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReminderDeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ReminderDeleteCommand(index);
        } catch (IndexExceedsCapacityException iece) {
            throw new ParseException(iece.getMessage());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReminderDeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
