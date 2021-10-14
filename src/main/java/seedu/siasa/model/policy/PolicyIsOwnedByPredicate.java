package seedu.siasa.model.policy;

import java.util.function.Predicate;

import seedu.siasa.model.person.Person;

public class PolicyIsOwnedByPredicate implements Predicate<Policy> {
    private final Person client;

    public PolicyIsOwnedByPredicate(Person client) {
        this.client = client;
    }

    @Override
    public boolean test(Policy policy) {
        return policy.getOwner().equals(client);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof PolicyIsOwnedByPredicate
                && client.equals(((PolicyIsOwnedByPredicate) other).client));
    }
}
