package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

/**
 * Tests that a {@code Person}'s {@code Status} is equal to confirmed status
 */
public class StatusEqualsConfirmedPredicate implements Predicate<Person> {

    private Status status;

    /**
     * Creates new StatusEqualsConfirmedPredicate with confirmed status
     */
    public StatusEqualsConfirmedPredicate() {
        this.status = new Status("Confirmed");
    }

    @Override
    public boolean test(Person person) {
        return person.hasSameStatus(status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusEqualsConfirmedPredicate); // state check
    }
}
