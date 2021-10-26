package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

public class TagsContainsKeywordsPredicate implements Predicate<Person> {
    private final List<Tag> tags;

    public TagsContainsKeywordsPredicate(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        return tags.stream()
                .allMatch(tags -> person.getTags().contains(tags));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsKeywordsPredicate // instanceof handles nulls
                && tags.equals(((TagsContainsKeywordsPredicate) other).tags));
    }
}
