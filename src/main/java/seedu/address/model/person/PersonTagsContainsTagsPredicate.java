package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;


/**
 * Tests that a {@code Person}'s {@code Tag} matches all of the tags given.
 */
public class PersonTagsContainsTagsPredicate implements Predicate<Person> {
    private final List<Tag> tagList;

    public PersonTagsContainsTagsPredicate(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public boolean test(Person person) {
        Tag[] arrayTags = new Tag[person.getTags().toArray().length];
        return tagList.stream()
                .allMatch(tag -> Arrays.stream(person.getTags().toArray(arrayTags))
                        .anyMatch(personTag-> personTag.compareTag(tag, false)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonTagsContainsTagsPredicate // instanceof handles nulls
                && tagList.equals(((PersonTagsContainsTagsPredicate) other).tagList)); // state check
    }

}
