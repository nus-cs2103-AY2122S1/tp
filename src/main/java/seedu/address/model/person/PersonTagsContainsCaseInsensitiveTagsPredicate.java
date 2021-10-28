package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches all of the tags given.
 */
public class PersonTagsContainsCaseInsensitiveTagsPredicate extends PersonTagsContainsTagsPredicate {
    public PersonTagsContainsCaseInsensitiveTagsPredicate(List<Tag> tagList) {
        super(tagList);
    }

    @Override
    public boolean test(Person person) {
        Tag[] arrayTags = new Tag[person.getTags().toArray().length];
        return super.tagList.stream()
                .allMatch(tag -> Arrays.stream(person.getTags().toArray(arrayTags))
                        .anyMatch(personTag-> personTag.compareTag(tag, false)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonTagsContainsCaseInsensitiveTagsPredicate // instanceof handles nulls
                && tagList.equals(((PersonTagsContainsCaseInsensitiveTagsPredicate) other).tagList)); // state check
    }

}
