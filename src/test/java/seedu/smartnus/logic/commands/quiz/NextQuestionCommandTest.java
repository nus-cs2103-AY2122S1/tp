package seedu.smartnus.logic.commands.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.logic.commands.quiz.NextQuestionCommand.MESSAGE_END_OF_QUIZ;
import static seedu.smartnus.logic.commands.quiz.NextQuestionCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;




class NextQuestionCommandTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private NextQuestionCommand nextQuestionCommand;
    private Model model;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        model = new ModelManager(smartNus, new UserPrefs());
        nextQuestionCommand = new NextQuestionCommand(quizManager);
    }

    @Test
    public void execute_returnsSuccessCommandResult() {
        assertEquals(nextQuestionCommand.execute(model), new CommandResult(MESSAGE_SUCCESS));
    }

    @Test
    public void execute_returnsEndOfQuizCommandResult() {
        while (!quizManager.isLastQuestion()) {
            quizManager.nextQuestion();
        }
        assertEquals(nextQuestionCommand.execute(model), new CommandResult(MESSAGE_END_OF_QUIZ
                + "\nHere is the quiz statistic: " + quizManager.getStatistic()));
    }

    @Test
    public void equals_returnsTrue() {
        assertEquals(nextQuestionCommand, new NextQuestionCommand(quizManager));
    }

}
