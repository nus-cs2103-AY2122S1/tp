package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteMultipleCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.StatisticsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * List of command words for command suggestions in case of a user typo
     */
    public static final List<String> COMMAND_WORDS = Arrays.asList(
            AddCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            DeleteMultipleCommand.COMMAND_WORD,
            ClearCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD,
            RemarkCommand.COMMAND_WORD,
            SortCommand.COMMAND_WORD,
            StatisticsCommand.COMMAND_WORD,
            ImportCommand.COMMAND_WORD,
            ExportCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD
    );

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
        if (CommandAliases.getValue(userInput).isPresent()) {
            return CommandAliases.getValue(userInput).get();
        }

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

        case DeleteMultipleCommand.COMMAND_WORD:
            return new DeleteMultipleCommandParser().parse(arguments);

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

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case StatisticsCommand.COMMAND_WORD:
            return new StatisticsCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments);

        case ExportCommand.COMMAND_WORD:
            return new ExportCommandParser().parse(arguments);

        case AliasCommand.COMMAND_WORD:
            return new AliasCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case AddTagCommand.COMMAND_WORD:
            return new AddTagCommandParser().parse(arguments);

        default:
            WordSuggestion commandSuggestions = new WordSuggestion(commandWord, COMMAND_WORDS, 2);

            throw new ParseException(String.format("%s: %s.\n%s",
                    MESSAGE_UNKNOWN_COMMAND, userInput, commandSuggestions.getSuggestedWords()));
        }
    }

}
