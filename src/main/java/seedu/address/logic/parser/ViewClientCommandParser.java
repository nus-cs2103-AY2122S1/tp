package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ViewClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.ClientContainsIdPredicate;

/**
 * Parses input arguments and creates a new ViewClientCommand object
 */
public class ViewClientCommandParser implements Parser<ViewClientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * ViewClientCommand and returns a ViewClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewClientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.length() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");
        try {
            Integer.parseInt(keywords[0]);
        } catch (NumberFormatException numberFormatException) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClientCommand.MESSAGE_USAGE));
        }
        return new ViewClientCommand(new ClientContainsIdPredicate(Arrays.asList(keywords)));
    }
}
