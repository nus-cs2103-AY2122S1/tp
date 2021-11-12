package seedu.notor.logic.parser;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.notor.logic.commands.ClearCommand;
import seedu.notor.logic.commands.ClearNoteCommand;
import seedu.notor.logic.commands.Command;
import seedu.notor.logic.commands.ExitCommand;
import seedu.notor.logic.commands.ExportCommand;
import seedu.notor.logic.commands.HelpCommand;
import seedu.notor.logic.commands.NoteCommand;
import seedu.notor.logic.commands.group.GroupClearNoteCommand;
import seedu.notor.logic.commands.group.GroupCommand;
import seedu.notor.logic.commands.group.GroupDeleteCommand;
import seedu.notor.logic.commands.group.GroupFindCommand;
import seedu.notor.logic.commands.group.GroupNoteCommand;
import seedu.notor.logic.commands.group.SubGroupCreateCommand;
import seedu.notor.logic.commands.group.SubGroupListCommand;
import seedu.notor.logic.commands.group.SuperGroupCreateCommand;
import seedu.notor.logic.commands.group.SuperGroupListCommand;
import seedu.notor.logic.commands.person.PersonAddGroupCommand;
import seedu.notor.logic.commands.person.PersonArchiveAllCommand;
import seedu.notor.logic.commands.person.PersonArchiveCommand;
import seedu.notor.logic.commands.person.PersonArchiveShowCommand;
import seedu.notor.logic.commands.person.PersonClearNoteCommand;
import seedu.notor.logic.commands.person.PersonClearTagsCommand;
import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.commands.person.PersonDeleteCommand;
import seedu.notor.logic.commands.person.PersonEditCommand;
import seedu.notor.logic.commands.person.PersonFindCommand;
import seedu.notor.logic.commands.person.PersonGroupListCommand;
import seedu.notor.logic.commands.person.PersonListCommand;
import seedu.notor.logic.commands.person.PersonNoteCommand;
import seedu.notor.logic.commands.person.PersonRemoveGroupCommand;
import seedu.notor.logic.commands.person.PersonTagCommand;
import seedu.notor.logic.commands.person.PersonUnarchiveCommand;
import seedu.notor.logic.commands.person.PersonUntagCommand;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.logic.parser.group.GroupClearNoteCommandParser;
import seedu.notor.logic.parser.group.GroupDeleteCommandParser;
import seedu.notor.logic.parser.group.GroupFindCommandParser;
import seedu.notor.logic.parser.group.GroupNoteCommandParser;
import seedu.notor.logic.parser.group.SubGroupCreateCommandParser;
import seedu.notor.logic.parser.group.SubGroupListCommandParser;
import seedu.notor.logic.parser.group.SuperGroupCreateCommandParser;
import seedu.notor.logic.parser.group.SuperGroupListCommandParser;
import seedu.notor.logic.parser.person.PersonAddGroupCommandParser;
import seedu.notor.logic.parser.person.PersonArchiveAllCommandParser;
import seedu.notor.logic.parser.person.PersonArchiveCommandParser;
import seedu.notor.logic.parser.person.PersonArchiveShowCommandParser;
import seedu.notor.logic.parser.person.PersonClearNoteCommandParser;
import seedu.notor.logic.parser.person.PersonClearTagsCommandParser;
import seedu.notor.logic.parser.person.PersonCreateCommandParser;
import seedu.notor.logic.parser.person.PersonDeleteCommandParser;
import seedu.notor.logic.parser.person.PersonEditCommandParser;
import seedu.notor.logic.parser.person.PersonFindCommandParser;
import seedu.notor.logic.parser.person.PersonGroupListCommandParser;
import seedu.notor.logic.parser.person.PersonListCommandParser;
import seedu.notor.logic.parser.person.PersonNoteCommandParser;
import seedu.notor.logic.parser.person.PersonRemoveGroupCommandParser;
import seedu.notor.logic.parser.person.PersonTagCommandParser;
import seedu.notor.logic.parser.person.PersonUnarchiveCommandParser;
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
                    + "(?<index>\\d+\\s+)" // index and any trailing spaces
                    + "/(?<subCommandWord>\\w*)" // subcommand word
                    + "(?<arguments>(\\s+.*)|(.*))"); // remaining arguments of the command or trailing spaces
    private static final Pattern TARGETED_NAME_COMMAND_FORMAT = Pattern.compile(
            "(?<commandWord>\\w+)\\s+" // command word and any trailing spaces
                    + "(?<name>[a-zA-Z][\\p{Alnum}-. ]*\\s+)" // name and any trailing spaces
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
            if (HelpCommand.COMMAND_WORDS.contains(commandWord)) {
                return new HelpCommand();
            } else if (ExitCommand.COMMAND_WORDS.contains(commandWord)) {
                return new ExitCommand();
            } else if (ClearCommand.COMMAND_WORDS.contains(commandWord)) {
                return new ClearCommand();
            } else if (NoteCommand.COMMAND_WORDS.contains(commandWord)) {
                return new NoteCommand();
            } else if (ClearNoteCommand.COMMAND_WORDS.contains(commandWord)) {
                return new ClearNoteCommand();
            } else if (ExportCommand.COMMAND_WORDS.contains(commandWord)) {
                return new ExportCommand();
            }
            // throw more specific errors
            if (PersonCommand.COMMAND_WORDS.contains(commandWord)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        PersonCommand.MESSAGE_USAGE));
            } else if (GroupCommand.COMMAND_WORDS.contains(commandWord)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        GroupCommand.MESSAGE_USAGE));
            } else {
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }

        if (targetedNameMatcher.matches()) {
            final String commandWord = targetedNameMatcher.group("commandWord").trim();
            final String name = targetedNameMatcher.group("name").trim();
            final String subCommandWord = targetedNameMatcher.group("subCommandWord").trim();
            final String arguments = targetedNameMatcher.group("arguments");
            if (PersonCommand.COMMAND_WORDS.contains(commandWord)) {
                if (PersonCreateCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonCreateCommandParser(name, arguments).parse();
                }
            }
            if (GroupCommand.COMMAND_WORDS.contains(commandWord)) {
                if (SuperGroupCreateCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new SuperGroupCreateCommandParser(name, arguments).parse();
                }
            }
        }

        if (targetedIndexMatcher.matches()) {
            final String commandWord = targetedIndexMatcher.group("commandWord").trim();
            final String index = targetedIndexMatcher.group("index").trim();
            final String subCommandWord = targetedIndexMatcher.group("subCommandWord").trim();
            final String arguments = targetedIndexMatcher.group("arguments");
            if (PersonCommand.COMMAND_WORDS.contains(commandWord)) {
                if (PersonDeleteCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonDeleteCommandParser(index).parse();
                } else if (PersonEditCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonEditCommandParser(index, arguments).parse();
                } else if (PersonNoteCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonNoteCommandParser(index).parse();
                } else if (PersonClearNoteCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonClearNoteCommandParser(index).parse();
                } else if (PersonAddGroupCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonAddGroupCommandParser(index, arguments).parse();
                } else if (PersonRemoveGroupCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonRemoveGroupCommandParser(index, arguments).parse();
                } else if (PersonGroupListCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonGroupListCommandParser(index).parse();
                } else if (PersonTagCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonTagCommandParser(index, arguments).parse();
                } else if (PersonUntagCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonUntagCommandParser(index, arguments).parse();
                } else if (PersonClearTagsCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonClearTagsCommandParser(index).parse();
                } else if (PersonArchiveCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonArchiveCommandParser(index).parse();
                } else if (PersonUnarchiveCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonUnarchiveCommandParser(index).parse();
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            PersonCommand.MESSAGE_USAGE));
                }
            }
            if (GroupCommand.COMMAND_WORDS.contains(commandWord)) {
                if (SubGroupCreateCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new SubGroupCreateCommandParser(index, arguments).parse();
                } else if (GroupDeleteCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new GroupDeleteCommandParser(index).parse();
                } else if (GroupNoteCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new GroupNoteCommandParser(index).parse();
                } else if (GroupClearNoteCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new GroupClearNoteCommandParser(index).parse();
                } else if (SubGroupListCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new SubGroupListCommandParser(index).parse();
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            GroupCommand.MESSAGE_USAGE));
                }
            }
        }

        if (targetedMatcher.matches()) {
            final String commandWord = targetedMatcher.group("commandWord");
            final String subCommandWord = targetedMatcher.group("subCommandWord");
            final String arguments = targetedMatcher.group("arguments");
            if (PersonCommand.COMMAND_WORDS.contains(commandWord)) {
                if (PersonListCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonListCommandParser(arguments).parse();
                } else if (PersonFindCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonFindCommandParser(arguments).parse();
                } else if (PersonArchiveAllCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonArchiveAllCommandParser().parse();
                } else if (PersonArchiveShowCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new PersonArchiveShowCommandParser().parse();
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            PersonCommand.MESSAGE_USAGE));
                }
            }
            if (GroupCommand.COMMAND_WORDS.contains(commandWord)) {
                if (SuperGroupListCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new SuperGroupListCommandParser(arguments).parse();
                } else if (GroupFindCommand.COMMAND_WORDS.contains(subCommandWord)) {
                    return new GroupFindCommandParser(arguments).parse();
                } else {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
                }
            }
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

}
