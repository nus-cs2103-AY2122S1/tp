package seedu.fast.logic.parser;

import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.logic.commands.MarkAppointmentCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.Appointment;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class MarkAppointmentCommandParser implements Parser<MarkAppointmentCommand> {
    @Override
    public MarkAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkAppointmentCommand.MESSAGE_USAGE), ive);
        }

        return new MarkAppointmentCommand(index,
                new Appointment(Appointment.NO_APPOINTMENT, Appointment.NO_TIME, Appointment.NO_VENUE));
    }
}
