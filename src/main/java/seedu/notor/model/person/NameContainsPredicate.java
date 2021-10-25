package seedu.notor.model.person;

import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.notor.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} fits the search query. Searches can optionally include a string to be
 * checked for in the name of the person, and / or tags which should be present in the tagset. Predicate only returns
 * true if all conditions are true
 */
public class NameContainsPredicate implements Predicate<Person> {
    private final Optional<String> nameQuery;
    private final Optional<Set<Tag>> tagQuery;

    /**
     * Constructor for the name finding predicate
     *
     * @param nameQuery A parameter that might be a substring to search the name for
     * @param tagQuery A parameter that might be a set of tags which must all match the person
     */
    public NameContainsPredicate(Optional<String> nameQuery, Optional<Set<Tag>> tagQuery) {
        this.nameQuery = nameQuery;
        this.tagQuery = tagQuery;
    }

    @Override
    public boolean test(Person person) {
        boolean nameHasMatch =
                person.getName().fullName.toLowerCase(Locale.ROOT)
                        .contains(nameQuery.orElse("").toLowerCase(Locale.ROOT));
        boolean tagsHaveMatch = person.getTags().containsAll(tagQuery.orElse(new HashSet<>()));
        return nameHasMatch && tagsHaveMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsPredicate // instanceof handles nulls
                && nameQuery.equals(((NameContainsPredicate) other).nameQuery))
                && tagQuery.equals(((NameContainsPredicate) other).tagQuery); // state check
    }

}
