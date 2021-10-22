package seedu.notor.logic.parser;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.notor.logic.commands.ClearCommand;
import seedu.notor.logic.commands.ClearNoteCommand;
import seedu.notor.logic.commands.Command;
import seedu.notor.logic.commands.ExitCommand;
import seedu.notor.logic.commands.HelpCommand;
import seedu.notor.logic.commands.NoteCommand;
import seedu.notor.logic.commands.group.GroupClearNoteCommand;
import seedu.notor.logic.commands.group.GroupCommand;
import seedu.notor.logic.commands.group.GroupDeleteCommand;
import seedu.notor.logic.commands.group.GroupNoteCommand;
import seedu.notor.logic.commands.group.SubGroupCreateCommand;
import seedu.notor.logic.commands.group.SuperGroupCreateCommand;
import seedu.notor.logic.commands.person.PersonAddGroupCommand;
import seedu.notor.logic.commands.person.PersonClearNoteCommand;
import seedu.notor.logic.commands.person.PersonClearTagsCommand;
import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.commands.person.PersonDeleteCommand;
import seedu.notor.logic.commands.person.PersonEditCommand;
import seedu.notor.logic.commands.person.PersonNoteCommand;
import seedu.notor.logic.commands.person.PersonRemoveGroupCommand;
import seedu.notor.logic.commands.person.PersonTagCommand;
import seedu.notor.logic.commands.person.PersonUntagCommand;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.logic.parser.group.GroupClearNoteCommandParser;
import seedu.notor.logic.parser.group.GroupDeleteCommandParser;
import seedu.notor.logic.parser.group.GroupNoteCommandParser;
import seedu.notor.logic.parser.group.SubGroupCreateCommandParser;
import seedu.notor.logic.parser.group.SuperGroupCreateCommandParser;
import seedu.notor.logic.parser.person.PersonAddGroupCommandParser;
import seedu.notor.logic.parser.person.PersonClearNoteCommandParser;
import seedu.notor.logic.parser.person.PersonClearTagsCommandParser;
import seedu.notor.logic.parser.person.PersonCreateCommandParser;
import seedu.notor.logic.parser.person.PersonDeleteCommandParser;
import seedu.notor.logic.parser.person.PersonEditCommandParser;
import seedu.notor.logic.parser.person.PersonNoteCommandParser;
import seedu.notor.logic.parser.person.PersonRemoveGroupCommandParser;
import seedu.notor.logic.parser.person.PersonTagCommandParser;
import seedu.notor.logic.parser.person.PersonUntagCommandParser;

/**
 * Parses user input.
 */
