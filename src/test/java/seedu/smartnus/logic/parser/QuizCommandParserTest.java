package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_NO_LIMIT_AND_INDEXES;
import static seedu.smartnus.commons.core.Messages.MESSAGE_NO_TAGS_AND_INDEXES;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.logic.parser.quiz.QuizCommandParser;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.comparator.QuestionsDefaultComparator;
import seedu.smartnus.model.question.predicates.LimitQuestionsPredicate;
import seedu.smartnus.model.question.predicates.ShowAllQuestionsPredicate;
import seedu.smartnus.model.question.predicates.ShowQuestionIndexPredicate;
import seedu.smartnus.model.question.predicates.TagsContainKeywordsPredicate;

class QuizCommandParserTest {

    public static final String INVALID_ARGUMENT = "@TASedg";
    private final QuizCommandParser parser = new QuizCommandParser();
    private ArrayList<Predicate<Question>> filterPredicates;
    private final Comparator<Question> defaultComparator = new QuestionsDefaultComparator();

    @BeforeEach
    public void setUp() {
        filterPredicates = new ArrayList<>();
        filterPredicates.add(new ShowAllQuestionsPredicate());
    }

    @Test
    void parse_withInvalidArgs_throwParseException() {
        assertParseFailure(parser, INVALID_ARGUMENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuizCommand.MESSAGE_USAGE));

        // Users entering index and tag
        assertParseFailure(parser, " n/1 2 t/abcdef t/defg",
                String.format(MESSAGE_NO_TAGS_AND_INDEXES, QuizCommand.MESSAGE_USAGE));

        // User entering index and limit
        assertParseFailure(parser, " n/1 2 lim/4",
                String.format(MESSAGE_NO_LIMIT_AND_INDEXES, QuizCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_withValidArgs_success() {
        assertParseSuccess(parser, "      ", new QuizCommand(filterPredicates, null));
        assertParseSuccess(parser, "", new QuizCommand(filterPredicates, null));

        // with tags
        String tags = " t/CS2103T t/ST2334 ";
        filterPredicates.add(new TagsContainKeywordsPredicate(Arrays.asList("CS2103T", "ST2334")));

        assertParseSuccess(parser, tags,
                new QuizCommand(filterPredicates, null));

        // with limit and tags
        String limit = "lim/ 4 lim/ 3";
        // takes the last limit passed in
        filterPredicates.add(new LimitQuestionsPredicate(3));
        assertParseSuccess(parser, tags + limit, new QuizCommand(filterPredicates, defaultComparator));
    }

    @Test
    void parse_withIndex() {
        filterPredicates.add(new ShowQuestionIndexPredicate(Index.fromOneBased(1)));
        assertParseSuccess(parser, " n/1", new QuizCommand(filterPredicates, null));
    }
}
