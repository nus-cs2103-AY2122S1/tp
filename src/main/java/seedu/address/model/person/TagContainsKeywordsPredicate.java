package seedu.address.model.person;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Creates a TagContainsKeywordsPredicate which contains
     * a list of {@code keywords}.
     *
     * @param keywords of Tags.
     */
    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Test if the input {@code person} matches the predicate
     * by comparing the Tags.
     *
     * @param person to be tested against.
     * @return Boolean representation of whether the
     * person is the same as the predicate.
     */
    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getTags().contains(new Tag(keyword.toLowerCase(Locale.ROOT))));
    }

    /**
     * Method to compare two TagContainsKeywordsPredicate objects.
     *
     * @param other is the object that is going to be compared
     *              to the TagContainsKeywordsPredicate object that called this method.
     * @return boolean representation of whether the TagContainsKeywordsPredicate
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
