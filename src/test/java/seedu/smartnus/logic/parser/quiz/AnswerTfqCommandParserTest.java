package seedu.smartnus.logic.parser.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.commons.core.Messages.MESSAGE_CONTINUE_QUIZ;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_TF_ANSWER_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.quiz.AnswerTfqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;


class AnswerTfqCommandParserTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerTfqCommandParser answerTfqCommandParser;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        answerTfqCommandParser = new AnswerTfqCommandParser();
    }

    @Test
    public void parse_validArgs() throws ParseException {
        assertEquals(answerTfqCommandParser.parse("t", quizManager), new AnswerTfqCommand("t", quizManager));
        assertEquals(answerTfqCommandParser.parse("T", quizManager), new AnswerTfqCommand("T", quizManager));
        assertEquals(answerTfqCommandParser.parse("f", quizManager), new AnswerTfqCommand("f", quizManager));
        assertEquals(answerTfqCommandParser.parse("F", quizManager), new AnswerTfqCommand("F", quizManager));
        assertEquals(answerTfqCommandParser.parse("True", quizManager),
                new AnswerTfqCommand("True", quizManager));
        assertEquals(answerTfqCommandParser.parse("true", quizManager),
                new AnswerTfqCommand("true", quizManager));
        assertEquals(answerTfqCommandParser.parse("False", quizManager),
                new AnswerTfqCommand("False", quizManager));
        assertEquals(answerTfqCommandParser.parse("false", quizManager),
                new AnswerTfqCommand("false", quizManager));
        assertEquals(answerTfqCommandParser.parse("TRUE", quizManager),
                new AnswerTfqCommand("TRUE", quizManager));
        assertEquals(answerTfqCommandParser.parse("FALSE", quizManager),
                new AnswerTfqCommand("FALSE", quizManager));
        assertEquals(answerTfqCommandParser.parse("TrUe", quizManager),
                new AnswerTfqCommand("TrUe", quizManager));
    }

    @Test
    public void parse_invalidArgs() {
        Exception exception;
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerTfqCommandParser.parse("B", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_TF_ANSWER_FORMAT);
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerTfqCommandParser.parse("tt", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_TF_ANSWER_FORMAT);
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerTfqCommandParser.parse("tf", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_TF_ANSWER_FORMAT);
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerTfqCommandParser.parse("truee", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_TF_ANSWER_FORMAT);
    }

    @Test
    public void parse_invalidArgs_alreadyAnswered() {
        Choice choice = quizManager.currQuestion().getCorrectChoice();
        quizManager.attemptAndCheckAnswer(choice);
        Exception exception = Assertions.assertThrows(ParseException.class, () ->
                answerTfqCommandParser.parse("tt", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_UNKNOWN_COMMAND + "\n" + MESSAGE_CONTINUE_QUIZ);
    }



}
