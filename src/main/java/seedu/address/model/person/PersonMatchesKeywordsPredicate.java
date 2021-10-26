package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

public class PersonMatchesKeywordsPredicate implements Predicate<Person> {
    private final Name name;
    private final Phone phone;
    private final List<Tag> tags;
    private final Availability availability;
    private final Predicate<Person> predicate;

    private PersonMatchesKeywordsPredicate(Name name, Phone phone, List<Tag> tags,
                                           Availability availability, Predicate<Person> predicate) {
        this.name = name;
        this.phone = phone;
        this.tags = tags;
        this.availability = availability;
        this.predicate = predicate;
    }

    @Override
    public boolean test(Person person) {
        return predicate.test(person);
    }

    public static class Builder {
        // default values
        private Name name;
        private Phone phone;
        private List<Tag> tags;
        private Availability availability;
        private Predicate<Person> predicate;

        public Builder setName(Name name) {
            this.name = name;
            return this;
        }

        public Builder setPhone(Phone phone) {
            this.phone = phone;
            return this;
        }

        public Builder setTags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder setAvailability(Availability availability) {
            this.availability = availability;
            return this;
        }

        public Builder setPredicate(Predicate<Person> predicate) {
            this.predicate = predicate;
            return this;
        }

        public PersonMatchesKeywordsPredicate build() {
            return new PersonMatchesKeywordsPredicate(name, phone, tags, availability, predicate);
        }
    }
}
