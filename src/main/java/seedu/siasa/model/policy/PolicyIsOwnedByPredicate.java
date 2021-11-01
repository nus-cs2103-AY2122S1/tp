package seedu.siasa.model.policy;

import java.util.function.Predicate;

import seedu.siasa.model.contact.Contact;

public class PolicyIsOwnedByPredicate implements Predicate<Policy> {
    private final Contact contact;

    public PolicyIsOwnedByPredicate(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean test(Policy policy) {
        return policy.getOwner().equals(contact);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof PolicyIsOwnedByPredicate
                && contact.equals(((PolicyIsOwnedByPredicate) other).contact));
    }
}
