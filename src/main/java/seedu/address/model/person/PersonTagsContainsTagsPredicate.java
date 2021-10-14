package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;


/**
 * Tests that a {@code Person}'s {@code Tag} matches all of the tags given.
 */
public abstract class PersonTagsContainsTagsPredicate implements Predicate<Person> {
    protected final List<Tag> tagList;

    public PersonTagsContainsTagsPredicate(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
