package seedu.siasa.testutil;

import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PRICE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_TITLE_CRITICAL;
import static seedu.siasa.testutil.TypicalPersons.ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.siasa.model.policy.Policy;

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
