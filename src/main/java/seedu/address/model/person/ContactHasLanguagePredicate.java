package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.skill.Language;

public class ContactHasLanguagePredicate implements Predicate<Person> {
    private final List<String> tags;

    public ContactHasLanguagePredicate(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        if (!tags.isEmpty()) {
            return tags.stream()
                    .anyMatch(tag -> person.getLanguages().contains(new Language(tag.toLowerCase())));
        }
        return true;
    }
}
