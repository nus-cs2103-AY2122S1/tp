package seedu.siasa.testutil;

import seedu.siasa.model.Siasa;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Policy;

public class TypicalSiasa {
    /**
     * Returns an {@code Siasa} with all the typical persons and policies.
     */
    public static Siasa getTypicalSiasa() {
        Siasa siasa = new Siasa();
        for (Person person : TypicalPersons.getTypicalPersons()) {
            siasa.addPerson(person);
        }
        for (Policy policy : TypicalPolicies.getTypicalPolicies()) {
            assert siasa.hasPerson(policy.getOwner());
            siasa.addPolicy(policy);
        }
        return siasa;
    }
}
