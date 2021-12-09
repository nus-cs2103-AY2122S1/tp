package seedu.times.model.tuitionclass.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.times.commons.util.StringUtil;
import seedu.times.model.tuitionclass.TuitionClass;

/**
 * Tests that a {@code TuitionClass}'s {@code ClassTiming} matches any of the keywords given.
 */
public class ClassTimingContainsKeywordsPredicate implements Predicate<TuitionClass> {

    private final List<String> keywords;

    public ClassTimingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(TuitionClass tuitionClass) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(tuitionClass.getClassTiming().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassTimingContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ClassTimingContainsKeywordsPredicate) other).keywords)); // state check
    }
}
