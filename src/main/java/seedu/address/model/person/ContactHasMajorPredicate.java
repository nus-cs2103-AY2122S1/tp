package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class ContactHasMajorPredicate implements Predicate<Person> {

    private final List<String> tags;

    public ContactHasMajorPredicate(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        if (!tags.isEmpty()) {
            return tags.stream()
                    .anyMatch(tag -> person.getMajor().equals(new Major(tag.toLowerCase())));
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactHasMajorPredicate // instanceof handles nulls
                && tags.equals(((ContactHasMajorPredicate) other).tags)); // state check
    }
}
