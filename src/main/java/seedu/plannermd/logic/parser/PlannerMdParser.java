package seedu.plannermd.logic.parser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.plannermd.logic.commands.ClearCommand;
import seedu.plannermd.logic.commands.Command;
import seedu.plannermd.logic.commands.ExitCommand;
import seedu.plannermd.logic.commands.HelpCommand;
import seedu.plannermd.logic.commands.ToggleCommand;
import seedu.plannermd.logic.commands.addcommand.AddDoctorCommand;
import seedu.plannermd.logic.commands.addcommand.AddPatientCommand;
import seedu.plannermd.logic.commands.apptcommand.AppointmentCommand;
import seedu.plannermd.logic.commands.deletecommand.DeleteCommand;
import seedu.plannermd.logic.commands.editcommand.EditCommand;
import seedu.plannermd.logic.commands.findcommand.FindDoctorCommand;
import seedu.plannermd.logic.commands.findcommand.FindPatientCommand;
import seedu.plannermd.logic.commands.listcommand.ListDoctorCommand;
import seedu.plannermd.logic.commands.listcommand.ListPatientCommand;
import seedu.plannermd.logic.commands.remarkcommand.RemarkCommand;
import seedu.plannermd.logic.commands.tagcommand.TagCommand;
import seedu.plannermd.logic.parser.addcommandparser.AddDoctorCommandParser;
import seedu.plannermd.logic.parser.addcommandparser.AddPatientCommandParser;
import seedu.plannermd.logic.parser.apptcommandparser.AppointmentCommandParser;
import seedu.plannermd.logic.parser.deletecommandparser.DeleteDoctorCommandParser;
import seedu.plannermd.logic.parser.deletecommandparser.DeletePatientCommandParser;
import seedu.plannermd.logic.parser.editcommandparser.EditDoctorCommandParser;
import seedu.plannermd.logic.parser.editcommandparser.EditPatientCommandParser;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.logic.parser.findcommandparser.FindDoctorCommandParser;
import seedu.plannermd.logic.parser.findcommandparser.FindPatientCommandParser;
import seedu.plannermd.logic.parser.remarkcommandparser.RemarkDoctorCommandParser;
import seedu.plannermd.logic.parser.remarkcommandparser.RemarkPatientCommandParser;
import seedu.plannermd.logic.parser.tagcommandparser.TagDoctorCommandParser;
import seedu.plannermd.logic.parser.tagcommandparser.TagPatientCommandParser;
import seedu.plannermd.model.Model.State;

/**
 * Parses user input.
 */
public class PlannerMdParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, State state) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ToggleCommand.COMMAND_WORD:
            return new ToggleCommand();

        case AppointmentCommand.COMMAND_WORD:
            return new AppointmentCommandParser().parseAppointmentCommand(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        default:
            if (state.equals(State.PATIENT)) {
                return parsePatientCommand(commandWord, arguments);
            } else {
                return parseDoctorCommand(commandWord, arguments);
            }
        }
    }

    private Command parsePatientCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditPatientCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkPatientCommandParser().parse(arguments);

        case TagCommand.COMMAND_WORD:
            return new TagPatientCommandParser().parse(arguments);

        case FindPatientCommand.COMMAND_WORD:
            return new FindPatientCommandParser().parse(arguments);

        case ListPatientCommand.COMMAND_WORD:
            return new ListPatientCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command parseDoctorCommand(String commandWord, String arguments) throws ParseException {

        switch (commandWord) {

        case AddDoctorCommand.COMMAND_WORD:
            return new AddDoctorCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditDoctorCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteDoctorCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkDoctorCommandParser().parse(arguments);

        case TagCommand.COMMAND_WORD:
            return new TagDoctorCommandParser().parse(arguments);

        case FindDoctorCommand.COMMAND_WORD:
            return new FindDoctorCommandParser().parse(arguments);

        case ListDoctorCommand.COMMAND_WORD:
            return new ListDoctorCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
