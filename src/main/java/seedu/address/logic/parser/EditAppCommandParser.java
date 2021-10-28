package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATETIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.TimePeriod;

/**
 * Parses input arguments and creates a new EditAppCommand object
 */
public class EditAppCommandParser implements Parser<EditAppCommand> {

    @Override
    public EditAppCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ADDRESS, PREFIX_STARTDATETIME,
                        PREFIX_ENDDATETIME, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppCommand.MESSAGE_USAGE), pe);
        }

        EditAppCommand.EditAppDescriptor editAppDescriptor = new EditAppCommand.EditAppDescriptor();
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editAppDescriptor.setLocation(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_STARTDATETIME).isPresent()
                && argMultimap.getValue(PREFIX_ENDDATETIME).isPresent()) {
            editAppDescriptor
                    .setTimePeriod(new TimePeriod(ParserUtil.parseDateTime(argMultimap
                            .getValue(PREFIX_STARTDATETIME).get()),
                            ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_ENDDATETIME).get())));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editAppDescriptor.setDescription(ParserUtil
                    .parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (!editAppDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppCommand(index, editAppDescriptor);
    }

}
