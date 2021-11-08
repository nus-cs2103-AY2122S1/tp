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
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;


class AnswerSaqCommandTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private AnswerSaqCommand answerSaqCommand;
    private Model model;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        int saqIndex = SampleDataUtil.SAQ_QUESTION_INDEX;
        quizManager = new QuizManager(smartNus.getQuestionList());
        while (quizManager.getCurrentIndex() < saqIndex) {
            quizManager.nextQuestion();
        }
        model = new ModelManager(smartNus, new UserPrefs());
        answerSaqCommand = new AnswerSaqCommand("Harry Potter", quizManager);
    }

    @Test
    public void constructor_invalidInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new AnswerSaqCommand("", quizManager));
    }

    @Test
    public void execute_nullModel_throwsAssertionError() {
        assertThrows(NullPointerException.class, () -> answerSaqCommand.execute(null));
    }

    @Test
    public void execute_validInput_returnsCorrectCommandResult() {
        answerSaqCommand = new AnswerSaqCommand("harry potter and blah", quizManager);
        assertEquals(answerSaqCommand.execute(model),
                new CommandResult("Correct!\n" + MESSAGE_CONTINUE_QUIZ));
    }

    @Test
    public void execute_validInput_returnsIncorrectCommandResult() {
        Question question = quizManager.currQuestion();
        answerSaqCommand = new AnswerSaqCommand("Harry", quizManager);
        assertEquals(answerSaqCommand.execute(model),
                new CommandResult("Incorrect. The correct answer is: "
                        + question.getCorrectChoiceTitle() + "\n" + MESSAGE_CONTINUE_QUIZ));
    }

    @Test
    public void execute_multipleInputs_returnsAlreadyAnsweredCommandResult() {
        answerSaqCommand = new AnswerSaqCommand("a", quizManager);
        answerSaqCommand.execute(model);
        assertEquals(answerSaqCommand.execute(model),
                new CommandResult("You have already answered this question.\n" + MESSAGE_CONTINUE_QUIZ));
    }

}
