package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class AvailabilityContainsKeywordsPredicate implements Predicate<Person> {
    private final List<Availability> availability;

    public AvailabilityContainsKeywordsPredicate(List<Availability> availability) {
        this.availability = availability;
    }
    @Override
    public boolean test(Person person) {
        return availability.stream()
                .anyMatch(availability -> person.getAvailability().containsAll(availability));
    }
}
