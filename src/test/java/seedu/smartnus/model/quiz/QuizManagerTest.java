package seedu.smartnus.model.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.quiz.exceptions.QuestionAlreadyAnsweredException;
import seedu.smartnus.model.quiz.exceptions.QuizOutOfBoundException;
import seedu.smartnus.model.util.SampleDataUtil;

public class QuizManagerTest {

    private ReadOnlySmartNus smartNus;
    private QuizManager quizManager;

    @BeforeEach
    public void setUp() {
        smartNus = SampleDataUtil.getSampleSmartNus();
        quizManager = new QuizManager(smartNus.getQuestionList());
    }

    @Test
    public void getCurrentQuestions_returnsCorrectQuestion() {
        assertEquals(quizManager.currQuestion(), smartNus.getQuestionList().get(0));
    }

    @Test
    public void nextQuestion_success() {
        quizManager.nextQuestion();
        assertEquals(quizManager.getCurrentIndex(), 1);
    }

    @Test
    public void prevQuestion_throwsQuizOutOfBoundException() {
        assertThrows(QuizOutOfBoundException.class, () -> quizManager.prevQuestion());
    }

    @Test
    public void getTotalQuestions_returnsCorrectNumber() {
        assertEquals(quizManager.getTotalQuestions(), smartNus.getQuestionList().size());
    }

    @Test
    public void getCurrentIndex_returnsCorrectIndex() {
        assertEquals(quizManager.getCurrentIndex(), 0);
    }

    @Test
    public void attemptAndCheckAnswer_returnsTrue() {
        Choice choice = quizManager.currQuestion().getCorrectChoice();
        assertTrue(quizManager.attemptAndCheckAnswer(choice));
    }

    @Test
    public void attemptAndCheckAnswer_returnsFalse() {
        ArrayList<Choice> choices = new ArrayList<>(quizManager.currQuestion().getChoices());
        Choice correctChoice = quizManager.currQuestion().getCorrectChoice();
        choices.remove(correctChoice);
        assertFalse(quizManager.attemptAndCheckAnswer(choices.get(0)));
    }

    @Test
    public void attemptAndCheckAnswer_throwsQuestionAlreadyAnsweredException() {
        Choice choice = quizManager.currQuestion().getCorrectChoice();
        quizManager.attemptAndCheckAnswer(choice);
        assertThrows(QuestionAlreadyAnsweredException.class, () -> quizManager.attemptAndCheckAnswer(choice));
    }

    @Test
    public void getCurrentSelectedChoice_returnsCorrectChoice() {
        Choice choice = quizManager.currQuestion().getOrderedChoices().get(0);
        quizManager.attemptAndCheckAnswer(choice);
        assertEquals(choice, quizManager.getCurrentSelectedChoice());
    }
}
