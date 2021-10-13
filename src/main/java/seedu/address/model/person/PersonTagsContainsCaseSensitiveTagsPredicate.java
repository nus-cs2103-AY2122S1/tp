package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


/**
 * Tests that a {@code Person}'s {@code Tag} matches all of the tags given.
 */
public class PersonTagsContainsCaseSensitiveTagsPredicate extends PersonTagsContainsTagsPredicate {
    public PersonTagsContainsCaseSensitiveTagsPredicate(List<Tag> tagList) {
        super(tagList);
    }

    @Override
    public boolean test(Person person) {
        Tag[] arrayTags = new Tag[person.getTags().toArray().length];
        return super.tagList.stream()
                .allMatch(tag -> Arrays.stream(person.getTags().toArray(arrayTags))
                        .anyMatch(personTag-> personTag.compareTag(tag, true)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonTagsContainsCaseSensitiveTagsPredicate // instanceof handles nulls
                && tagList.equals(((PersonTagsContainsCaseSensitiveTagsPredicate) other).tagList)); // state check
    }

}
