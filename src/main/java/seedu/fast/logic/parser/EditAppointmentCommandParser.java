package seedu.fast.logic.parser;

import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.logic.commands.AppointmentCommand;
import seedu.fast.logic.commands.EditAppointmentCommand;
import seedu.fast.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.fast.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;


public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand>{
    @Override
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_APPOINTMENT, PREFIX_APPOINTMENT_TIME, PREFIX_APPOINTMENT_VENUE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentCommand.MESSAGE_USAGE), ive);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_APPOINTMENT).isPresent()) {
            editAppointmentDescriptor.setDate(ParserUtil.parseDateString(argMultimap.getValue(PREFIX_APPOINTMENT).get()));
        }

        if (argMultimap.getValue(PREFIX_APPOINTMENT_TIME).isPresent()) {
            editAppointmentDescriptor.setTime(ParserUtil.parseTimeString(argMultimap.getValue(PREFIX_APPOINTMENT_TIME).get()));
        }

        if (argMultimap.getValue(PREFIX_APPOINTMENT_VENUE).isPresent()) {
            editAppointmentDescriptor.setVenue(ParserUtil.parseVenueString(argMultimap.getValue(PREFIX_APPOINTMENT_VENUE).get()));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_FAILED);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }
}
