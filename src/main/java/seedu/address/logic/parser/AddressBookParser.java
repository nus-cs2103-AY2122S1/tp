package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddToFolderCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CreateFolderCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteFolderCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditFolderNameCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListFoldersCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern
            .compile("(?<commandWord>\\S+)(?<arguments>.*)");

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

        final String arguments = matcher.group("arguments");
        // using if-else and String.startsWith() instead of switch-case
        // allows for the flexibility of having commands with multiple words, e.g. rm -contacts
        if (userInput.startsWith(AddCommand.COMMAND_WORD)) {
            return new AddCommandParser().parse(arguments);
        } else if (userInput.startsWith(EditFolderNameCommand.COMMAND_WORD)) {
            return new EditFolderNameCommandParser().parse(arguments);
        } else if (userInput.startsWith(DeleteFolderCommand.COMMAND_WORD)) {
            return new DeleteFolderCommandParser().parse(arguments);
        } else if (userInput.startsWith(EditCommand.COMMAND_WORD)) {
            return new EditCommandParser().parse(arguments);
        } else if (userInput.startsWith(ClearCommand.COMMAND_WORD)) {
            return new ClearCommand();
        } else if (userInput.startsWith(DeleteCommand.COMMAND_WORD)) {
            return new DeleteCommandParser().parse(arguments);
        } else if (userInput.startsWith(FindCommand.COMMAND_WORD)) {
            return new FindCommandParser().parse(arguments);
        } else if (userInput.startsWith(ListFoldersCommand.COMMAND_WORD)) {
            return new ListFoldersCommand();
        } else if (userInput.startsWith(ListCommand.COMMAND_WORD)) {
            return new ListCommand();
        } else if (userInput.startsWith(ExitCommand.COMMAND_WORD)) {
            return new ExitCommand();
        } else if (userInput.startsWith(HelpCommand.COMMAND_WORD)) {
            return new HelpCommand();
        } else if (userInput.startsWith(CreateFolderCommand.COMMAND_WORD)) {
            return new CreateFolderCommandParser().parse(arguments);
        } else if (userInput.startsWith(AddToFolderCommand.COMMAND_WORD)) {
            return new AddToFolderParser().parse(arguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
