package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.skill.Framework;

public class ContactHasFrameworkPredicate implements Predicate<Person> {

    private final List<String> tags;

    public ContactHasFrameworkPredicate(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        if (!tags.isEmpty()) {
            return tags.stream()
                    .anyMatch(tag -> person.getFrameworks().contains(new Framework(tag.toLowerCase())));
        }
        return true;
    }
}
