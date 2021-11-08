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


class AnswerMcqCommandTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerMcqCommand answerMcqCommand;
    private Model model;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        model = new ModelManager(smartNus, new UserPrefs());
        answerMcqCommand = new AnswerMcqCommand("a", quizManager);
    }

    @Test
    public void constructor_invalidInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new AnswerMcqCommand("e", quizManager));
    }

    @Test
    public void execute_nullModel_throwsAssertionError() {
        assertThrows(NullPointerException.class, () -> answerMcqCommand.execute(null));
    }

    @Test
    public void execute_validInput_returnsCorrectCommandResult() {
        Question question = quizManager.currQuestion();
        Choice correctChoice = question.getCorrectChoice();
        int idx = question.getOrderedChoices().indexOf(correctChoice);
        answerMcqCommand = new AnswerMcqCommand(Character.toString("abcd".charAt(idx)), quizManager);
        assertEquals(answerMcqCommand.execute(model),
                new CommandResult("Correct!\n" + MESSAGE_CONTINUE_QUIZ));
    }

    @Test
    public void execute_validInput_returnsIncorrectCommandResult() {
        Question question = quizManager.currQuestion();
        Choice correctChoice = question.getCorrectChoice();
        int idx = question.getOrderedChoices().indexOf(correctChoice);
        answerMcqCommand = new AnswerMcqCommand(Character.toString("dcba".charAt(idx)), quizManager);
        assertEquals(answerMcqCommand.execute(model),
                new CommandResult("Incorrect. The correct answer is: "
                        + question.getCorrectChoiceTitle() + "\n" + MESSAGE_CONTINUE_QUIZ));
    }

    @Test
    public void execute_multipleInputs_returnsAlreadyAnsweredCommandResult() {
        answerMcqCommand = new AnswerMcqCommand("a", quizManager);
        answerMcqCommand.execute(model);
        assertEquals(answerMcqCommand.execute(model),
                new CommandResult("You have already answered this question.\n" + MESSAGE_CONTINUE_QUIZ));
    }

}
