package manageme.logic.parser.link;

import manageme.commons.core.Messages;
import manageme.commons.core.index.Index;
import manageme.logic.commands.link.OpenLinkCommand;
import manageme.logic.parser.Parser;
import manageme.logic.parser.ParserUtil;
import manageme.logic.parser.exceptions.ParseException;

public class OpenLinkCommandParser implements Parser<OpenLinkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the OpenLinkCommand
     * and returns a OpenLinkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OpenLinkCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new OpenLinkCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, OpenLinkCommand.MESSAGE_USAGE), pe);
        }
    }
}
