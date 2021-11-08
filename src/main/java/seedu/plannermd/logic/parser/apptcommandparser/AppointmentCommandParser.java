package seedu.plannermd.logic.parser.apptcommandparser;

import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.commons.core.Messages.MESSAGE_UNKNOWN_FLAG;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_DELETE;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_EDIT;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_FILTER;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_FILTER_UPCOMING;
import static seedu.plannermd.logic.parser.CliSyntax.FLAG_LIST;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.plannermd.logic.commands.apptcommand.AppointmentCommand;
import seedu.plannermd.logic.commands.apptcommand.ListAppointmentCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;

public class AppointmentCommandParser {

    /**
     * Used for initial separation of flag and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<flag>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput user input string containing flag and arguments
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentCommand parseAppointmentCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppointmentCommand.MESSAGE_USAGE));
        }

        final String flag = matcher.group("flag");
        final String arguments = matcher.group("arguments");
        switch (flag) {
        case FLAG_ADD:
            return new AddAppointmentCommandParser().parse(arguments);

        case FLAG_EDIT:
            return new EditAppointmentCommandParser().parse(arguments);

        case FLAG_DELETE:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case FLAG_FILTER:
            return new FilterAppointmentCommandParser().parse(arguments);

        case FLAG_FILTER_UPCOMING:
            return new FilterUpcomingAppointmentCommandParser().parse(arguments);

        case FLAG_LIST:
            return new ListAppointmentCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_FLAG);
        }
    }
}
