package seedu.anilist.logic.parser;

import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.anilist.logic.commands.AddCommand;
import seedu.anilist.logic.commands.ClearCommand;
import seedu.anilist.logic.commands.Command;
import seedu.anilist.logic.commands.DeleteCommand;
import seedu.anilist.logic.commands.EditCommand;
import seedu.anilist.logic.commands.ExitCommand;
import seedu.anilist.logic.commands.FindCommand;
import seedu.anilist.logic.commands.HelpCommand;
import seedu.anilist.logic.commands.ListCommand;
import seedu.anilist.logic.commands.RenameCommand;
import seedu.anilist.logic.commands.UpdateEpisodeCommand;
import seedu.anilist.logic.commands.UpdateStatusCommand;
import seedu.anilist.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AnimeListParser {

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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UpdateEpisodeCommand.COMMAND_WORD:
            return new UpdateEpisodeCommandParser().parse(arguments);

        case RenameCommand.COMMAND_WORD:
            return new RenameCommandParser().parse(arguments);
            
        case UpdateStatusCommand.COMMAND_WORD:
            return new UpdateStatusCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
