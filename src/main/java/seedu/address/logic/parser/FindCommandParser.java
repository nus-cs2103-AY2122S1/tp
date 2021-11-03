package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.IdContainsNumberPredicate;
import seedu.address.model.item.Item;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.model.item.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

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

        List<Predicate<Item>> predicates = new ArrayList<>();

        // Add name predicate if name(s) specified
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            predicates.add(
                    new NameContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_NAME))
            );
        }

        // Add id predicate if id(s) specified
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            Collection<Integer> queryIds = ParserUtil.parseIds(argMultimap.getAllValues(PREFIX_ID));
            predicates.add(new IdContainsNumberPredicate(queryIds));
        }

        // Add tag predicate if tag(s) specified
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            Collection<Tag> queryTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            predicates.add(new TagContainsKeywordsPredicate(queryTags));
        }

        return new FindCommand(predicates);
    }

}
