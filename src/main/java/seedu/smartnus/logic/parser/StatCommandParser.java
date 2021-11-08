package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.smartnus.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.smartnus.logic.commands.StatCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.statistic.TagStatistic;
import seedu.smartnus.model.statistic.predicates.StatContainsKeywordPredicate;
import seedu.smartnus.model.tag.Tag;

public class StatCommandParser implements Parser<StatCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        String trimmedArgs = args.trim();
        if (!trimmedArgs.isEmpty() && !arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StatCommand.MESSAGE_NO_TAG_PREFIX + StatCommand.MESSAGE_USAGE));
        }

        ArrayList<Predicate<TagStatistic>> predicates = new ArrayList<>();

        setTagsPredicate(argMultimap, predicates);

        return new StatCommand(predicates);
    }

    private void setTagsPredicate(ArgumentMultimap argMultimap, ArrayList<Predicate<TagStatistic>> predicates)
            throws ParseException {
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        if (!tagList.isEmpty()) {
            List<String> tagKeywords = new ArrayList<>();
            tagList.stream().forEach(tag -> tagKeywords.add(tag.getTagName()));
            predicates.add(new StatContainsKeywordPredicate(tagKeywords));
        }
    }
}
