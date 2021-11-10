package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND;

import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddShortcutCommand object
 */
public class AddShortcutCommandParser implements Parser<AddShortcutCommand> {

    @Override
    public AddShortcutCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMMAND);
        if (argMultimap.getPreamble().isBlank() || !argMultimap.getValue(PREFIX_COMMAND).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
        }
        String[] splitInput = trimmedArgs.split(PREFIX_COMMAND.toString());
        if (splitInput.length != 2 || splitInput[1].isBlank()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
        }
        String commandString = splitInput[1];
        String keyword = argMultimap.getPreamble();
        return new AddShortcutCommand(keyword, commandString);
    }
}
