package seedu.siasa.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_PERCENTAGE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PAYMENT_AMOUNT_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.siasa.testutil.Assert.assertThrows;
import static seedu.siasa.testutil.TypicalContacts.ALICE;
import static seedu.siasa.testutil.TypicalPolicies.FULL_LIFE;
import static seedu.siasa.testutil.TypicalSiasa.getTypicalSiasa;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.contact.exceptions.DuplicateContactException;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.exceptions.DuplicatePolicyException;
import seedu.siasa.testutil.ContactBuilder;
import seedu.siasa.testutil.PolicyBuilder;

public class SiasaTest {

    private final Siasa siasa = new Siasa();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), siasa.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> siasa.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySiasa_replacesData() {
        Siasa newData = getTypicalSiasa();
        siasa.resetData(newData);
        assertEquals(newData, siasa);
    }

    @Test
    public void resetData_withDuplicateContact_throwsDuplicateContactException() {
        // Two persons with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        SiasaStub newData = new SiasaStub(newContacts);

        assertThrows(DuplicateContactException.class, () -> siasa.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> siasa.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInSiasa_returnsFalse() {
        assertFalse(siasa.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInSiasa_returnsTrue() {
        siasa.addContact(ALICE);
        assertTrue(siasa.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInSiasa_returnsTrue() {
        siasa.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(siasa.hasContact(editedAlice));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> siasa.getContactList().remove(0));
    }

    @Test
    public void resetData_withDuplicatePolicy_throwsDuplicatePolicyException() {
        // Two policies with the same identity fields (name and owner)
        Policy editedLifePlan = new PolicyBuilder(FULL_LIFE)
                .withPaymentStructure(VALID_POLICY_PAYMENT_AMOUNT_CRITICAL)
                .withCommission(VALID_POLICY_COMMISSION_PERCENTAGE_CRITICAL)
                .withExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL)
                .build();
        List<Policy> policies = Arrays.asList(FULL_LIFE, editedLifePlan);
        List<Contact> contacts = Arrays.asList(FULL_LIFE.getOwner());
        SiasaStub newData = new SiasaStub(contacts, policies);

        assertThrows(DuplicatePolicyException.class, () -> siasa.resetData(newData));
    }

    @Test
    public void hasPolicy_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> siasa.hasPolicy(null));
    }

    @Test
    public void hasPolicy_policyNotInSiasa_returnsFalse() {
        assertFalse(siasa.hasPolicy(FULL_LIFE));
    }

    @Test
    public void hasPolicy_policyInSiasa_returnsTrue() {
        siasa.addPolicy(FULL_LIFE);
        assertTrue(siasa.hasPolicy(FULL_LIFE));
    }

    @Test
    public void hasPolicy_policyWithSameIdentityFieldsInSiasa_returnsTrue() {
        siasa.addPolicy(FULL_LIFE);
        Policy editedFullLife = new PolicyBuilder(FULL_LIFE)
                .withPaymentStructure(VALID_POLICY_PAYMENT_AMOUNT_CRITICAL)
                .withCommission(VALID_POLICY_COMMISSION_PERCENTAGE_CRITICAL)
                .withExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL)
                .build();
        assertTrue(siasa.hasPolicy(editedFullLife));
    }

    /**
     * A stub ReadOnlySiasa whose persons list can violate interface constraints.
     */
    private static class SiasaStub implements ReadOnlySiasa {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
        private final ObservableList<Policy> policies = FXCollections.observableArrayList();

        SiasaStub(Collection<Contact> contacts, Collection<Policy> policies) {
            this.policies.setAll(policies);
            this.contacts.setAll(contacts);
        }

        SiasaStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }

        @Override
        public ObservableList<Policy> getPolicyList() {
            return policies;
        }
    }

}
