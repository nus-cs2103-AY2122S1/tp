package seedu.smartnus.testutil;

import java.util.ArrayList;

import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.quiz.Quiz;

public class TypicalQuiz {

    public static final Quiz DEFAULT_QUIZ = new QuizBuilder().build();
    public static final Quiz SEVEN_QUESTIONS_QUIZ = new QuizBuilder(TypicalQuestions.getTypicalQuestions())
            .build();
    public static final Quiz FIVE_QUESTIONS_QUIZ = new QuizBuilder(
            new ArrayList<Question>() {
                {
                    add(TypicalQuestions.MCQ_QUESTION_1);
                    add(TypicalQuestions.MCQ_QUESTION_2);
                    add(TypicalQuestions.TF_QUESTION_2);
                    add(TypicalQuestions.TF_QUESTION_3);
                    add(TypicalQuestions.SAQ_QUESTION_1);
                }
            }
    ).build();

    private TypicalQuiz() {} // Prevents instantiation

}
