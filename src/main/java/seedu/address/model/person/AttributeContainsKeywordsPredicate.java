package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a general predicate to test if an attribute of a person matches the keywords given.
 */
public abstract class AttributeContainsKeywordsPredicate implements Predicate<Person> {
    protected final List<String> keywords;

    protected AttributeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public boolean isEmpty() {
        return keywords.stream().allMatch(String::isEmpty);
    }

}
