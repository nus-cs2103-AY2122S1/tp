package seedu.smartnus.logic.parser.quiz;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_NO_LIMIT_AND_INDEXES;
import static seedu.smartnus.commons.core.Messages.MESSAGE_NO_TAGS_AND_INDEXES;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_NUMBER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.logic.parser.ArgumentMultimap;
import seedu.smartnus.logic.parser.ArgumentTokenizer;
import seedu.smartnus.logic.parser.Parser;
import seedu.smartnus.logic.parser.ParserUtil;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.comparator.QuestionsDefaultComparator;
import seedu.smartnus.model.question.predicates.LimitQuestionsPredicate;
import seedu.smartnus.model.question.predicates.ShowAllQuestionsPredicate;
import seedu.smartnus.model.question.predicates.ShowQuestionIndexPredicate;
import seedu.smartnus.model.question.predicates.TagsContainKeywordsPredicate;
import seedu.smartnus.model.tag.Tag;

public class QuizCommandParser implements Parser<QuizCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the QuizCommand
     * and returns a QuizCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public QuizCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG, PREFIX_NUMBER, PREFIX_LIMIT);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuizCommand.MESSAGE_USAGE));
        }

        Collection<String> indexes = argMultimap.getAllValues(PREFIX_NUMBER);
        Collection<String> tags = argMultimap.getAllValues(PREFIX_TAG);
        Optional<String> limit = argMultimap.getValue(PREFIX_LIMIT);

        boolean hasTagArgs = tags.size() > 0;
        boolean hasIndexArgs = indexes.size() > 0;
        boolean hasLimitArg = limit.isPresent();

        // Check if the user passed in both index and tags
        if (hasIndexArgs && hasTagArgs) {
            throw new ParseException(String.format(MESSAGE_NO_TAGS_AND_INDEXES, QuizCommand.MESSAGE_USAGE));
        }

        // Check if user passed in both limit and index
        if (hasIndexArgs && hasLimitArg) {
            throw new ParseException(String.format(MESSAGE_NO_LIMIT_AND_INDEXES, QuizCommand.MESSAGE_USAGE));
        }

        // ArrayList of Predicates for question to be filtered by
        ArrayList<Predicate<Question>> filterPredicates = new ArrayList<>();
        filterPredicates.add(new ShowAllQuestionsPredicate());
        Comparator<Question> questionComparator = null;

        // Check if no arguments are given
        if (!hasTagArgs && !hasIndexArgs && !hasLimitArg) {
            return new QuizCommand(filterPredicates, questionComparator, true);
        }

        // Adds to filter predicate based on arguments the user has passed in
        if (hasIndexArgs) {
            Set<Index> indexSet = ParserUtil.parseQuizIndexes(indexes);
            filterPredicates.add(getIndexPredicate(indexSet));
            return new QuizCommand(filterPredicates, questionComparator, true);
        } else if (hasTagArgs) {
            Set<Tag> tagList = ParserUtil.parseTags(tags);
            List<String> tagKeywords = new ArrayList<>();
            tagList.stream().forEach(tag -> tagKeywords.add(tag.getTagName()));
            filterPredicates.add(getTagPredicate(tagKeywords));
        }

        if (hasLimitArg) {
            int quizLimit = ParserUtil.parseQuizLimit(limit.get());
            filterPredicates.add(getLimitPredicate(quizLimit));
            questionComparator = new QuestionsDefaultComparator();
        }

        return new QuizCommand(filterPredicates, questionComparator);
    }

    private Predicate<Question> getTagPredicate(List<String> tagKeywords) {
        return new TagsContainKeywordsPredicate(tagKeywords);
    }

    private Predicate<Question> getIndexPredicate(Set<Index> indexes) {
        return new ShowQuestionIndexPredicate(indexes);
    }

    private Predicate<Question> getLimitPredicate(int limit) {
        return new LimitQuestionsPredicate(limit);
    }
}
