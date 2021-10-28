package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;


/**
 * Tests that a {@code Person} matches any of the keywords given.
 */
public class PersonMatchesKeywordsPredicate implements Predicate<Person> {
    private final Name name;
    private final Phone phone;
    private final List<Tag> tags;
    private final Availability availability;
    private final TodayAttendance todayAttendance;
    private final TotalAttendance totalAttendance;
    private final Predicate<Person> predicate;

    private PersonMatchesKeywordsPredicate(Name name, Phone phone, List<Tag> tags,
                                           Availability availability, TodayAttendance todayAttendance,
                                           TotalAttendance totalAttendance, Predicate<Person> predicate) {
        this.name = name;
        this.phone = phone;
        this.tags = tags;
        this.availability = availability;
        this.todayAttendance = todayAttendance;
        this.totalAttendance = totalAttendance;
        this.predicate = predicate;
    }

    @Override
    public boolean test(Person person) {
        return predicate.test(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonMatchesKeywordsPredicate // instanceof handles nulls
                && name.equals(((PersonMatchesKeywordsPredicate) other).name) // state check
                && phone.equals(((PersonMatchesKeywordsPredicate) other).phone)
                && tags.equals(((PersonMatchesKeywordsPredicate) other).tags)
                && todayAttendance.equals(((PersonMatchesKeywordsPredicate) other).todayAttendance)
                && totalAttendance.equals(((PersonMatchesKeywordsPredicate) other).totalAttendance));
    }

    public static class Builder {
        // default values
        private Name name = new Name("DEFAULT");
        private Phone phone = new Phone("00000000");
        private List<Tag> tags = new ArrayList<>();
        private Availability availability = new Availability(new ArrayList<>());
        private TodayAttendance todayAttendance = new TodayAttendance(false);
        private TotalAttendance totalAttendance = new TotalAttendance(0);
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

        public Builder setTodayAttendance(TodayAttendance todayAttendance) {
            this.todayAttendance = todayAttendance;
            return this;
        }

        public Builder setTotalAttendance(TotalAttendance totalAttendance) {
            this.totalAttendance = totalAttendance;
            return this;
        }


        public Builder setPredicate(Predicate<Person> predicate) {
            this.predicate = predicate;
            return this;
        }

        /**
         * Builds a {@code PersonMatchesKeywordsPredicate} to be used in {@code FindMemberCommand}
         */
        public PersonMatchesKeywordsPredicate build() {
            return new PersonMatchesKeywordsPredicate(name, phone, tags, availability,
                    todayAttendance, totalAttendance, predicate);
        }
    }
}
