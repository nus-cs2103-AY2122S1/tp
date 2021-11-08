package seedu.smartnus.logic.commands.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.commons.core.Messages.MESSAGE_CONTINUE_QUIZ;
import static seedu.smartnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;


class AnswerTfqCommandTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerTfqCommand answerTfqCommand;
    private Model model;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        model = new ModelManager(smartNus, new UserPrefs());
        answerTfqCommand = new AnswerTfqCommand("a", quizManager);
    }

    @Test
    public void constructor_invalidInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new AnswerMcqCommand("e", quizManager));
    }

    @Test
    public void execute_nullModel_throwsAssertionError() {
        assertThrows(NullPointerException.class, () -> answerTfqCommand.execute(null));
    }

    @Test
    public void execute_validInput_returnsCorrectCommandResult() {
        Question question = quizManager.nextQuestion();
        Choice correctChoice = question.getCorrectChoice();
        int idx = question.getOrderedChoices().indexOf(correctChoice);
        answerTfqCommand = new AnswerTfqCommand(Character.toString("tf".charAt(idx)), quizManager);
        assertEquals(answerTfqCommand.execute(model),
                new CommandResult("Correct!\n" + MESSAGE_CONTINUE_QUIZ));
    }

    @Test
    public void execute_validInput_returnsIncorrectCommandResult() {
        Question question = quizManager.nextQuestion();
        Choice correctChoice = question.getCorrectChoice();
        int idx = question.getOrderedChoices().indexOf(correctChoice);
        answerTfqCommand = new AnswerTfqCommand(Character.toString("ft".charAt(idx)), quizManager);
        assertEquals(answerTfqCommand.execute(model),
                new CommandResult("Incorrect. The correct answer is: "
                        + question.getCorrectChoiceTitle() + "\n" + MESSAGE_CONTINUE_QUIZ));
    }

    @Test
    public void execute_multipleInputs_returnsAlreadyAnsweredCommandResult() {
        quizManager.nextQuestion();
        answerTfqCommand = new AnswerTfqCommand("t", quizManager);
        answerTfqCommand.execute(model);
        assertEquals(answerTfqCommand.execute(model),
                new CommandResult("You have already answered this question.\n" + MESSAGE_CONTINUE_QUIZ));
    }

}
