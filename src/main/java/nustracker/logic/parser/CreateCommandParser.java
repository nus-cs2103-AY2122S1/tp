package nustracker.logic.parser;

import static nustracker.commons.core.Messages.MESSAGE_COMMAND_EXTRANEOUS_SLASHES;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustracker.logic.parser.CliSyntax.PREFIX_DATE;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_TIME;

import nustracker.logic.commands.CreateCommand;
import nustracker.logic.parser.exceptions.ExtraSlashException;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.event.Event;
import nustracker.model.event.EventDate;
import nustracker.model.event.EventName;
import nustracker.model.event.EventTime;

public class CreateCommandParser implements Parser<CreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateCommand
     * and returns an CreateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CreateCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap;

        try {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME);
        } catch (ExtraSlashException e) {
            throw new ParseException(String.format(MESSAGE_COMMAND_EXTRANEOUS_SLASHES,
                    CreateCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.arePrefixesPresent(PREFIX_NAME, PREFIX_DATE, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }

        EventName name = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());
        EventDate date = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get());
        EventTime time = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_TIME).get());

        Event event = new Event(name, date, time);

        return new CreateCommand(event);
    }


}
