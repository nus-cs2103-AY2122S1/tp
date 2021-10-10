package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ShowEventParticipantsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventNamePredicate;

public class ShowEventParticipantsCommandParser implements Parser<ShowEventParticipantsCommand> {
    @Override
    public ShowEventParticipantsCommand parse(String userInput) throws ParseException {
        String trimmedArg = userInput.trim().replaceAll("\\s+", " ");
        if (trimmedArg.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowEventParticipantsCommand.MESSAGE_USAGE));
        }

        return new ShowEventParticipantsCommand(new EventNamePredicate(trimmedArg));
    }
}
