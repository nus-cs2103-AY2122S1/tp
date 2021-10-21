package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.IdContainsNumberPredicate;
import seedu.address.model.item.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        boolean areNames = false;
        boolean areIds = false;
        List<String> id;
        List<String> names;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        // Check that either name or id specified
        if (argMultimap.getValue(PREFIX_NAME).isEmpty() && argMultimap.getValue(PREFIX_ID).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // Parse id
        if (!argMultimap.getValue(PREFIX_ID).isEmpty()) {
            areIds = true;
        }

        // Initialise the list of names or id
        if (areIds) {
            id = argMultimap.getAllValues(PREFIX_ID);
            for (String element : id) {
                ParserUtil.parseId(element);
            }
            return new FindCommand((new IdContainsNumberPredicate(id)));
        }
        names = argMultimap.getAllValues(PREFIX_NAME);
        return new FindCommand((new NameContainsKeywordsPredicate(names)));
    }



}
