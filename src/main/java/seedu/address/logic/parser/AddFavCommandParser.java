package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


import seedu.address.logic.commands.AddFavCommand;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddFavCommand object
 */
public class AddFavCommandParser implements Parser<AddFavCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddFavCommand
     * and returns a AddFavCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddFavCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFavCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        if (nameKeywords.length > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFavCommand.MESSAGE_USAGE));
        }

        return new AddFavCommand(nameKeywords[0]);
    }

}
