package seedu.fast.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.logic.commands.DeleteAppointmentCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.Appointment;

public class DeleteAppointmentCommandParser implements Parser<DeleteAppointmentCommand> {
    @Override
    public DeleteAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAppointmentCommand.MESSAGE_USAGE), ive);
        }

        return new DeleteAppointmentCommand(index, Appointment.getDefaultAppointment());
    }
}
