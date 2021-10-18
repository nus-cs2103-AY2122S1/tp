package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

public class CaseNumberContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public CaseNumberContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getCaseNumber().value.contains(String.valueOf(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CaseNumberContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((CaseNumberContainsKeywordsPredicate) other).keywords)); // state check
    }
}
