package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.skill.Skill;

public class ContactHasSkillPredicate implements Predicate<Person> {
    private final List<String> tags;

    public ContactHasSkillPredicate(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        if (!tags.isEmpty()) {
            return tags.stream().anyMatch(tag -> person.getSkills().contains(new Skill(tag.toLowerCase())));
        }
        return true;
    }
}
