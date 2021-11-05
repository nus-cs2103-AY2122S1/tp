package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {

    /**
     * The empty version of NameContainsKeywordsPredicate that tests true for all input.
     */
    private static class EmptyNameContainsKeywordsPredicate extends NameContainsKeywordsPredicate {
        public EmptyNameContainsKeywordsPredicate() {
            super(List.of(""));
        }


        @Override
        public boolean test(Person person) {
            return true;
        }


    }

    public static final NameContainsKeywordsPredicate EMPTY =
            new EmptyNameContainsKeywordsPredicate();

    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
