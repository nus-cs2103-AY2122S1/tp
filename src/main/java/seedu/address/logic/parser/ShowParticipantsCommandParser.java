package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ShowParticipantsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventNamePredicate;

public class ShowParticipantsCommandParser implements Parser<ShowParticipantsCommand> {
    @Override
    public ShowParticipantsCommand parse(String userInput) throws ParseException {
        String trimmedArg = userInput.trim().replaceAll("\\s+", " ");
        if (trimmedArg.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowParticipantsCommand.MESSAGE_USAGE));
        }

        return new ShowParticipantsCommand(new EventNamePredicate(trimmedArg));
    }
}
