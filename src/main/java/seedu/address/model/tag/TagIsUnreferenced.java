package seedu.address.model.tag;

import java.util.function.Predicate;

/**
 * Tests if {@code Tag} is unreferenced by persons.
 */
public class TagIsUnreferenced implements Predicate<Tag> {

    @Override
    public boolean test(Tag tag) {
        return tag.getPersons().size() == 0;
    }
}

