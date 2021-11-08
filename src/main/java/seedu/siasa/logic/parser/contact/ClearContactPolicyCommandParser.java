package seedu.siasa.logic.parser.contact;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.contact.ClearContactPolicyCommand;
import seedu.siasa.logic.parser.Parser;
import seedu.siasa.logic.parser.ParserUtil;
import seedu.siasa.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearContactPolicyCommand object
 */
public class ClearContactPolicyCommandParser implements Parser<ClearContactPolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearContactPolicyCommand
     * and returns a ClearContactPolicy object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearContactPolicyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearContactPolicyCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearContactPolicyCommand.MESSAGE_USAGE), pe);
        }
    }

}
