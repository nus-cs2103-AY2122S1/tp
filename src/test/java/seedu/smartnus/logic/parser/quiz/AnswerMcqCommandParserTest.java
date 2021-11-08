package seedu.smartnus.logic.parser.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.commons.core.Messages.MESSAGE_CONTINUE_QUIZ;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_MCQ_ANSWER_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.quiz.AnswerMcqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;


class AnswerMcqCommandParserTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerMcqCommandParser answerMcqCommandParser;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        answerMcqCommandParser = new AnswerMcqCommandParser();
    }

    @Test
    public void parse_validArgs() throws ParseException {
        assertEquals(answerMcqCommandParser.parse("a", quizManager), new AnswerMcqCommand("a", quizManager));
        assertEquals(answerMcqCommandParser.parse("b", quizManager), new AnswerMcqCommand("b", quizManager));
        assertEquals(answerMcqCommandParser.parse("c", quizManager), new AnswerMcqCommand("c", quizManager));
        assertEquals(answerMcqCommandParser.parse("d", quizManager), new AnswerMcqCommand("d", quizManager));
        assertEquals(answerMcqCommandParser.parse("A", quizManager), new AnswerMcqCommand("A", quizManager));
        assertEquals(answerMcqCommandParser.parse("B", quizManager), new AnswerMcqCommand("B", quizManager));
        assertEquals(answerMcqCommandParser.parse("C", quizManager), new AnswerMcqCommand("C", quizManager));
        assertEquals(answerMcqCommandParser.parse("D", quizManager), new AnswerMcqCommand("D", quizManager));
    }

    @Test
    public void parse_invalidArgs_notAlreadyAnswered() {
        Exception exception;
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerMcqCommandParser.parse("e", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_MCQ_ANSWER_FORMAT);
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerMcqCommandParser.parse("E", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_MCQ_ANSWER_FORMAT);
        exception = Assertions.assertThrows(ParseException.class, () ->
                answerMcqCommandParser.parse("fasd", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_INVALID_MCQ_ANSWER_FORMAT);
    }

    @Test
    public void parse_invalidArgs_alreadyAnswered() {
        Choice choice = quizManager.currQuestion().getCorrectChoice();
        quizManager.attemptAndCheckAnswer(choice);
        Exception exception = Assertions.assertThrows(ParseException.class, () ->
                answerMcqCommandParser.parse("fasd", quizManager));
        assertEquals(exception.getMessage(), MESSAGE_UNKNOWN_COMMAND + "\n" + MESSAGE_CONTINUE_QUIZ);
    }



}
