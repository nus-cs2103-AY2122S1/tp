package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANT_ID;

import java.util.stream.Stream;
import seedu.address.logic.commands.RemoveParticipantFromEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventName;
import seedu.address.model.participant.ParticipantId;

public class RemoveParticipantFromEventParser implements Parser<RemoveParticipantFromEventCommand> {

    @Override
    public RemoveParticipantFromEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_PARTICIPANT_ID, PREFIX_EVENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_PARTICIPANT_ID, PREFIX_EVENT)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveParticipantFromEventCommand.MESSAGE_USAGE));
        }

        // REQUIRED
        ParticipantId participantId = ParserUtil.parseParticipantId(argMultimap.getValue(PREFIX_PARTICIPANT_ID).get());
        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT).get());

        return new RemoveParticipantFromEventCommand(participantId, eventName);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
