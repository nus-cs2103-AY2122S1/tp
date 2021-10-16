package seedu.siasa.testutil;

import seedu.siasa.model.Siasa;
import seedu.siasa.model.policy.Policy;

public class TypicalPolicyBook {
    /**
     * Returns an {@code Siasa} with all the typical policies.
     */
    public static Siasa getTypicalPolicyBook() {
        Siasa siasa = new Siasa();
        for (Policy policy : TypicalPolicies.getTypicalPolicies()) {
            assert siasa.hasPerson(policy.getOwner());
            siasa.addPolicy(policy);
        }
        return siasa;
    }
}
