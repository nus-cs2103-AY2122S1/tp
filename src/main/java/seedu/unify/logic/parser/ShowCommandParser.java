package seedu.unify.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.unify.logic.commands.FindCommand;
import seedu.unify.logic.commands.ShowCommand;
import seedu.unify.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new FindCommand object
 */

public class ShowCommandParser implements Parser<ShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.

     * @throws seedu.unify.logic.parser.exceptions.ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        String trimmedArgs;
        requireNonNull(args);
        trimmedArgs = args.trim();


        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new ShowCommand(Integer.parseInt(trimmedArgs));
    }
}
