package seedu.smartnus.model.quiz;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.testutil.TypicalQuestions.MCQ_QUESTION_1;
import static seedu.smartnus.testutil.TypicalQuestions.MCQ_QUESTION_2;
import static seedu.smartnus.testutil.TypicalQuestions.TF_QUESTION_2;
import static seedu.smartnus.testutil.TypicalQuiz.FIVE_QUESTIONS_QUIZ;
import static seedu.smartnus.testutil.TypicalQuiz.SEVEN_QUESTIONS_QUIZ;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.quiz.exceptions.QuizOutOfBoundException;

public class QuizTest {

    @Test
    public void isSameQuizQuestion() {
        // Same object -> Returns true
        assertTrue(FIVE_QUESTIONS_QUIZ.currQuestion().isSameQuestion(MCQ_QUESTION_1));

        assertTrue(FIVE_QUESTIONS_QUIZ.nextQuestion().isSameQuestion(MCQ_QUESTION_2));
    }

    @Test
    public void isNavigable() {
        Quiz testQuizObject = SEVEN_QUESTIONS_QUIZ;

        // First question is MCQ_QUESTION_1 -> returns True
        assertTrue(testQuizObject.currQuestion().isSameQuestion(MCQ_QUESTION_1));

        // Second question is STORAGE_QUESTION_2 -> returns True
        assertTrue(testQuizObject.nextQuestion().isSameQuestion(MCQ_QUESTION_2));

        // Third question is TF_QUESTION_2 -> returns True
        assertTrue(testQuizObject.nextQuestion().isSameQuestion(TF_QUESTION_2));

        // Third question is TF_QUESTION_2 -> returns True
        assertTrue(testQuizObject.currQuestion().isSameQuestion(TF_QUESTION_2));

        // Second question is STORAGE_QUESTION_2 -> returns True
        assertTrue(testQuizObject.prevQuestion().isSameQuestion(MCQ_QUESTION_2));

        // First question is MCQ_QUESTION_1 -> returns False
        assertFalse(testQuizObject.prevQuestion().isSameQuestion(MCQ_QUESTION_2));
    }

    @Test
    public void testException() {
        Quiz testQuizObject = FIVE_QUESTIONS_QUIZ;
        // Try to go to the previous question when we are at the first question -> out of bound
        assertThrows(QuizOutOfBoundException.class, () -> testQuizObject.prevQuestion());
    }

}
