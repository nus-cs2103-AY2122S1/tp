package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.commands.CommandTestUtil.ANSWER_DESC_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.IMPORTANCE_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_IMPORTANCE_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_KEYWORD_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_KEYWORD_DESC_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.INVALID_QUESTION_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.KEYWORD_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.OPTIONS_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.smartnus.logic.commands.CommandTestUtil.QUESTION_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.SAQ_ANSWER_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.TRUE_ANSWER_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TRUE_FALSE_ANSWER_1;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartnus.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.questions.AddSaqCommand;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.ShortAnswerQuestion;

class AddSaqCommandParserTest {

    private final AddSaqCommandParser parser = new AddSaqCommandParser();

    @Test
    void parse_allFieldsValid_success() {
        // TODO: Use question builder for this instead of generating it here
        Set<String> keywords = Collections.singleton("rowling");
        Set<Choice> expectedChoices = new HashSet<>();
        expectedChoices.add(new Choice("J. K. Rowling", true, keywords));
        Importance expectedImportance = new Importance(VALID_IMPORTANCE_1);
        Name expectedName = new Name(VALID_QUESTION_1);

        Question expectedQuestion = new ShortAnswerQuestion(expectedName, expectedImportance,
                new HashSet<>(), expectedChoices);

        AddSaqCommand expectedCommand = new AddSaqCommand(expectedQuestion);

        // normal command with preamble whitespace
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + QUESTION_DESC_1 + SAQ_ANSWER_DESC_1 + IMPORTANCE_DESC_1,
                expectedCommand);

        // accept only last argument for answer
        assertParseSuccess(parser,
                QUESTION_DESC_1 + ANSWER_DESC_2 + OPTIONS_DESC_1 + IMPORTANCE_DESC_1 + SAQ_ANSWER_DESC_1,
                expectedCommand);
    }

    @Test
    void parse_validKeywordsWithNonAlphanumericCharacters_success() {
        Set<String> keywords = new HashSet<>();
        keywords.add("rowling"); // keywords are saved in lowercase
        Set<Choice> expectedChoices = new HashSet<>();
        expectedChoices.add(new Choice("J. K. $%^Rowling)(ABC!)#@.?", true, keywords));

        Importance expectedImportance = new Importance(VALID_IMPORTANCE_1);
        Name expectedName = new Name(VALID_QUESTION_1);

        Question expectedQuestion = new ShortAnswerQuestion(expectedName, expectedImportance,
                new HashSet<>(), expectedChoices);

        AddSaqCommand expectedCommand = new AddSaqCommand(expectedQuestion);

        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + QUESTION_DESC_1 + " ans/J. K. k/$%^Rowling)(ABC!)#@.? " + IMPORTANCE_DESC_1,
                expectedCommand);
    }

    @Test
    void parse_fieldsOrPrefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSaqCommand.MESSAGE_USAGE);
        // answer missing
        assertParseFailure(parser, QUESTION_DESC_1 + IMPORTANCE_DESC_1, expectedMessage);
        // question missing
        assertParseFailure(parser, SAQ_ANSWER_DESC_1 + IMPORTANCE_DESC_1, expectedMessage);
        // importance missing
        assertParseFailure(parser, SAQ_ANSWER_DESC_1 + QUESTION_DESC_1, expectedMessage);
        // answer prefix missing
        assertParseFailure(parser, QUESTION_DESC_1 + VALID_TRUE_FALSE_ANSWER_1,
                expectedMessage);
        // question prefix missing
        assertParseFailure(parser, VALID_QUESTION_1 + TRUE_ANSWER_DESC, expectedMessage);
        // importance prefix missing
        assertParseFailure(parser, TRUE_ANSWER_DESC + QUESTION_DESC_1 + VALID_IMPORTANCE_1,
                expectedMessage);
        // keyword prefix missing
        assertParseFailure(parser, QUESTION_DESC_1 + IMPORTANCE_DESC_1 + TRUE_ANSWER_DESC, expectedMessage);
    }
    
    @Test
    void parse_keywordsPrefixNotWithinAnswer_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSaqCommand.MESSAGE_USAGE);
        assertParseFailure(parser, QUESTION_DESC_1 + KEYWORD_DESC_1 + TRUE_ANSWER_DESC + IMPORTANCE_DESC_1,
                expectedMessage);
    }

    @Test
    void parse_fieldsInvalid_failure() {
        // answer without keywords
        assertParseFailure(parser, QUESTION_DESC_1 + TRUE_ANSWER_DESC + IMPORTANCE_DESC_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSaqCommand.MESSAGE_USAGE));
        // invalid question
        assertParseFailure(parser, INVALID_QUESTION_DESC + SAQ_ANSWER_DESC_1 + IMPORTANCE_DESC_1,
                Name.MESSAGE_CONSTRAINTS);
        // invalid importance
        assertParseFailure(parser,
                QUESTION_DESC_1 + SAQ_ANSWER_DESC_1 + INVALID_IMPORTANCE_DESC,
                Importance.MESSAGE_CONSTRAINTS);
        // answer with blank keyword
        assertParseFailure(parser, QUESTION_DESC_1 + TRUE_ANSWER_DESC + INVALID_KEYWORD_DESC_1
                + IMPORTANCE_DESC_1, Choice.MESSAGE_KEYWORD_CONSTRAINTS);
        // answer with invalid keyword (only contains non-alphanumeric characters)
        assertParseFailure(parser, QUESTION_DESC_1 + TRUE_ANSWER_DESC + INVALID_KEYWORD_DESC_2
                + IMPORTANCE_DESC_1, Choice.MESSAGE_KEYWORD_CONSTRAINTS);
    }

}
