package seedu.smartnus.model.quiz.exceptions;

/**
 * Signals that the navigation operation will result in an out of bound error.
 */
public class QuestionAlreadyAnsweredException extends RuntimeException {
    public QuestionAlreadyAnsweredException() {
        super("You have already answered this question!");
    }
}
