package seedu.smartnus.model.quiz;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.exceptions.QuizOutOfBoundException;
import seedu.smartnus.model.statistic.Statistic;

/**
 * The API for the Quiz component.
 */
public interface Quiz {
    /**
     * Gets the current question.
     * @return The current question.
     */
    public Question currQuestion();

    /**
     * Gets the next question.
     * @return The question after the current question.
     * @throws QuizOutOfBoundException when navigation goes out of bound.
     */
    public Question nextQuestion() throws QuizOutOfBoundException;

    /**
     * Gets the previous question.
     * @return The question before the current question.
     * @throws QuizOutOfBoundException when navigation goes out of bound.
     */
    public Question prevQuestion() throws QuizOutOfBoundException;

    /**
     * Gets the statistic of the quiz.
     * @return the Statistic of the quiz.
     */
    public Statistic getStatistic();

    /**
     * Attempts the current question.
     * @param choice The choice to the question.
     * @return True if the attempt is correct. False otherwise.
     */
    public boolean attemptAndCheckAnswer(Choice choice);
}
