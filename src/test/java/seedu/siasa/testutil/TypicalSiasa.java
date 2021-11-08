package seedu.siasa.testutil;

import seedu.siasa.model.Siasa;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Policy;

public class TypicalSiasa {
    /**
     * Returns an {@code Siasa} with all the typical persons.
     */
    public static Siasa getTypicalSiasa() {
        Siasa siasa = new Siasa();
        for (Contact contact : TypicalContacts.getTypicalContacts()) {
            siasa.addContact(contact);
        }
        for (Policy policy : TypicalPolicies.getTypicalPolicies()) {
            siasa.addPolicy(policy);
        }
        return siasa;
    }
}
