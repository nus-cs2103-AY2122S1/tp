package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_PERCENTAGE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PAYMENT_AMOUNT_CRITICAL;
import static seedu.siasa.testutil.Assert.assertThrows;
import static seedu.siasa.testutil.TypicalPolicies.CRITICAL_ILLNESS;
import static seedu.siasa.testutil.TypicalPolicies.FULL_LIFE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.siasa.model.policy.exceptions.DuplicatePolicyException;
import seedu.siasa.model.policy.exceptions.PolicyNotFoundException;
import seedu.siasa.testutil.PolicyBuilder;

class UniquePolicyListTest {
    private final UniquePolicyList uniquePolicyList = new UniquePolicyList();

    @Test
    public void contains_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.contains(null));
    }

    @Test
    public void contains_policyNotInList_returnsFalse() {
        assertFalse(uniquePolicyList.contains(FULL_LIFE));
    }

    @Test
    public void contains_policyInList_returnsTrue() {
        uniquePolicyList.add(FULL_LIFE);
        assertTrue(uniquePolicyList.contains(FULL_LIFE));
    }

    @Test
    public void contains_policyWithSameIdentityFieldsInList_returnsTrue() {
        uniquePolicyList.add(FULL_LIFE);
        Policy editedFullLife = new PolicyBuilder(FULL_LIFE)
                .withPaymentStructure(VALID_POLICY_PAYMENT_AMOUNT_CRITICAL)
                .withCommission(VALID_POLICY_COMMISSION_PERCENTAGE_CRITICAL)
                .withExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL)
                .build();
        assertTrue(uniquePolicyList.contains(editedFullLife));
    }

    @Test
    public void add_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.add(null));
    }

    @Test
    public void add_duplicatePolicy_throwsDuplicatePolicyException() {
        uniquePolicyList.add(FULL_LIFE);
        assertThrows(DuplicatePolicyException.class, () -> uniquePolicyList.add(FULL_LIFE));
    }

    @Test
    public void setPolicy_nullTargetPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicy(null, FULL_LIFE));
    }

    @Test
    public void setPolicy_nullEditedPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicy(FULL_LIFE, null));
    }

    @Test
    public void setPolicy_targetPolicyNotInList_throwsPolicyNotFoundException() {
        assertThrows(PolicyNotFoundException.class, () -> uniquePolicyList.setPolicy(FULL_LIFE, CRITICAL_ILLNESS));
    }

    @Test
    public void setPolicy_editedPolicyIsSamePolicy_success() {
        uniquePolicyList.add(FULL_LIFE);
        uniquePolicyList.setPolicy(FULL_LIFE, FULL_LIFE);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(FULL_LIFE);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPolicy_editedPolicyHasSameIdentity_success() {
        uniquePolicyList.add(FULL_LIFE);
        Policy editedFullLife = new PolicyBuilder(FULL_LIFE)
                .withPaymentStructure(VALID_POLICY_PAYMENT_AMOUNT_CRITICAL)
                .withCommission(VALID_POLICY_COMMISSION_PERCENTAGE_CRITICAL)
                .withExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL)
                .build();
        uniquePolicyList.setPolicy(FULL_LIFE, editedFullLife);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(editedFullLife);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPolicy_editedPolicyHasDifferentIdentity_success() {
        uniquePolicyList.add(FULL_LIFE);
        uniquePolicyList.setPolicy(FULL_LIFE, CRITICAL_ILLNESS);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(CRITICAL_ILLNESS);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPolicy_editedPolicyHasNonUniqueIdentity_throwsDuplicatePolicyException() {
        uniquePolicyList.add(FULL_LIFE);
        uniquePolicyList.add(CRITICAL_ILLNESS);
        assertThrows(DuplicatePolicyException.class, () -> uniquePolicyList.setPolicy(FULL_LIFE, CRITICAL_ILLNESS));
    }

    @Test
    public void remove_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.remove(null));
    }

    @Test
    public void remove_policyDoesNotExist_throwsPolicyNotFoundException() {
        assertThrows(PolicyNotFoundException.class, () -> uniquePolicyList.remove(FULL_LIFE));
    }

    @Test
    public void remove_existingPolicy_removesPolicy() {
        uniquePolicyList.add(FULL_LIFE);
        uniquePolicyList.remove(FULL_LIFE);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPolicies_nullUniquePolicyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicies((UniquePolicyList) null));
    }

    @Test
    public void setPolicies_uniquePolicyList_replacesOwnListWithProvidedUniquePolicyList() {
        uniquePolicyList.add(FULL_LIFE);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(CRITICAL_ILLNESS);
        uniquePolicyList.setPolicies(expectedUniquePolicyList);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPolicies_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicies((List<Policy>) null));
    }

    @Test
    public void setPolicies_list_replacesOwnListWithProvidedList() {
        uniquePolicyList.add(FULL_LIFE);
        List<Policy> policyList = Collections.singletonList(CRITICAL_ILLNESS);
        uniquePolicyList.setPolicies(policyList);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(CRITICAL_ILLNESS);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPolicies_listWithDuplicatePolicies_throwsDuplicatePolicyException() {
        List<Policy> listWithDuplicatePolicies = Arrays.asList(FULL_LIFE, FULL_LIFE);
        assertThrows(DuplicatePolicyException.class, () -> uniquePolicyList.setPolicies(listWithDuplicatePolicies));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniquePolicyList.asUnmodifiableObservableList().remove(0));
    }
}
