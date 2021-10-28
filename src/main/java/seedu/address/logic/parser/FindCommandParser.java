package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.IdContainsNumberPredicate;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.model.item.TagContainsKeywordsPredicate;

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
        boolean areIds = false;
        List<String> id;
        List<String> names;
        List<String> tags;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_TAG);

        // Check that either name or id or tag is specified
        if (argMultimap.getValue(PREFIX_NAME).isEmpty() && argMultimap.getValue(PREFIX_ID).isEmpty()
                && argMultimap.getValue(PREFIX_TAG).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // Parse id
        if (!argMultimap.getValue(PREFIX_ID).isEmpty()) {
            areIds = true;
        }

        // Parse tag
        if (!argMultimap.getValue(PREFIX_TAG).isEmpty()) {
            tags = argMultimap.getAllValues(PREFIX_TAG);
            return new FindCommand((new TagContainsKeywordsPredicate(tags)));
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
