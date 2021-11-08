package seedu.siasa.testutil;

import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_NUM_OF_PAYMENTS_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_PERCENTAGE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CONST;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_NUMBER_OF_PAYMENTS_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PAYMENT_AMOUNT_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PAYMENT_FREQUENCY_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_TITLE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_TAG_TERM_INSURANCE;
import static seedu.siasa.testutil.TypicalContacts.ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.siasa.model.policy.Policy;

public class TypicalPolicies {
    public static final Policy FULL_LIFE = new PolicyBuilder(ALICE).build();
    public static final Policy CRITICAL_ILLNESS = new PolicyBuilder(ALICE)
            .withTitle(VALID_POLICY_TITLE_CRITICAL)
            .withCommission(VALID_POLICY_COMMISSION_PERCENTAGE_CRITICAL,
                    VALID_POLICY_COMMISSION_NUM_OF_PAYMENTS_CRITICAL)
            .withExpiryDate(VALID_POLICY_EXPIRY_DATE_CONST)
            .withPaymentStructure(VALID_POLICY_PAYMENT_AMOUNT_CRITICAL,
                    VALID_POLICY_PAYMENT_FREQUENCY_CRITICAL,
                    VALID_POLICY_NUMBER_OF_PAYMENTS_CRITICAL)
            .withTags(VALID_TAG_TERM_INSURANCE)
            .build();

    private TypicalPolicies() {} // prevents instantiation

    public static List<Policy> getTypicalPolicies() {
        return new ArrayList<>(Arrays.asList(FULL_LIFE, CRITICAL_ILLNESS));
    }
}
