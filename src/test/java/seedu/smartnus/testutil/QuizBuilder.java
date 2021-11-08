package seedu.smartnus.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.Quiz;
import seedu.smartnus.model.quiz.QuizManager;

/**
 * A utility class to help with building Quiz Objects
 */
public class QuizBuilder {

    public static final Question DEFAULT_QUESTION_1 = new QuestionBuilder().build();
    public static final Question DEFAULT_QUESTION_2 = new QuestionBuilder().withName("1+1=?").build();
    public static final Question DEFAULT_QUESTION_3 = new QuestionBuilder().withName("2+2=?").build();

    private final List<Question> questions;

    /**
     * Creates a {@code QuestionBuilder} with the default details.
     */
    public QuizBuilder() {
        questions = new ArrayList<>();
        questions.add(DEFAULT_QUESTION_1);
        questions.add(DEFAULT_QUESTION_2);
        questions.add(DEFAULT_QUESTION_3);
    }

    /**
     * Initializes the QuestionBuilder with the data of {@code questions}
     * @param questions The list of questions.
     */
    public QuizBuilder(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Builds the {@code Quiz} that we are building.
     * @return A Quiz object.
     */
    public Quiz build() {
        // TODO: edit when more Quiz types are supported
        return new QuizManager(questions);
    }
}
