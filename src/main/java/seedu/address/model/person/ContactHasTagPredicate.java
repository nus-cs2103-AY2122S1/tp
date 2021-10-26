package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

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
        if (!tags.isEmpty()) {
            return tags.stream()
                .anyMatch(tag -> person.getTags().contains(new Tag(tag.toLowerCase())));
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactHasTagPredicate // instanceof handles nulls
                && tags.equals(((ContactHasTagPredicate) other).tags)); // state check
    }
}
