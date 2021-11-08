package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public abstract class AttributeContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final String type;

    /**
     * Constructor for the class.
     * @param keywords keywords to be searched.
     * @param type type to be searched.
     */
    public AttributeContainsKeywordsPredicate(List<String> keywords, String type) {
        this.keywords = keywords;
        this.type = type;
    }

    @Override
    public abstract boolean test(Person person);

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AttributeContainsKeywordsPredicate) other).keywords)); // state check
    }
}
