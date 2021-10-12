package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class ContactHasFacultyPredicate implements Predicate<Person> {

    private final List<String> tags;

    public ContactHasFacultyPredicate(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        if (!tags.isEmpty()) {
            return tags.stream()
                    .anyMatch(tag -> person.getFaculty().equals(new Faculty(tag.toLowerCase())));
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactHasFacultyPredicate // instanceof handles nulls
                && tags.equals(((ContactHasFacultyPredicate) other).tags)); // state check
    }
}
