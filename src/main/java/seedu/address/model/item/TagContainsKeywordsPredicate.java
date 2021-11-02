package seedu.address.model.item;

import java.util.Collection;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Item}'s {@code tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Item> {
    private final Collection<Tag> keytags;

    public TagContainsKeywordsPredicate(Collection<Tag> keytags) {
        this.keytags = keytags;
    }

    @Override
    public boolean test(Item item) {
        int lengthOfItemTag = item.getTags().toString().length();
        if (item.getTags().isEmpty()) {
            return false;
        }

        return item.getTags().stream().anyMatch(keytags::contains);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keytags.equals(((TagContainsKeywordsPredicate) other).keytags)); // state check
    }

}
