package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddParticipantToEventByIndexCommand;
import seedu.address.logic.commands.RemoveParticipantFromEventByIndexCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class RemoveParticipantFromEventByIndexParser implements Parser<RemoveParticipantFromEventByIndexCommand> {
    @Override
    public RemoveParticipantFromEventByIndexCommand parse(String args) throws ParseException {
        String[] sections = args.split(" ");

        if (sections.length != 3) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddParticipantToEventByIndexCommand.MESSAGE_USAGE));
        }

        try {
            Index participantIndex = ParserUtil.parseIndex(sections[1]);
            Index eventIndex = ParserUtil.parseIndex(sections[2]);
            return new RemoveParticipantFromEventByIndexCommand(participantIndex, eventIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddParticipantToEventByIndexCommand.MESSAGE_USAGE), pe);
        }

    }
}
