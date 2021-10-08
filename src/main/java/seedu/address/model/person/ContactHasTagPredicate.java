package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the tags given.
 */
public class ContactHasTagPredicate implements Predicate<Person> {

    private final List<String> tags;

    public ContactHasTagPredicate(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        return tags.stream()
                .anyMatch(tag -> person.getTags().contains(new Tag(tag.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactHasTagPredicate // instanceof handles nulls
                && tags.equals(((ContactHasTagPredicate) other).tags)); // state check
    }
}
