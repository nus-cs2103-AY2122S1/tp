package seedu.siasa.testutil;

import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Policy;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_TITLE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PRICE_CRITICAL;



import static seedu.siasa.testutil.TypicalPersons.ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalPolicies {
    public static final Policy FULL_LIFE = new PolicyBuilder(ALICE).build();
    public static final Policy CRITICAL_ILLNESS = new PolicyBuilder(ALICE)
            .withTitle(VALID_POLICY_TITLE_CRITICAL)
            .withCommission(VALID_POLICY_COMMISSION_CRITICAL)
            .withExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL)
            .withPrice(VALID_POLICY_PRICE_CRITICAL)
            .build();

    private TypicalPolicies() {} // prevents instantiation

    public static List<Policy> getTypicalPolicies() {
        return new ArrayList<>(Arrays.asList(FULL_LIFE, CRITICAL_ILLNESS));
    }
}
