package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the tag keywords given.
 */
public class TagsContainKeywordsPredicate implements Predicate<Person> {
    private final List<Tag> tags;

    public TagsContainKeywordsPredicate(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        return tags.stream()
                .anyMatch(tags -> person.getTags().contains(tags));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainKeywordsPredicate // instanceof handles nulls
                && tags.equals(((TagsContainKeywordsPredicate) other).tags));
    }
}
