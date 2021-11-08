package seedu.smartnus.model.question.predicates;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Question;

/**
 * Tests that a Question has an Importance value.
 */
public class HasImportancePredicate implements Predicate<Question> {
    private final Importance importance;

    /**
     * Constructs the HasImportancePredicate with the Importance for a Question to be matched against.
     * @param importance The Importance for the question to be matched against.
     */
    public HasImportancePredicate(Importance importance) {
        requireNonNull(importance);
        this.importance = importance;
    }

    @Override
    public boolean test(Question question) {
        return question.getImportance().equals(importance);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HasImportancePredicate // instanceof handles nulls
                && importance.equals(((HasImportancePredicate) other).importance)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(importance);
    }
}
