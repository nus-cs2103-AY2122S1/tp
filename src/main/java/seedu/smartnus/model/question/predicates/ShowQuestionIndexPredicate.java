package seedu.smartnus.model.question.predicates;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.model.question.Question;

/**
 * Predicate to get a specific question from the question list based on the index.
 */
public class ShowQuestionIndexPredicate implements Predicate<Question> {

    private int currentIndex;
    private final Set<Index> targetIndexes;

    /**
     * Constructor for the predicate.
     * @param targetIndexes The target index set of the list.
     */
    public ShowQuestionIndexPredicate(Set<Index> targetIndexes) {
        this.currentIndex = 0;
        this.targetIndexes = targetIndexes;

    }

    /**
     * Constructor for the predicate.
     * @param targetIndex The target index of the list.
     */
    public ShowQuestionIndexPredicate(Index targetIndex) {
        this.currentIndex = 0;
        this.targetIndexes = new HashSet<Index>() {
            {
                add(targetIndex);
            }
        };
    }

    @Override
    public boolean test(Question question) {
        if (targetIndexes.contains(Index.fromZeroBased(currentIndex))) {
            currentIndex++;
            return true;
        } else {
            currentIndex++;
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowQuestionIndexPredicate // instanceof handles nulls
                && targetIndexes.equals(((ShowQuestionIndexPredicate) other).targetIndexes)); // state check
    }
}
