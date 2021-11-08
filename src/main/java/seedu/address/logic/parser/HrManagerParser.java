package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.Command;
import seedu.address.logic.candidate.AddCandidateCommand;
import seedu.address.logic.candidate.DeleteCandidateCommand;
import seedu.address.logic.candidate.EditCandidateCommand;
import seedu.address.logic.candidate.FindCandidateCommand;
import seedu.address.logic.candidate.ListCandidateCommand;
import seedu.address.logic.candidate.RemarkCandidateCommand;
import seedu.address.logic.general.ClearCommand;
import seedu.address.logic.general.ExitCommand;
import seedu.address.logic.general.HelpCommand;
import seedu.address.logic.interview.AddInterviewCommand;
import seedu.address.logic.interview.AssignInterviewCommand;
import seedu.address.logic.interview.DeleteInterviewCommand;
import seedu.address.logic.interview.EditInterviewCommand;
import seedu.address.logic.interview.FindInterviewCommand;
import seedu.address.logic.interview.ListInterviewCommand;
import seedu.address.logic.interview.UnassignInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.position.AddPositionCommand;
import seedu.address.logic.position.DeletePositionCommand;
import seedu.address.logic.position.EditPositionCommand;
import seedu.address.logic.position.FindPositionCommand;
import seedu.address.logic.position.ListPositionCommand;

/**
 * Parses user input.
 */
public class HrManagerParser {

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
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCandidateCommand.COMMAND_WORD:
            return new AddCandidateCommandParser().parse(arguments);

        case EditCandidateCommand.COMMAND_WORD:
            return new EditCandidateCommandParser().parse(arguments);

        case DeleteCandidateCommand.COMMAND_WORD:
            return new DeleteCandidateCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCandidateCommand.COMMAND_WORD:
            return new FindCandidateCommandParser().parse(arguments);

        case ListCandidateCommand.COMMAND_WORD:
            return new ListCandidateCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RemarkCandidateCommand.COMMAND_WORD:
            return new RemarkCandidateCommandParser().parse(arguments);

        case AddPositionCommand.COMMAND_WORD:
            return new AddPositionCommandParser().parse(arguments);

        case DeletePositionCommand.COMMAND_WORD:
            return new DeletePositionCommandParser().parse(arguments);

        case EditPositionCommand.COMMAND_WORD:
            return new EditPositionCommandParser().parse(arguments);

        case ListPositionCommand.COMMAND_WORD:
            return new ListPositionCommand();

        case FindPositionCommand.COMMAND_WORD:
            return new FindPositionCommandParser().parse(arguments);

        case AddInterviewCommand.COMMAND_WORD:
            return new AddInterviewCommandParser().parse(arguments);

        case EditInterviewCommand.COMMAND_WORD:
            return new EditInterviewCommandParser().parse(arguments);

        case ListInterviewCommand.COMMAND_WORD:
            return new ListInterviewCommand();

        case DeleteInterviewCommand.COMMAND_WORD:
            return new DeleteInterviewCommandParser().parse(arguments);

        case FindInterviewCommand.COMMAND_WORD:
            return new FindInterviewCommandParser().parse(arguments);

        case UnassignInterviewCommand.COMMAND_WORD:
            return new UnassignInterviewCommandParser().parse(arguments);

        case AssignInterviewCommand.COMMAND_WORD:
            return new AssignInterviewCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
