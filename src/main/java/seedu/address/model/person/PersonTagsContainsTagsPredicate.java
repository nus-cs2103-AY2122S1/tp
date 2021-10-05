package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches all of the tags given.
 */
public class PersonTagsContainsTagsPredicate implements Predicate<Person> {
    private final List<Tag> tags_list;

    public PersonTagsContainsTagsPredicate(List<Tag> tags_list) {
        this.tags_list = tags_list;
    }

    @Override
    public boolean test(Person person) {
        return tags_list.stream()
                .allMatch(tag -> Arrays.stream(person.getTags().toArray()).anyMatch(personTag->personTag.equals(tag)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonTagsContainsTagsPredicate // instanceof handles nulls
                && tags_list.equals(((PersonTagsContainsTagsPredicate) other).tags_list)); // state check
    }

}
