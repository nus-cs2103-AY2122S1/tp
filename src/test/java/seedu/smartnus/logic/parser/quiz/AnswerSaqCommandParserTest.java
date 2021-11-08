package seedu.smartnus.logic.parser.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.commons.core.Messages.MESSAGE_CONTINUE_QUIZ;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_SAQ_ANSWER_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.smartnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.quiz.AnswerSaqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;


class AnswerSaqCommandParserTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerSaqCommandParser answerSaqCommandParser;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        answerSaqCommandParser = new AnswerSaqCommandParser();
    }

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> answerSaqCommandParser.parse(null, quizManager));
    }

    @Test
    public void parse_validArgs() throws ParseException {
        assertEquals(answerSaqCommandParser.parse("ans/abc def ans/abs", quizManager),
                new AnswerSaqCommand("abc def ans/abs", quizManager));
        assertEquals(answerSaqCommandParser.parse("ans/   abc def ans/abs  ", quizManager),
                new AnswerSaqCommand("abc def ans/abs", quizManager));
    }

    @Test
    public void parse_invalidArgs() {
        Exception exception;
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerSaqCommandParser.parse("", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_SAQ_ANSWER_FORMAT);
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerSaqCommandParser.parse("anse/", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_SAQ_ANSWER_FORMAT);
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerSaqCommandParser.parse("abcd/", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_SAQ_ANSWER_FORMAT);
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerSaqCommandParser.parse("answer", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_SAQ_ANSWER_FORMAT);
    }

    @Test
    public void parse_invalidArgs_alreadyAnswered() {
        Choice choice = quizManager.currQuestion().getCorrectChoice();
        quizManager.attemptAndCheckAnswer(choice);
        Exception exception = Assertions.assertThrows(ParseException.class, () ->
                answerSaqCommandParser.parse("asda", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_UNKNOWN_COMMAND + "\n" + MESSAGE_CONTINUE_QUIZ);
    }
}
