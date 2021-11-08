package seedu.fast.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Remark} contains any of the queries given.
 */
public class RemarkContainsKeywordPredicate implements Predicate<Person> {
    public final List<String> queries;

    public RemarkContainsKeywordPredicate(List<String> queries) {
        this.queries = queries;
    }

    /**
     * Tests to see if a Person has a remark containing a query.
     * @param person The Person being tested.
     * @return true if person's remark contains a query, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        String personsRemarkLowerCase = person.getRemark().value.toLowerCase();
        for (String query:queries) {
            String queryLowerCase = query.toLowerCase();
            // ignore case
            if (personsRemarkLowerCase.contains(queryLowerCase)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkContainsKeywordPredicate // instanceof handles nulls
                && queries.equals(((RemarkContainsKeywordPredicate) other).queries)); // state check
    }
}
