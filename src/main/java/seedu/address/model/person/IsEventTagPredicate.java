package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class IsEventTagPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(Tag::isEvent);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof IsEventTagPredicate;
    }

}

