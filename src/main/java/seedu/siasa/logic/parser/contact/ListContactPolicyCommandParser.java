package seedu.siasa.logic.parser.contact;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.contact.ListContactPolicyCommand;
import seedu.siasa.logic.parser.Parser;
import seedu.siasa.logic.parser.ParserUtil;
import seedu.siasa.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListContactPolicyCommand object
 */
public class ListContactPolicyCommandParser implements Parser<ListContactPolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListContactPolicyCommand
     * and returns a ListContactPolicyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListContactPolicyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListContactPolicyCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListContactPolicyCommand.MESSAGE_USAGE), pe);
        }
    }
}
