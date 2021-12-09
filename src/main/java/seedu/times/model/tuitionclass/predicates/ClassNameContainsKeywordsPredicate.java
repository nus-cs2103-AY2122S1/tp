package seedu.times.model.tuitionclass.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.times.model.tuitionclass.TuitionClass;

/**
 * Tests that a {@code TuitionClass}'s {@code ClassName} matches any of the keywords given.
 */
public class ClassNameContainsKeywordsPredicate implements Predicate<TuitionClass> {

    private final List<String> keywords;

    public ClassNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(TuitionClass tuitionClass) {
        String classNameLowerCase = tuitionClass.getClassName().className.toLowerCase();
        return keywords.stream()
                .anyMatch(keyword -> classNameLowerCase.contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ClassNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
