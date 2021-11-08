package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.docit.logic.commands.AddAppointmentCommand;
import seedu.docit.logic.commands.AppointmentCommand;
import seedu.docit.logic.commands.ArchiveAppointmentCommand;
import seedu.docit.logic.commands.DeleteAppointmentCommand;
import seedu.docit.logic.commands.EditAppointmentCommand;
import seedu.docit.logic.commands.ListAppointmentsCommand;
import seedu.docit.logic.commands.ListArchivedAppointmentsCommand;
import seedu.docit.logic.commands.SortAppointmentsCommand;
import seedu.docit.logic.commands.prescription.AddPrescriptionCommand;
import seedu.docit.logic.commands.prescription.DeletePrescriptionCommand;
import seedu.docit.logic.parser.exceptions.ParseException;
import seedu.docit.logic.parser.prescription.AddPrescriptionCommandParser;
import seedu.docit.logic.parser.prescription.DeletePrescriptionCommandParser;

/**
 * Parses user input.
 */
public class AppointmentBookParser {
    /**
     * Parses user input of appointment command for execution.
     * @param commandWord command word
     * @param arguments arguments of command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentCommand parseAppointmentCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parseAppointmentCommand(arguments);
        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parseAppointmentCommand(arguments);
        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parseAppointmentCommand(arguments);
        case ArchiveAppointmentCommand.COMMAND_WORD:
            return new ArchiveAppointmentCommandParser().parseAppointmentCommand(arguments);
        case ListAppointmentsCommand.COMMAND_WORD:
            return new ListAppointmentsCommand();
        case ListArchivedAppointmentsCommand.COMMAND_WORD:
            return new ListArchivedAppointmentsCommand();
        case SortAppointmentsCommand.COMMAND_WORD:
            return new SortAppointmentsCommand();
        case AddPrescriptionCommand.COMMAND_WORD:
            return new AddPrescriptionCommandParser().parseAppointmentCommand(arguments);
        case DeletePrescriptionCommand.COMMAND_WORD:
            return new DeletePrescriptionCommandParser().parseAppointmentCommand(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
