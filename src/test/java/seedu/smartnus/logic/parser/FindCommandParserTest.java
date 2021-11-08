package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.FindCommand;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicates.HasImportancePredicate;
import seedu.smartnus.model.question.predicates.NameContainsKeywordsPredicate;
import seedu.smartnus.model.question.predicates.TagsContainKeywordsPredicate;
import seedu.smartnus.model.tag.Tag;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidKeyword_throwsParseException() {
        assertParseFailure(parser, ",.?!:;*()[]{}\"", FindCommand.MESSAGE_INVALID_KEYWORDS);
    }

    @Test
    public void parse_validArgsWithPunctuation_returnsFindCommand() {
        ArrayList<Predicate<Question>> predicates = new ArrayList<>();
        NameContainsKeywordsPredicate expectedNamePredicate =
                new NameContainsKeywordsPredicate(Arrays.asList("math", "english"));
        predicates.add(expectedNamePredicate);
        FindCommand expectedFindCommand =
                new FindCommand(predicates);
        assertParseSuccess(parser, "math,english!?", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // find by question name
        // no leading and trailing whitespaces
        ArrayList<Predicate<Question>> predicates = new ArrayList<>();
        NameContainsKeywordsPredicate expectedNamePredicate =
                new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        predicates.add(expectedNamePredicate);
        FindCommand expectedFindCommand =
                new FindCommand(predicates);
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
        // no leading and trailing whitespaces

        // find by name and tags
        TagsContainKeywordsPredicate expectedTagsPredicate =
                new TagsContainKeywordsPredicate(Arrays.asList("math", "english"));
        predicates.add(expectedTagsPredicate);
        expectedFindCommand = new FindCommand(predicates);
        assertParseSuccess(parser, "Alice Bob t/math t/english", expectedFindCommand);

        // find by name, tags and importance
        HasImportancePredicate expectedImportancePredicate =
                new HasImportancePredicate(new Importance("1"));
        predicates.add(expectedImportancePredicate);
        expectedFindCommand = new FindCommand(predicates);
        assertParseSuccess(parser, "Alice Bob t/math t/english i/1", expectedFindCommand);

        // reordered inputs
        assertParseSuccess(parser, "Alice Bob t/math i/1 t/english", expectedFindCommand);
    }

    @Test
    public void parse_invalidParameters_throwsParseException() {
        assertParseFailure(parser, "  i/  ", Importance.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " i/abc ", Importance.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "  t/  ", Tag.MESSAGE_CONSTRAINTS);
        // valid importance invalid tag name
        assertParseFailure(parser, " i/1 t/abc def", Tag.MESSAGE_CONSTRAINTS);
    }

}
