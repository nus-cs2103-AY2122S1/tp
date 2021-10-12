package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class ContactHasRolePredicate implements Predicate<Person> {

    private final List<String> tags;

    public ContactHasRolePredicate(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        if (!tags.isEmpty()) {
            return tags.stream()
                    .anyMatch(tag -> person.getRole().equals(new Role(tag.toLowerCase())));
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactHasRolePredicate // instanceof handles nulls
                && tags.equals(((ContactHasRolePredicate) other).tags)); // state check
    }
}
