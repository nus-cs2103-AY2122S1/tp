package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ZERO_BASED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddParticipantToEventCommand;
import seedu.address.logic.commands.RemoveParticipantFromEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class RemoveParticipantFromEventParser implements Parser<RemoveParticipantFromEventCommand> {
    @Override
    public RemoveParticipantFromEventCommand parse(String args) throws ParseException {
        String[] sections = args.split(" ");

        if (sections.length != 3) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, AddParticipantToEventCommand.MESSAGE_USAGE));
        }

        try {
            Index participantIndex = ParserUtil.parseIndex(sections[1]);
            Index eventIndex = ParserUtil.parseIndex(sections[2]);
            return new RemoveParticipantFromEventCommand(participantIndex, eventIndex);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_ZERO_BASED_INDEX);
        }

    }
}
