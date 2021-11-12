package seedu.address.model.person.predicates;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Address} contains the keywords given.
 */
public class AddressContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final boolean isFindAll;

    /**
     * Constructor for the AddressContainsKeywordsPredicate class
     * @param keywords The keywords to compare against the {@code Person}'s {@code Address}
     * @param isFindAll True if all keywords need to match, false otherwise
     */
    public AddressContainsKeywordsPredicate(List<String> keywords, boolean isFindAll) {
        requireNonNull(keywords);
        this.keywords = keywords;
        this.isFindAll = isFindAll;
    }

    @Override
    public boolean test(Person person) {
        if (isFindAll) {
            return keywords.stream().allMatch(keyword -> person.getAddress().value.toLowerCase(Locale.ROOT)
                            .contains(keyword.toLowerCase(Locale.ROOT)));
        }
        return keywords.stream().anyMatch(keyword -> person.getAddress().value.toLowerCase(Locale.ROOT)
                                .contains(keyword.toLowerCase(Locale.ROOT)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AddressContainsKeywordsPredicate) other).keywords))
                && isFindAll == ((AddressContainsKeywordsPredicate) other).isFindAll; // state check
    }
}
