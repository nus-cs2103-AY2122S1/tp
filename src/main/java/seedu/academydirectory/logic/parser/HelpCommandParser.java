package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.commons.core.Messages.MESSAGE_HELP_NOT_EXIST;

import seedu.academydirectory.logic.commands.AddCommand;
import seedu.academydirectory.logic.commands.AttendanceCommand;
import seedu.academydirectory.logic.commands.ClearCommand;
import seedu.academydirectory.logic.commands.DeleteCommand;
import seedu.academydirectory.logic.commands.EditCommand;
import seedu.academydirectory.logic.commands.ExitCommand;
import seedu.academydirectory.logic.commands.FilterCommand;
import seedu.academydirectory.logic.commands.GetCommand;
import seedu.academydirectory.logic.commands.GradeCommand;
import seedu.academydirectory.logic.commands.HelpCommand;
import seedu.academydirectory.logic.commands.HistoryCommand;
import seedu.academydirectory.logic.commands.ListCommand;
import seedu.academydirectory.logic.commands.ParticipationCommand;
import seedu.academydirectory.logic.commands.RedoCommand;
import seedu.academydirectory.logic.commands.RevertCommand;
import seedu.academydirectory.logic.commands.ShowCommand;
import seedu.academydirectory.logic.commands.SortCommand;
import seedu.academydirectory.logic.commands.TagCommand;
import seedu.academydirectory.logic.commands.UndoCommand;
import seedu.academydirectory.logic.commands.ViewCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns an HelpCom object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public HelpCommand parse(String userInput) throws ParseException {
        userInput = userInput.trim();
        if (userInput.isEmpty()) {
            return new HelpCommand();
        }

        switch (userInput) {
        case AddCommand.COMMAND_WORD:
            return new HelpCommand(userInput, AddCommand.HELP_MESSAGE);

        case EditCommand.COMMAND_WORD:
            return new HelpCommand(userInput, EditCommand.HELP_MESSAGE);

        case DeleteCommand.COMMAND_WORD:
            return new HelpCommand(userInput, DeleteCommand.HELP_MESSAGE);

        case ClearCommand.COMMAND_WORD:
            return new HelpCommand(userInput, ClearCommand.HELP_MESSAGE);

        case FilterCommand.COMMAND_WORD:
            return new HelpCommand(userInput, FilterCommand.HELP_MESSAGE);

        case GetCommand.COMMAND_WORD:
            return new HelpCommand(userInput, GetCommand.HELP_MESSAGE);

        case ListCommand.COMMAND_WORD:
            return new HelpCommand(userInput, ListCommand.HELP_MESSAGE);

        case AttendanceCommand.COMMAND_WORD:
            return new HelpCommand(userInput, AttendanceCommand.HELP_MESSAGE);

        case GradeCommand.COMMAND_WORD:
            return new HelpCommand(userInput, GradeCommand.HELP_MESSAGE);

        case ExitCommand.COMMAND_WORD:
            return new HelpCommand(userInput, ExitCommand.HELP_MESSAGE);

        case ParticipationCommand.COMMAND_WORD:
            return new HelpCommand(userInput, ParticipationCommand.HELP_MESSAGE);

        case ShowCommand.COMMAND_WORD:
            return new HelpCommand(userInput, ShowCommand.HELP_MESSAGE);

        case SortCommand.COMMAND_WORD:
            return new HelpCommand(userInput, SortCommand.HELP_MESSAGE);

        case TagCommand.COMMAND_WORD:
            return new HelpCommand(userInput, TagCommand.HELP_MESSAGE);

        case HistoryCommand.COMMAND_WORD:
            return new HelpCommand(userInput, HistoryCommand.HELP_MESSAGE);

        case RevertCommand.COMMAND_WORD:
            return new HelpCommand(userInput, RevertCommand.HELP_MESSAGE);

        case UndoCommand.COMMAND_WORD:
            return new HelpCommand(userInput, UndoCommand.HELP_MESSAGE);

        case RedoCommand.COMMAND_WORD:
            return new HelpCommand(userInput, RedoCommand.HELP_MESSAGE);

        case ViewCommand.COMMAND_WORD:
            return new HelpCommand(userInput, ViewCommand.HELP_MESSAGE);

        case VisualizeCommand.COMMAND_WORD:
            return new HelpCommand(userInput, VisualizeCommand.HELP_MESSAGE);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(String.format(MESSAGE_HELP_NOT_EXIST, userInput));
        }
    }
}

