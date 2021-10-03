package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ShowEventDetailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventNamePredicate;

public class ShowEventDetailsCommandParser implements Parser<ShowEventDetailsCommand> {
    @Override
    public ShowEventDetailsCommand parse(String userInput) throws ParseException {
        String trimmedArg = userInput.trim().replaceAll("\\s+", " ");
        if (trimmedArg.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowEventDetailsCommand.MESSAGE_USAGE));
        }

        return new ShowEventDetailsCommand(new EventNamePredicate(trimmedArg));
    }
}