public class NotorParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern GENERAL_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)");
    private static final Pattern TARGETED_COMMAND_FORMAT = Pattern.compile(
            "(?<commandWord>\\w+)\\s+" // command word and any trailing spaces
                    + "/(?<subCommandWord>\\w+)" // subcommand word and any trailing spaces
                    + "(?<arguments>(\\s+.*)|(.*))"); // remaining arguments of the command
    private static final Pattern TARGETED_INDEX_COMMAND_FORMAT = Pattern.compile(
            "(?<commandWord>\\w+)\\s+" // command word and any trailing spaces
                    + "(?<index>\\d+\\s+)" // index or name and any trailing spaces
                    + "/(?<subCommandWord>\\w*)" // subcommand word
                    + "(?<arguments>(\\s+.*)|(.*))"); // remaining arguments of the command or trailing spaces
    private static final Pattern TARGETED_NAME_COMMAND_FORMAT = Pattern.compile(
            "(?<commandWord>\\w+)\\s+" // command word and any trailing spaces
                    + "(?<name>[A-Z][a-zA-Z ]+\\s+)" // index or name and any trailing spaces
                    + "/(?<subCommandWord>\\w+)" // subcommand word and any trailing spaces
                    + "(?<arguments>(\\s+.*)|(.*))"); // remaining arguments of the command

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        Matcher generalMatcher = GENERAL_COMMAND_FORMAT.matcher(userInput.trim());
        Matcher targetedMatcher = TARGETED_COMMAND_FORMAT.matcher(userInput.trim());
        Matcher targetedIndexMatcher = TARGETED_INDEX_COMMAND_FORMAT.matcher(userInput.trim());
        Matcher targetedNameMatcher = TARGETED_NAME_COMMAND_FORMAT.matcher(userInput.trim());

        if (generalMatcher.matches()) {
            final String commandWord = generalMatcher.group("commandWord");
            switch (commandWord) {
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();
            case NoteCommand.COMMAND_WORD:
                return new NoteCommand();
            case ClearNoteCommand.COMMAND_WORD:
                return new ClearNoteCommand();
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }

        if (targetedNameMatcher.matches()) {
            final String commandWord = targetedNameMatcher.group("commandWord").trim();
            final String name = targetedNameMatcher.group("name").trim();
            final String subCommandWord = targetedNameMatcher.group("subCommandWord").trim();
            final String arguments = targetedNameMatcher.group("arguments");
            switch (commandWord) {
            case PersonCommand.COMMAND_WORD:
                if (subCommandWord.equals(PersonCreateCommand.COMMAND_WORD)) {
                    return new PersonCreateCommandParser(name, arguments).parse();
                }
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            case GroupCommand.COMMAND_WORD:
                if (subCommandWord.equals(SuperGroupCreateCommand.COMMAND_WORD)) {
                    return new SuperGroupCreateCommandParser(name, arguments).parse();
                }
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }

        if (targetedIndexMatcher.matches()) {
            final String commandWord = targetedIndexMatcher.group("commandWord").trim();
            final String index = targetedIndexMatcher.group("index").trim();
            final String subCommandWord = targetedIndexMatcher.group("subCommandWord").trim();
            final String arguments = targetedIndexMatcher.group("arguments");
            switch (commandWord) {
            case PersonCommand.COMMAND_WORD:
                switch (subCommandWord) {
                case PersonDeleteCommand.COMMAND_WORD:
                    return new PersonDeleteCommandParser(index).parse();
                case PersonEditCommand.COMMAND_WORD:
                    return new PersonEditCommandParser(index, arguments).parse();
                case PersonNoteCommand.COMMAND_WORD:
                    return new PersonNoteCommandParser(index).parse();
                case PersonClearNoteCommand.COMMAND_WORD:
                    return new PersonClearNoteCommandParser(index).parse();
                case PersonAddGroupCommand.COMMAND_WORD:
                    return new PersonAddGroupCommandParser(index, arguments).parse();
                case PersonRemoveGroupCommand.COMMAND_WORD:
                    return new PersonRemoveGroupCommandParser(index, arguments).parse();
                case PersonTagCommand.COMMAND_WORD:
                    return new PersonTagCommandParser(index, arguments).parse();
                case PersonUntagCommand.COMMAND_WORD:
                    return new PersonUntagCommandParser(index, arguments).parse();
                case PersonClearTagsCommand.COMMAND_WORD:
                    return new PersonClearTagsCommandParser(index).parse();
                default:
                    throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
                }
            case GroupCommand.COMMAND_WORD:
                switch (subCommandWord) {
                case SubGroupCreateCommand.COMMAND_WORD:
                    return new SubGroupCreateCommandParser(index, arguments).parse();
                case GroupDeleteCommand.COMMAND_WORD:
                    return new GroupDeleteCommandParser(index).parse();
                case GroupNoteCommand.COMMAND_WORD:
                    return new GroupNoteCommandParser(index).parse();
                case GroupClearNoteCommand.COMMAND_WORD:
                    return new GroupClearNoteCommandParser(index).parse();
                default:
                    throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
                }
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }

        if (targetedMatcher.matches()) {
            final String commandWord = targetedMatcher.group("commandWord");
            final String subCommandWord = targetedMatcher.group("subCommandWord");
            final String arguments = targetedMatcher.group("arguments");
            // TODO: List command conversion/find command conversion
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

}
