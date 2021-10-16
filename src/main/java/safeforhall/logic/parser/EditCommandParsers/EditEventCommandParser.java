package safeforhall.logic.parser.EditCommandParsers;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CliSyntax.PREFIX_NAME;
import static safeforhall.logic.parser.CliSyntax.PREFIX_DATE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_VENUE;
import static safeforhall.logic.parser.CliSyntax.PREFIX_CAPACITY;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.EditCommands.EditEventCommand;
import safeforhall.logic.commands.EditCommands.EditEventCommand.EditEventDescriptor;
import safeforhall.logic.parser.ArgumentMultimap;
import safeforhall.logic.parser.ArgumentTokenizer;
import safeforhall.logic.parser.Parser;
import safeforhall.logic.parser.ParserUtil;
import safeforhall.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditEventCommand object
 */
public class EditEventCommandParser implements Parser<EditEventCommand> {
    public EditEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_VENUE, PREFIX_CAPACITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            System.out.println(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE), pe);
        }

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEventDescriptor.setName(ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editEventDescriptor.setDate(ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            editEventDescriptor.setVenue(ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get()));
        }
        if (argMultimap.getValue(PREFIX_CAPACITY).isPresent()) {
            editEventDescriptor.setCapacity(ParserUtil.parseCapacity(argMultimap.getValue(PREFIX_CAPACITY).get()));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditEventCommand.MESSAGE_NOT_EDITED);
        }

        return new EditEventCommand(index, editEventDescriptor);
    }
}
