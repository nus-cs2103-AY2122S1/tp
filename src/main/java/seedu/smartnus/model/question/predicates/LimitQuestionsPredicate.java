package seedu.smartnus.model.question.predicates;

import java.util.function.Predicate;

import seedu.smartnus.model.question.Question;



public class LimitQuestionsPredicate implements Predicate<Question> {

    private int questionLimit;

    public LimitQuestionsPredicate(int limit) {
        this.questionLimit = limit;
    }

    @Override
    public boolean test(Question question) {
        if (questionLimit > 0) {
            questionLimit--;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LimitQuestionsPredicate // instanceof handles nulls
                && this.questionLimit == ((LimitQuestionsPredicate) other).questionLimit); // state check
    }

}
