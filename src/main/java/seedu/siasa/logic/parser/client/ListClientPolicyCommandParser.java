package seedu.siasa.logic.parser.client;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.client.ListClientPolicyCommand;
import seedu.siasa.logic.parser.Parser;
import seedu.siasa.logic.parser.ParserUtil;
import seedu.siasa.logic.parser.exceptions.ParseException;

public class ListClientPolicyCommandParser implements Parser<ListClientPolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListClientPolicyCommand
     * and returns a ListClientPolicyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListClientPolicyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListClientPolicyCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListClientPolicyCommand.MESSAGE_USAGE), pe);
        }
    }
}
