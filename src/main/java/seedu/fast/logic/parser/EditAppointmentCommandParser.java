package seedu.fast.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;

import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.logic.commands.EditAppointmentCommand;
import seedu.fast.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.Appointment;

public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {
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
                    EditAppointmentCommand.MESSAGE_USAGE), ive);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        setEditAppointmentDescriptor(argMultimap, editAppointmentDescriptor);

        boolean isAllFieldAbsent = !editAppointmentDescriptor.isAnyFieldEdited();
        if (isAllFieldAbsent) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_UPDATE_APPOINTMENT_FAILED_MISSING_FIELDS + "\n"
                    + EditAppointmentCommand.MESSAGE_USAGE);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }

    private void setEditAppointmentDescriptor(ArgumentMultimap argMultimap,
            EditAppointmentDescriptor editAppointmentDescriptor) throws ParseException {
        if (argMultimap.getValue(PREFIX_APPOINTMENT).isPresent()) {
            getAndSetDate(argMultimap, editAppointmentDescriptor);
        }

        if (argMultimap.getValue(PREFIX_APPOINTMENT_TIME).isPresent()) {
            getAndSetTime(argMultimap, editAppointmentDescriptor);
        }

        if (argMultimap.getValue(PREFIX_APPOINTMENT_VENUE).isPresent()) {
            getAndSetVenue(argMultimap, editAppointmentDescriptor);
        }
    }

    private void getAndSetDate(ArgumentMultimap argMultimap, EditAppointmentDescriptor editAppointmentDescriptor)
            throws ParseException {
        String date = ParserUtil.parseDateString(argMultimap.getValue(PREFIX_APPOINTMENT).get());
        editAppointmentDescriptor.setDate(date);
    }

    private void getAndSetTime(ArgumentMultimap argMultimap, EditAppointmentDescriptor editAppointmentDescriptor)
            throws ParseException {
        String time = ParserUtil.parseTimeString(argMultimap.getValue(PREFIX_APPOINTMENT_TIME).get());
        editAppointmentDescriptor.setTime(time);
    }

    private void getAndSetVenue(ArgumentMultimap argMultimap, EditAppointmentDescriptor editAppointmentDescriptor)
            throws ParseException {
        String venue = argMultimap.getValue(PREFIX_APPOINTMENT_VENUE).get();
        if (!Appointment.isValidVenueFormat(venue)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Appointment.INVALID_VENUE_INPUT));
        }
        editAppointmentDescriptor.setVenue(venue);
    }
}
