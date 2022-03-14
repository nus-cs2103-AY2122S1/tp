package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditEventCommandParser implements Parser<EditEventCommand> {
    @Override
    public EditEventCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME);
        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE), pe);
        }

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEventDescriptor.setEventName(ParserUtil.parseEventName(argumentMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argumentMultimap.getValue(PREFIX_DATE).isPresent()) {
            editEventDescriptor.setEventDate(ParserUtil.parseEventDate(argumentMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argumentMultimap.getValue(PREFIX_TIME).isPresent()) {
            editEventDescriptor.setEventTime(ParserUtil.parseEventTime(argumentMultimap.getValue(PREFIX_TIME).get()));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
        }
        return new EditEventCommand(index, editEventDescriptor);
    }
}
