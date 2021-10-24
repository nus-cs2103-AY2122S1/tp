package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ShowEventDetailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ShowEventDetailsCommandParser implements Parser<ShowEventDetailsCommand> {
    @Override
    public ShowEventDetailsCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new ShowEventDetailsCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowEventDetailsCommand.MESSAGE_USAGE), pe);
        }
    }
}
