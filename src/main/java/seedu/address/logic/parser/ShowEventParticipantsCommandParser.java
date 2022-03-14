package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ShowEventParticipantsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ShowEventParticipantsCommandParser implements Parser<ShowEventParticipantsCommand> {
    @Override
    public ShowEventParticipantsCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new ShowEventParticipantsCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowEventParticipantsCommand.MESSAGE_USAGE), pe);
        }
    }
}
