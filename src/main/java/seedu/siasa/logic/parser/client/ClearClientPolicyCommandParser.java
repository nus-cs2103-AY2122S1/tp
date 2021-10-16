package seedu.siasa.logic.parser.client;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.client.ClearClientPolicyCommand;
import seedu.siasa.logic.parser.Parser;
import seedu.siasa.logic.parser.ParserUtil;
import seedu.siasa.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ClearClientPolicyCommandParser implements Parser<ClearClientPolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearClientPolicyCommand
     * and returns a ClearClientPolicy object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearClientPolicyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearClientPolicyCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearClientPolicyCommand.MESSAGE_USAGE), pe);
        }
    }

}
