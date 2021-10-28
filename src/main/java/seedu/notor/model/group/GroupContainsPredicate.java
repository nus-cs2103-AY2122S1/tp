package seedu.notor.model.group;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that a {@code Group}'s {@code Name} fit the search query. Searches can optionally
 * include a string to be checked for in the name of the Group.
 */
public class GroupContainsPredicate implements Predicate<Group> {
    private final Optional<String> nameQuery;

    /**
     * Constructor for the name finding predicate
     *
     * @param nameQuery A parameter that might be a substring to search the name for
     */
    public GroupContainsPredicate(Optional<String> nameQuery) {
        this.nameQuery = nameQuery;
    }

    @Override
    public boolean test(Group group) {
        boolean nameHasMatch =
                group.getName().toLowerCase(Locale.ROOT)
                        .contains(nameQuery.orElse("").toLowerCase(Locale.ROOT));
        return nameHasMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupContainsPredicate // instanceof handles nulls
                && nameQuery.equals(((GroupContainsPredicate) other).nameQuery)); // state check
    }

}
