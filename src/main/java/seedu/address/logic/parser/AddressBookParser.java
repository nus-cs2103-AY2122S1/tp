package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.util.CollectionUtil.equalsIgnoreOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAllocCommand;
import seedu.address.logic.commands.AddAssessmentCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddScoreCommand;
import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private final List<Alias> aliases = new ArrayList<>();

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

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        // if it ends with a prefix, add a space so that the prefix can be picked up by the tokenizer
        if (arguments.matches(".* -[a-z]+$")) {
            arguments = arguments + " ";
        }

        switch (commandWord) {

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case SearchCommand.COMMAND_WORD:
            return new SearchCommandParser().parse(arguments);

        case ShowCommand.COMMAND_WORD:
            return new ShowCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments);

        case ExportCommand.COMMAND_WORD:
            return new ExportCommand();

        case AliasCommand.COMMAND_WORD:
            return new AliasCommandParser(this).parse(arguments);

        default:
            boolean isTwoWordCommand = arguments.length() > 0
                    && !arguments.startsWith(" -") && !Character.isDigit(arguments.charAt(1));

            if (isTwoWordCommand) {
                return parseTwoWordCommand(commandWord, arguments);
            }

            return parseAliases(userInput);
        }
    }

    private Command parseTwoWordCommand(String commandWord, String arguments) throws ParseException {
        commandWord = extractFullCommandWord(commandWord, arguments);
        arguments = extractArguments(arguments);

        switch (commandWord) {

        case AddGroupCommand.COMMAND_WORD:
            return new AddGroupCommandParser().parse(arguments);

        case AddAllocCommand.COMMAND_WORD:
            return new AddAllocCommandParser().parse(arguments);

        case AddAssessmentCommand.COMMAND_WORD:
            return new AddAssessmentCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddScoreCommand.COMMAND_WORD:
            return new AddScoreCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Removes second word of command from arguments.
     *
     * @param arguments raw arguments to extract from.
     * @return extracted arguments.
     */
    private String extractArguments(String arguments) {
        int argumentsIndex = arguments.indexOf("-");
        if (argumentsIndex == -1) {
            return "";
        }
        String extractedArguments = arguments.substring(argumentsIndex - 1);
        return extractedArguments;
    }

    /**
     * Converts commandWord to the two word format and retrieves second word from arguments.
     *
     * @param firstCommandWord the original commandWord parsed.
     * @param arguments arguments to retrieve second half of commandWord from.
     * @return full commandWord.
     */
    private String extractFullCommandWord(String firstCommandWord, String arguments) {
        int argumentsIndex = arguments.indexOf("-");
        if (argumentsIndex == -1) {
            String[] arr = arguments.split(" ", 3);
            String firstWord = arr[1];
            return firstCommandWord + " " + firstWord;
        }
        String fullCommandWord = firstCommandWord + arguments.substring(0, argumentsIndex - 1).stripTrailing();
        return fullCommandWord;
    }

    /**
     * Creates Aliases and adds it into the list for each aliasWord -> commandWord mapping in the provided Map.
     */
    public void addAliases(Map<String, String> aliases) {
        requireNonNull(aliases);
        for (String aliasWord : aliases.keySet()) {
            if (Alias.isValidAlias(aliasWord)) {
                addAlias(new Alias(aliasWord, aliases.get(aliasWord)));
            }
        }
    }

    /**
     * Adds or replaces the alias in the parser's list of aliases.
     */
    public void addAlias(Alias alias) {
        requireNonNull(alias);
        removeAlias(alias.getAliasWord());
        aliases.add(alias);
    }

    /**
     * Removes the provided alias from the parser's list of aliases.
     */
    public void removeAlias(String aliasWord) {
        requireNonNull(aliasWord);
        Optional<Alias> existing = getAlias(aliasWord);
        existing.ifPresent(aliases::remove);
    }

    /**
     * Returns an optional containing the alias with that alias word, or an empty optional if no alias matches.
     */
    public Optional<Alias> getAlias(String aliasWord) {
        return aliases.stream()
                .filter(a -> a.getAliasWord().equals(aliasWord))
                .findFirst();
    }

    /**
     * Tries to match the user input to an alias. Throws ParseException if none match.
     */
    private Command parseAliases(String userInput) throws ParseException {
        // matches the first word to the alias
        Optional<Alias> firstAlias = aliases.stream()
                .filter(alias -> userInput.split(" ")[0].equals(alias.getAliasWord()))
                .findFirst();

        Alias alias = firstAlias.orElseThrow(() -> new ParseException(MESSAGE_UNKNOWN_COMMAND));

        // replace the command of the user input, then try to parse again
        String newUserInput = alias.replaceFirst(userInput);
        return parseCommand(newUserInput);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddressBookParser
                && equalsIgnoreOrder(aliases, ((AddressBookParser) other).aliases));
    }
}
