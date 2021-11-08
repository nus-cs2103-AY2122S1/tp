package seedu.smartnus.logic.commands.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.logic.commands.quiz.PrevQuestionCommand.MESSAGE_START_OF_QUIZ;
import static seedu.smartnus.logic.commands.quiz.PrevQuestionCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.UserPrefs;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.model.util.SampleDataUtil;



class PrevQuestionCommandTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;
    private PrevQuestionCommand prevQuestionCommand;
    private Model model;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
        model = new ModelManager(smartNus, new UserPrefs());
        prevQuestionCommand = new PrevQuestionCommand(quizManager);
    }

    @Test
    public void execute_returnsSuccessCommandResult() {
        quizManager.nextQuestion();
        assertEquals(prevQuestionCommand.execute(model), new CommandResult(MESSAGE_SUCCESS));
    }

    @Test
    public void execute_returnsStartOfQuizCommandResult() {
        assertEquals(prevQuestionCommand.execute(model), new CommandResult(MESSAGE_START_OF_QUIZ));
    }

    @Test
    public void equals_returnsTrue() {
        assertEquals(prevQuestionCommand, new PrevQuestionCommand(quizManager));
    }

}
