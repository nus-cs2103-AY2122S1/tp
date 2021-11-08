package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.docit.logic.commands.AddMedicalEntryCommand;
import seedu.docit.logic.commands.AddPatientCommand;
import seedu.docit.logic.commands.DeleteMedicalEntryCommand;
import seedu.docit.logic.commands.DeletePatientCommand;
import seedu.docit.logic.commands.EditPatientCommand;
import seedu.docit.logic.commands.FindPatientCommand;
import seedu.docit.logic.commands.ListPatientCommand;
import seedu.docit.logic.commands.PatientCommand;
import seedu.docit.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class PatientBookParser {
    /**
     * Parses user input of patient command for execution.
     *
     * @param commandWord command word
     * @param arguments arguments of command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public PatientCommand parsePatientCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parsePatientCommand(arguments);
        case EditPatientCommand.COMMAND_WORD:
            return new EditPatientCommandParser().parsePatientCommand(arguments);
        case DeletePatientCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parsePatientCommand(arguments);
        case FindPatientCommand.COMMAND_WORD:
            return new FindPatientCommandParser().parsePatientCommand(arguments);
        case ListPatientCommand.COMMAND_WORD:
            return new ListPatientCommand();
        case AddMedicalEntryCommand.COMMAND_WORD:
            return new AddMedicalEntryCommandParser().parsePatientCommand(arguments);
        case DeleteMedicalEntryCommand.COMMAND_WORD:
            return new DeleteMedicalEntryCommandParser().parsePatientCommand(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
